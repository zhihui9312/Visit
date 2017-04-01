package com.guc.visit.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseActivity;
import com.guc.visit.fragment.SplashFragment;
import com.guc.visit.net.DefaultErrorListener;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.DownLoadManager;
import com.guc.visit.utils.ToastUtils;


import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private FragmentManager mFragmentManager;
    private boolean isExit;
    private boolean isBack;
    private int cur_version;
    private boolean isEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUpdate();
            }
        }, 3000);
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!GucApplication.getInstance().getIslogin() && !isEnter) {
            replace("splashfragment", SplashFragment.newInstance(), false);
            isEnter = true;
        }
    }

    @Override
    public void popBackStack(int i) {
        for (int j = 0; j < i; j++) {
            mFragmentManager.popBackStack();
        }
    }


    @Override
    public void replace(Fragment fragment, boolean isaddToBackStack) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (isaddToBackStack) {
            fragmentTransaction.replace(android.R.id.content, fragment).addToBackStack(null).commitAllowingStateLoss();
        } else {
            fragmentTransaction.replace(android.R.id.content, fragment).commitAllowingStateLoss();
        }
    }

    @Override
    public void replace(String tag, Fragment fragment, boolean isaddToBackStack) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (isaddToBackStack) {
            fragmentTransaction.replace(android.R.id.content, fragment, tag).addToBackStack(null).commitAllowingStateLoss();
        } else {
            fragmentTransaction.replace(android.R.id.content, fragment, tag).commitAllowingStateLoss();
        }
    }

    @Override
    protected void setListeners() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(new OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                int stackCount = mFragmentManager.getBackStackEntryCount();
                isBack = stackCount > 0;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && !isBack) {
            if (GucApplication.getInstance().getIslogin()) {
                exitBy2Click();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitBy2Click() {
        Timer tExit;
        if (isExit) {
            finish();
            System.exit(0);
        } else {
            isExit = true;
            ToastUtils.showShort(getApplicationContext(), R.string.click_two_exit);
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        }
    }

    private void checkUpdate() {
        GucNetEngineClient.getVersion(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject object = JSON.parseObject(response);
                JSONObject objResult = object.getJSONObject("getVersionResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    String currentVersion = objResult.getString("result");
                    float locationVersion = Float.parseFloat(getVersionName());
                    cur_version = Integer.parseInt(currentVersion);
                    if (cur_version > locationVersion) {
                        showUpdateDialog();
                    }
                } else {
                    ToastUtils.showLong(MainActivity.this, errInfo);
                }
            }
        }, new DefaultErrorListener());
    }

    protected void showUpdateDialog() {

        new MaterialDialog.Builder(this)
                .content(R.string.the_new_version_is_visible)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        getAppURL();
                        dialog.dismiss();
                    }
                }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();
    }

    private void getAppURL() {
        GucNetEngineClient.getAppUrl(cur_version + "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject obj_res = jsonObject.getJSONObject("getAppURLResult");
                String url = obj_res.getString("result");
                downLoadApk(url);
            }
        }, new DefaultErrorListener());
    }

    protected void downLoadApk(final String url) {
        final ProgressDialog pd;
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = DownLoadManager.getFileFromServer(url, pd);
                    sleep(2000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private String getVersionName() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
        return packInfo.versionName;
    }
}
