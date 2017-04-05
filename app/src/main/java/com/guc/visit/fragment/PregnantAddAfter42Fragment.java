package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.PregnantAddDTO;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.HashMap;

public class PregnantAddAfter42Fragment extends BaseFragment implements View.OnTouchListener {
    private EditText name;
    private EditText visit_date;
    private EditText systolic_pressure;
    private EditText diastolic_pressure;
    private Spinner health_flag;
    private EditText health_info;
    private Spinner mental_flag;
    private EditText mental_skills;
    private Spinner breast;
    private EditText breast_abn;
    private Spinner lochia;
    private EditText lochia_abn;
    private Spinner uterus;
    private EditText uterus_abn;
    private Spinner trauma;
    private EditText trauma_abn;
    private EditText other;
    private Spinner visit_class;
    private EditText visit_class_abn;
    private EditText guidance;
    private StringBuilder guidance_code = new StringBuilder("");
    private EditText guidance_other;
    private Spinner transfert_mark;
    private EditText transfert_dept;
    private EditText transfert_cause;
    private EditText visit_doctor;
    private EditText education_prescribe;
    private CheckBox stop_women;
    private EditText stop_why;

    private TextView tv_right;

    private String ehr_id;
    private String record_code;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return initView(inflater, container, R.layout.fragment_pregnant_add_after42);
    }

    @Override
    protected void initData() {
        initExisting();
        controlBar(R.string.pregnant_after42,R.string.back,true,true);
    }

    private void initExisting() {
        Bundle bundle = getArguments();
        HashMap<String, String> map = (HashMap<String, String>) bundle.getSerializable("map");
        ehr_id = map.get("ehr_id");
        record_code = map.get("record_code");
        String nameStr = map.get("name");
        name.setText(nameStr);
        visit_doctor.setText(GucApplication.visit_doctor);
        String dateStr = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        visit_date.setText(dateStr);
        tv_right.setText(R.string.commint);

    }

    @Override
    protected void setListeners() {
        visit_date.setOnTouchListener(this);
        ll_back.setOnClickListener(this);
        right_layout.setOnClickListener(this);

    }

    @Override
    protected void initWidget(View view) {
        name = (EditText) view.findViewById(R.id.name);
        visit_date = (EditText) view.findViewById(R.id.visit_date);
        systolic_pressure = (EditText) view.findViewById(R.id.systolic_pressure);
        diastolic_pressure = (EditText) view.findViewById(R.id.diastolic_pressure);
        health_flag = (Spinner) view.findViewById(R.id.health_flag);
        health_info = (EditText) view.findViewById(R.id.health_info);
        mental_flag = (Spinner) view.findViewById(R.id.mental_flag);
        mental_skills = (EditText) view.findViewById(R.id.mental_skills);
        breast = (Spinner) view.findViewById(R.id.breast);
        breast_abn = (EditText) view.findViewById(R.id.breast_abn);
        lochia = (Spinner) view.findViewById(R.id.lochia);
        lochia_abn = (EditText) view.findViewById(R.id.lochia_abn);
        uterus = (Spinner) view.findViewById(R.id.uterus);
        uterus_abn = (EditText) view.findViewById(R.id.uterus_abn);
        trauma = (Spinner) view.findViewById(R.id.trauma);
        trauma_abn = (EditText) view.findViewById(R.id.trauma_abn);
        other = (EditText) view.findViewById(R.id.other);
        visit_class = (Spinner) view.findViewById(R.id.visit_class);
        visit_class_abn = (EditText) view.findViewById(R.id.visit_class_abn);
        guidance = (EditText) view.findViewById(R.id.guidance);
        guidance_other = (EditText) view.findViewById(R.id.guidance_other);
        transfert_mark = (Spinner) view.findViewById(R.id.transfert_mark);
        transfert_dept = (EditText) view.findViewById(R.id.transfert_dept);
        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);
        visit_doctor = (EditText) view.findViewById(R.id.visit_doctor);
        education_prescribe = (EditText) view.findViewById(R.id.education_prescribe);
        stop_women = (CheckBox) view.findViewById(R.id.stop_women);
        stop_why = (EditText) view.findViewById(R.id.stop_why);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.right_layout:
                submit();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.visit_date:
                    showDatePicker(visit_date);
                    break;
                case R.id.guidance:
                    multiChoiceDialog(getIntArray(guidance_code),R.array.array_after42_guidance,guidance,guidance_code);
                    break;
                default:
                    break;

            }
        }
        return false;
    }

    private String buildJson() {
        PregnantAddDTO dto = new PregnantAddDTO();
        dto.setEhr_id(ehr_id);
        dto.setDb_id(GucNetEngineClient.DBID);
        dto.setCr_time("");
        dto.setCr_operator(GucApplication.loginUserCode);
        dto.setCr_org_code(GucNetEngineClient.ORG_CODE);
        dto.setCr_org_name(GucApplication.cr_org_name);
        dto.setRecord_code_enregister(record_code);
        dto.setVisit_doctor(StrUtil.getToTrim(visit_doctor));
        dto.setName(StrUtil.getToTrim(name));
        dto.setVisit_date(StrUtil.getToTrim(visit_date));
        dto.setSystolic_pressure(StrUtil.getToTrim(systolic_pressure));
        dto.setDiastolic_pressure(StrUtil.getToTrim(diastolic_pressure));
        dto.setHealth_flag(getSpinnerValue(health_flag));
        dto.setHealth_info(StrUtil.getToTrim(health_info));
        dto.setMental_flag(getSpinnerValue(mental_flag));
        dto.setMental_skills(StrUtil.getToTrim(mental_skills));
        dto.setBreast(getSpinnerValue(breast));
        dto.setBreast_abn(StrUtil.getToTrim(breast_abn));
        dto.setLochia(getSpinnerValue(lochia));
        dto.setLochia_abn(StrUtil.getToTrim(lochia_abn));
        dto.setUterus(getSpinnerValue(uterus));
        dto.setUterus_abn(StrUtil.getToTrim(uterus_abn));
        dto.setTrauma(getSpinnerValue(trauma));
        dto.setTrauma_abn(StrUtil.getToTrim(trauma_abn));
        dto.setOther(StrUtil.getToTrim(other));
        dto.setVisit_class(getSpinnerValue(visit_class));
        dto.setVisit_class_abn(StrUtil.getToTrim(visit_class_abn));
        dto.setGuidance(StrUtil.getToTrim(guidance));
        dto.setGuidance_code(StrUtil.getString(guidance_code));
        dto.setGuidance_other(StrUtil.getToTrim(guidance_other));
        dto.setTransfert_mark(getSpinnerValue(transfert_mark));
        dto.setTransfert_dept(StrUtil.getToTrim(transfert_dept));
        dto.setTransfert_cause(StrUtil.getToTrim(transfert_cause));
        dto.setEducation_prescribe(StrUtil.getToTrim(education_prescribe));
        dto.setStop_women(stop_women.isChecked() ? "1" : "0");
        dto.setStop_why(StrUtil.getToTrim(stop_why));
        return JSON.toJSONString(dto);
    }
    private void submit(){
        String json=buildJson();
        showDialog(R.string.isSubmitting);
        GucNetEngineClient.addMaternalAfter42(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                //{"UploadMaternalAfter42DayResult":{"result":true,"errInfo":null}}
                JSONObject jsonObject=JSON.parseObject(response);
                JSONObject objResult=jsonObject.getJSONObject("UploadMaternalAfter42DayResult");
                String errInfo=objResult.getString("errInfo");
                if(StringUtils.isBlank(errInfo)){
                    showToast(R.string.add_success);
                }else{
                    showToast(errInfo);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });
    }

    public static PregnantAddAfter42Fragment newInstance(HashMap<String, String> map) {

        Bundle args = new Bundle();
        args.putSerializable("map", map);
        PregnantAddAfter42Fragment fragment = new PregnantAddAfter42Fragment();
        fragment.setArguments(args);
        return fragment;
    }

}
