package com.guc.visit.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.GucPreferenceUtils;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ToastUtils;


public class LoginFragment extends BaseFragment implements OnCheckedChangeListener {
    private TextView tv_login;
    private TextView tv_register;
    private EditText input_account;
    private EditText input_password;
    private CheckBox cb_save_password;
    private CheckBox cb_show_password;
    private boolean isRemember;
    private String account;
    private String password;
    private boolean isShowPwd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_login);
    }

    @Override
    public void onClick(View v) {
        hideSoftInput();
        switch (v.getId()) {
            case R.id.tv_login:
                checkLimit();
                break;
            case R.id.tv_register:
                mActivity.replace("registerFragment", RegisterFragment.newInstance(), true);
                break;
            default:
                break;
        }
    }

    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(input_account.getWindowToken(), 0);
    }

    @Override
    protected void initData() {
        controlBar(R.string.login, R.string.login, false, false);
        if (GucPreferenceUtils.getInstance().getIsSavePwd()) {
            cb_save_password.setChecked(true);
            input_account.setText(GucApplication.getInstance().getUserName());
            input_password.setText(GucApplication.getInstance().getPassword());
        }
        if (GucPreferenceUtils.getInstance().getIsShowPwd()) {
            cb_show_password.setChecked(true);
        }
    }

    @Override
    protected void setListeners() {
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        cb_save_password.setOnCheckedChangeListener(this);
        cb_show_password.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initWidget(View view) {
        input_account = (EditText) view.findViewById(R.id.input_account);
        input_password = (EditText) view.findViewById(R.id.input_password);
        cb_save_password = (CheckBox) view.findViewById(R.id.cb_save_password);
        cb_show_password = (CheckBox) view.findViewById(R.id.cb_show_password);
        tv_register = (TextView) view.findViewById(R.id.tv_register);
        tv_login = (TextView) view.findViewById(R.id.tv_login);
        tv_register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_register.getPaint().setAntiAlias(true);
    }

    private void checkLimit() {
        account = input_account.getText().toString().trim();
        password = input_password.getText().toString().trim();
        // account="18988262866";
        //password = "superman12345";
        //account = "13800138000";


        //account = "13400134000";
        //password = "12345";

        account = "13500135000";
        password = "guc258";
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showShort(mActivity, R.string.account_not_null);
            return;
        }
        if (TextUtils.isEmpty(password) || !StrUtil.checkPwd(password)) {
            ToastUtils.showShort(mActivity, R.string.psssword_not_null);
            return;
        }
        materialDialog = showIndeterminateProgressDialog(R.string.is_landing);
        GucNetEngineClient.checkLimit(account, new Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                String errInfo = jsonObject.getString("errInfo");
                if (errInfo == null) {
                    getDbid();
                } else {
                    materialDialog.dismiss();
                    ToastUtils.showLong(mActivity, errInfo);
                }
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                materialDialog.dismiss();
            }
        });
    }


    private void getDbid() {
        GucNetEngineClient.getDbid(account, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject json_res = jsonObject.getJSONObject("getDBIDResult");
                String errInfo = json_res.getString("errInfo");
                if (errInfo == null) {
                    String dbid = json_res.getString("result");
                    GucNetEngineClient.DBID = dbid;
                    getDoctorInfo();
                } else {
                    materialDialog.dismiss();
                    ToastUtils.showLong(mActivity, errInfo);
                }
            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                materialDialog.dismiss();
            }
        });
    }

    private void getDoctorInfo() {
        GucNetEngineClient.getDoctorInfo(account, password, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                materialDialog.dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject json_res = jsonObject.getJSONObject("getDoctorInfoResult");
                String errInfo = json_res.getString("errInfo");
                if (errInfo == null) {
                    JSONObject json_info = json_res.getJSONObject("doctorInfo");
                    GucNetEngineClient.ORG_CODE = json_info.getString("orgCode");
                    GucApplication.cr_org_name = json_info.getString("orgName");
                    GucApplication.loginUserCode = json_info.getString("login_user_code");
                    GucApplication.visit_doctor = json_info.getString("doctorName");
                    GucApplication.doctorCode = json_info.getString("doctorCode");
//                    String doctorName = json_info.getString("doctorName");
//                    String orgName = json_info.getString("orgName");
//                    String login_user_code = json_info.getString("login_user_code");
//                    savePreference(orgCode, doctorCode);
                    saveSharData();
                    mActivity.replace("mainfragment", MainFragment.newInstance(), false);
                } else {
                    materialDialog.dismiss();
                    ToastUtils.showLong(mActivity, errInfo);
                }
            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                materialDialog.dismiss();
                ToastUtils.showLong(mActivity, error.toString());
            }
        });
    }

    private void saveSharData() {
        GucPreferenceUtils.getInstance().setIsSavePwd(isRemember);
        GucPreferenceUtils.getInstance().setIsShowPwd(isShowPwd);
        GucApplication.getInstance().setUserName(account);
        GucApplication.getInstance().setPassword(password);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_show_password:
                isShowPwd = isChecked;
                if (isChecked) {
                    input_password.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    input_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.cb_save_password:
                isRemember = isChecked;
                break;
            default:
                break;
        }
    }

    public static Fragment newInstance() {
        return new LoginFragment();
    }
}