package com.guc.visit.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guc.visit.controller.DefaultHelper;
import com.guc.visit.controller.GucSDKHelper;


import java.util.List;

public class GucApplication extends Application {

    public static GucApplication instance;
    public static RequestQueue mRequestQueue;
    public static DefaultHelper hxSDKHelper = new DefaultHelper();
    public static Context applicationContext;
    public static String cr_org_name;
    public static String loginUserCode;
    public static String visit_doctor;
    public static String doctorCode;
    /**
     * 0 splash
     * 1 normal
     * 2 login
     */
    private boolean islogin = false;

    public boolean getIslogin() {
        return islogin;
    }

    public void setIslogin(boolean islogin) {
        this.islogin = islogin;
    }

    public GucApplication() {
        super();

    }

    public static GucApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        applicationContext = this;
        instance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        GucSDKHelper.getInstance().onInit(getApplicationContext());
        super.onCreate();
    }

    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//					Log.i(context.getPackageName(), "处于后台"
//							+ appProcess.processName);
                    return true;
                } else {
//					Log.i(context.getPackageName(), "处于前台"
//							+ appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前登陆用户名
     *
     * @return
     */
    public String getUserName() {
        return hxSDKHelper.getHXId();
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getPassword() {
        return hxSDKHelper.getPassword();
    }

    /**
     * 设置用户名
     *
     * @param user
     */
    public void setUserName(String username) {
        hxSDKHelper.setHXId(username);
    }

    /**
     * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
     * 内部的自动登录需要的密码，已经加密存储了
     *
     * @param pwd
     */
    public void setPassword(String pwd) {
        hxSDKHelper.setPassword(pwd);
    }
}
