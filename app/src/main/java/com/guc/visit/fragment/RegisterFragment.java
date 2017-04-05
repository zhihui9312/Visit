package com.guc.visit.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response.Listener;
import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.net.DefaultErrorListener;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.ToastUtils;

import java.util.HashMap;


public class RegisterFragment extends BaseFragment {
    private EditText tv_organization;
    private TextView tv_register;
    private EditText input_moblie;
    private EditText input_password;
    private String organizationId = "";
    private String orgName = "";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_register);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.tv_register:
                checkData();
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        controlBar(R.string.register, R.string.back, true, false);
        tv_organization.setText(orgName);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            HashMap<String, String> map = (HashMap<String, String>) data.getSerializableExtra("map");
            organizationId = map.get("organizationId");
            orgName = map.get("orgName");
        }
    }

    private void checkData() {
        String moblie = input_moblie.getText().toString().trim();
        String password = input_password.getText().toString().trim();
        if (TextUtils.isEmpty(organizationId)) {
            ToastUtils.showShort(mActivity, R.string.org_is_not_null);
            return;
        }
        if (TextUtils.isEmpty(moblie)) {
            ToastUtils.showShort(mActivity, R.string.account_not_null);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort(mActivity, R.string.psssword_not_null);
            return;
        }
        register(moblie, password);

    }

    private void register(String mobile, String password) {
        materialDialog= showIndeterminateProgressDialog(R.string.isSubmitting);
        GucNetEngineClient.register(organizationId, mobile, password, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject resResult = jsonObject.getJSONObject("RegisterResult");
                String result = resResult.getString("result");
                String errInfo = resResult.getString("errInfo");
                if (result.equals("false")) {
                    ToastUtils.showShort(mActivity.getApplicationContext(), errInfo);
                } else {
                    result.equals("true");
                    ToastUtils.showShort(mActivity.getApplicationContext(), R.string.register_success);
                    mActivity.popBackStack(1);
                }
            }
        }, null,materialDialog);
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        tv_organization.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (v.getId() == R.id.tv_organization) {
                        mActivity.replace("organizationFragment", OrganizationFragment.newInstance(RegisterFragment.this, 0), true);
                    }
                }
                return false;
            }
        });
        tv_register.setOnClickListener(this);
    }

    @Override
    protected void initWidget(View view) {
        tv_organization = (EditText) view.findViewById(R.id.tv_organization);
        input_moblie = (EditText) view.findViewById(R.id.input_moblie);
        input_password = (EditText) view.findViewById(R.id.input_password);
        tv_register = (TextView) view.findViewById(R.id.tv_register);
    }

    public static Fragment newInstance(String id, String orgname) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("orgname", orgname);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static Fragment newInstance() {
        return new RegisterFragment();
    }

}
