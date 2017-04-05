package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.guc.visit.utils.ViewUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.HashMap;


public class PregnantAdd25Fragment extends BaseFragment implements View.OnTouchListener {
    private EditText name;
    private EditText visit_date;
    private EditText birth_week;
    private EditText birth_week_days;
    private EditText chief_complaint;
    private EditText weight;
    private EditText fundus;
    private EditText abd_cir;
    private Spinner fetal_position;
    private EditText emb_heart_rate_time;
    private EditText systolic_pressure;
    private EditText diastolic_pressure;
    private EditText blood_hb;
    private EditText upro_value;
    private EditText blood_sugar;
    private EditText b_us;
    private EditText other_mtest;
    private Spinner visit_class;
    private EditText visit_class_abn;
    private EditText guidance;
    private StringBuilder guidance_code = new StringBuilder("");
    private Spinner transfert_mark;
    private EditText transfert_dept;
    private EditText transfert_cause;
    private EditText visit_doctor;
    private EditText next_visit_date;
    private EditText education_prescribe;
    private CheckBox stop_women;
    private EditText stop_why;
    private TextView tv_right;

    private String ehr_id;
    private String record_code;
    private String operation;
    private LinearLayout linearLayout;
    private String nameStr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        HashMap<String, String> map = (HashMap<String, String>) bundle.getSerializable("map");
        operation = map.get("operation");
        nameStr = map.get("name");
        if (operation.equals("1")) {
            record_code = map.get("record_code");
            getNetworkData(record_code);
        } else {
            ehr_id = map.get("ehr_id");
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_pregnant25_add);
    }

    @Override
    protected void initData() {
        controlBar(R.string.pregnant_25, R.string.back, true, true);
        if(operation.equals("0")){
            initExisting();
        }else{
            ViewUtils.setAllViewEnable(linearLayout);
        }
    }

    private void initExisting() {
        name.setText(nameStr);
        visit_doctor.setText(GucApplication.visit_doctor);
        String dateStr = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        visit_date.setText(dateStr);
        next_visit_date.setText(dateStr);
        tv_right.setText(R.string.commint);
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        right_layout.setOnClickListener(this);
        next_visit_date.setOnTouchListener(this);
        visit_date.setOnTouchListener(this);
    }

    private void getNetworkData(final String record_code) {
        GucNetEngineClient.getPregnancyFive(record_code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //getMaternalBeforeFiveRecordResult
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getMaternalBeforeFiveRecordResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONObject objInfo = objResult.getJSONObject("recordInfo");
                    PregnantAddDTO dto = JSON.parseObject(objInfo.toJSONString(), PregnantAddDTO.class);
                    updateUI(dto);
                } else {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    private void updateUI(PregnantAddDTO dto) {
        name.setText(nameStr);
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        birth_week.setText(dto.getBirth_week());
        birth_week_days.setText(dto.getBirth_week_days());
        chief_complaint.setText(dto.getChief_complaint());
        weight.setText(dto.getWeight());
        fundus.setText(dto.getFundus());
        abd_cir.setText(dto.getAbd_cir());
        fetal_position.setSelection(convertToInteger(dto.getFetal_position()));
        emb_heart_rate_time.setText(dto.getEmb_heart_rate_time());
        systolic_pressure.setText(dto.getSystolic_pressure());
        diastolic_pressure.setText(dto.getDiastolic_pressure());
        blood_hb.setText(dto.getBlood_hb());
        upro_value.setText(dto.getUpro_value());
        blood_sugar.setText(dto.getBlood_sugar());
        b_us.setText(dto.getB_us());
        other_mtest.setText(dto.getOther_mtest());
        visit_class.setSelection(convertToInteger(dto.getVisit_class()));
        visit_class_abn.setText(dto.getVisit_class_abn());
        guidance.setText(dto.getGuidance());
        transfert_mark.setSelection(convertToInteger(dto.getTransfert_mark()));
        transfert_dept.setText(dto.getTransfert_dept());
        transfert_cause.setText(dto.getTransfert_cause());
        visit_doctor.setText(dto.getVisit_doctor());
        next_visit_date.setText(getFormatDateStr(dto.getNext_visit_date()));
        education_prescribe.setText(dto.getEducation_prescribe());
        stop_women.setChecked(convertToInteger(dto.getStop_women()) == 0 ? false : true);
        stop_why.setText(dto.getStop_why());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.guidance:
                    multiChoiceDialog(getIntArray(guidance_code),R.array.array_guidance, guidance, guidance_code);
                    break;
                case R.id.next_visit_date:
                    showDatePicker(next_visit_date);
                    break;
                case R.id.visit_date:
                    showDatePicker(visit_date);
                    break;
                default:
                    break;
            }
        }
        return false;
    }


    @Override
    protected void initWidget(View view) {
        name = (EditText) view.findViewById(R.id.name);
        visit_date = (EditText) view.findViewById(R.id.visit_date);
        birth_week = (EditText) view.findViewById(R.id.birth_week);
        birth_week_days = (EditText) view.findViewById(R.id.birth_week_days);
        chief_complaint = (EditText) view.findViewById(R.id.chief_complaint);
        weight = (EditText) view.findViewById(R.id.weight);
        fundus = (EditText) view.findViewById(R.id.fundus);
        abd_cir = (EditText) view.findViewById(R.id.abd_cir);
        fetal_position = (Spinner) view.findViewById(R.id.fetal_position);
        emb_heart_rate_time = (EditText) view.findViewById(R.id.emb_heart_rate_time);
        systolic_pressure = (EditText) view.findViewById(R.id.systolic_pressure);
        diastolic_pressure = (EditText) view.findViewById(R.id.diastolic_pressure);
        blood_hb = (EditText) view.findViewById(R.id.blood_hb);
        upro_value = (EditText) view.findViewById(R.id.upro_value);
        blood_sugar = (EditText) view.findViewById(R.id.blood_sugar);
        b_us = (EditText) view.findViewById(R.id.b_us);
        other_mtest = (EditText) view.findViewById(R.id.other_mtest);
        visit_class = (Spinner) view.findViewById(R.id.visit_class);
        visit_class_abn = (EditText) view.findViewById(R.id.visit_class_abn);
        guidance = (EditText) view.findViewById(R.id.guidance);
        transfert_mark = (Spinner) view.findViewById(R.id.transfert_mark);
        transfert_dept = (EditText) view.findViewById(R.id.transfert_dept);
        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);
        visit_doctor = (EditText) view.findViewById(R.id.visit_doctor);
        next_visit_date = (EditText) view.findViewById(R.id.next_visit_date);
        education_prescribe = (EditText) view.findViewById(R.id.education_prescribe);
        stop_women = (CheckBox) view.findViewById(R.id.stop_women);
        stop_why = (EditText) view.findViewById(R.id.stop_why);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);
        linearLayout=(LinearLayout) view.findViewById(R.id.linearLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_layout:
                submit();
                break;
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            default:
                break;
        }

    }

    private String buildJson() {
        PregnantAddDTO dto = new PregnantAddDTO();
        dto.setEhr_id(ehr_id);
        dto.setDb_id(GucNetEngineClient.DBID);
        dto.setCr_time("");
        dto.setCr_operator(GucApplication.loginUserCode);
        dto.setCr_org_code(GucNetEngineClient.ORG_CODE);
        dto.setCr_org_name(GucApplication.cr_org_name);
        dto.setVisit_doctor(StrUtil.getToTrim(visit_doctor));
        dto.setRecord_code_enregister(record_code);
        dto.setName(StrUtil.getToTrim(name));
        dto.setVisit_date(getFormatDateStr(StrUtil.getToTrim(visit_date)));
        dto.setBirth_week(StrUtil.getToTrim(birth_week));
        dto.setBirth_week_days(StrUtil.getToTrim(birth_week_days));
        dto.setChief_complaint(StrUtil.getToTrim(chief_complaint));
        dto.setWeight(StrUtil.getToTrim(weight));
        dto.setFundus(StrUtil.getToTrim(fundus));
        dto.setAbd_cir(StrUtil.getToTrim(abd_cir));
        String[] array_fetal_position = mActivity.getResources().getStringArray(R.array.array_fetal_position);
        int fetal_position_select = fetal_position.getSelectedItemPosition();
        dto.setFetal_position(array_fetal_position[fetal_position_select]);
        dto.setEmb_heart_rate_time(StrUtil.getToTrim(emb_heart_rate_time));
        dto.setSystolic_pressure(StrUtil.getToTrim(systolic_pressure));
        dto.setDiastolic_pressure(StrUtil.getToTrim(diastolic_pressure));
        dto.setBlood_hb(StrUtil.getToTrim(blood_hb));
        dto.setUpro_value(StrUtil.getToTrim(upro_value));
        dto.setBlood_sugar(StrUtil.getToTrim(blood_sugar));
        dto.setB_us(StrUtil.getToTrim(b_us));
        dto.setOther_mtest(StrUtil.getToTrim(other_mtest));
        dto.setVisit_class(getSpinnerValue(visit_class));
        dto.setVisit_class_abn(StrUtil.getToTrim(visit_class_abn));
        dto.setGuidance(StrUtil.getToTrim(guidance));
        dto.setGuidance_code(StrUtil.getString(guidance_code));
        dto.setTransfert_mark(getSpinnerValue(transfert_mark));
        dto.setTransfert_dept(StrUtil.getToTrim(transfert_dept));
        dto.setTransfert_cause(StrUtil.getToTrim(transfert_cause));
        dto.setNext_visit_date(StrUtil.getToTrim(next_visit_date));
        dto.setEducation_prescribe(StrUtil.getToTrim(education_prescribe));
        dto.setStop_women(stop_women.isChecked() ? "1" : "0");
        dto.setStop_why(StrUtil.getToTrim(stop_why));
        return JSON.toJSONString(dto);
    }

    private void submit() {
        String json = buildJson();
        showDialog(R.string.isSubmitting);
        GucNetEngineClient.addMaternalBeforeFive(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                //{"UploadMaternalBeforeFiveResult":{"result":true,"errInfo":null}}
                JSONObject jsonobject = JSON.parseObject(response);
                JSONObject objResult = jsonobject.getJSONObject("UploadMaternalBeforeFiveResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    showToast(R.string.add_success);
                } else {
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

    public static PregnantAdd25Fragment newInstance(HashMap<String, String> map) {
        Bundle args = new Bundle();
        args.putSerializable("map", map);
        PregnantAdd25Fragment fragment = new PregnantAdd25Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
