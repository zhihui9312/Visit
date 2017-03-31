package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.PregnantBaseDTO;
import com.guc.visit.listener.DateListener;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ToastUtils;

/**
 * 孕妇添加手册
 */
public class PregnantAddManualFragment extends BaseFragment implements View.OnTouchListener {
    private EditText manual_ehr_id;
    private EditText menses_over_date;
    private EditText due_birth_date;
    private EditText create_week;
    private EditText create_week_days;
    private Spinner is_high_risk;
    private EditText high_score;
    private String ehrId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_pregnant_maunal_add);
    }

    @Override
    protected void initData() {
        controlBar(R.string.add_maunal, R.string.back, true, true);
        ehrId = getArguments().getString("ehrId");
    }

    private String buildJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("manual_ehr_id", StrUtil.getToTrim(manual_ehr_id));
        jsonObject.put("menses_over_date", StrUtil.getToTrim(menses_over_date));
        jsonObject.put("due_birth_date", StrUtil.getToTrim(due_birth_date));
        jsonObject.put("create_week", StrUtil.getToTrim(create_week));
        jsonObject.put("create_week_days", StrUtil.getToTrim(create_week_days));
        int is_high_risk_position = is_high_risk.getSelectedItemPosition();
        jsonObject.put("is_high_risk", is_high_risk_position == 0 ? null : is_high_risk_position);
        jsonObject.put("high_score", StrUtil.getToTrim(high_score));

        jsonObject.put("db_id", GucNetEngineClient.DBID);
        jsonObject.put("ehr_id", ehrId);
        jsonObject.put("cr_operator", GucApplication.loginUserCode);
        jsonObject.put("cr_org_name", GucApplication.cr_org_name);
        jsonObject.put("cr_org_code", GucNetEngineClient.ORG_CODE);
        return jsonObject.toString();
    }

    @Override
    protected void setListeners() {
        right_layout.setOnClickListener(this);
        menses_over_date.setOnTouchListener(this);
        due_birth_date.setOnTouchListener(this);
        ll_back.setOnClickListener(this);
    }

    @Override
    protected void initWidget(View view) {
        manual_ehr_id = (EditText) view.findViewById(R.id.manual_ehr_id);
        menses_over_date = (EditText) view.findViewById(R.id.menses_over_date);
        due_birth_date = (EditText) view.findViewById(R.id.due_birth_date);
        create_week = (EditText) view.findViewById(R.id.create_week);
        create_week_days = (EditText) view.findViewById(R.id.create_week_days);
        is_high_risk = (Spinner) view.findViewById(R.id.is_high_risk);
        high_score = (EditText) view.findViewById(R.id.high_score);
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_right)).setText(R.string.commint);
    }

    private void addManual(String json) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.addMaunal(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject obj_res = jsonObject.getJSONObject("UploadMaternalRegisterResult");
                String result = obj_res.getString("result");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    ToastUtils.showLong(mActivity, R.string.add_success);
                    mActivity.popBackStack(1);
                } else {
                    ToastUtils.showLong(mActivity, errInfo);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_layout:
                addManual(buildJson());
                break;
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.menses_over_date:
                    showDatePicker(menses_over_date);
                    break;
                case R.id.due_birth_date:
                    showDatePicker(due_birth_date);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    public static PregnantAddManualFragment newInstance(String ehrId) {
        Bundle args = new Bundle();
        args.putString("ehrId",ehrId);
        PregnantAddManualFragment fragment = new PregnantAddManualFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
