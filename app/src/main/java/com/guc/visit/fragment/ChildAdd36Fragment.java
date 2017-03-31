package com.guc.visit.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.guc.visit.domain.ChildVisitAdd;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ViewUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.HashMap;

public class ChildAdd36Fragment extends BaseFragment implements View.OnTouchListener {
    private EditText visit_doctor;
    private EditText visit_date;
    private Spinner current_agey;
    private EditText weight;
    private Spinner weight_grade;
    private EditText height;
    private Spinner height_grade;
    private EditText physique;
    private StringBuilder physique_mark = new StringBuilder("");
    private EditText ue_myopia_left;
    private EditText ue_myopia_right;
    private EditText tooth;
    private EditText tooth_decayed;
    private Spinner ear_result;
    private EditText ear_result_abn;

    private Spinner complexion;
    private EditText complexion_abn;
    private Spinner gait;
    private EditText gait_abn;
    private Spinner eye;
    private EditText eye_abn;
    private Spinner ear;
    private EditText ear_abn;
    private Spinner heart_lung;
    private EditText heart_lung_abn;
    private Spinner liver_spleen;
    private EditText liver_spleen_sbn;
    private Spinner growth_action;
    private EditText growth_action_abn;

    private Spinner growth_gam;
    private EditText growth_gam_abn;

    private EditText blood_hb;
    private Spinner irritability;
    private EditText irritability_con;
    private EditText other;

    private EditText pneumonia_memo;
    private StringBuilder pneumonia_mark = new StringBuilder("");

    private EditText is_pneumonia_sum;
    private EditText is_diarrheaih_sum;

    private EditText is_traumaih_sum;
    private EditText is_other_con;

    private Spinner transfert;
    private EditText transfert_cause;
    private EditText transfert_org_name;

    private EditText guidance_con;
    private StringBuilder guidance_mark = new StringBuilder("");

    private EditText guidance_other_exp;
    private EditText next_visit_date;

    private EditText education_prescribe;

    private TextView tv_right;

    private String ehr_id;
    private String birth_date;
    private String current_date;

    private String name;
    private String operation;

    private LinearLayout linearLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        HashMap<String, String> map = (HashMap<String, String>) bundle.getSerializable("map");
        name = map.get("name");
        operation = map.get("operation");
        if (operation.equals("1")) {
            String record_code = map.get("record_code");
            getNetworkData(record_code);
        } else {
            ehr_id = map.get("ehr_id");
            birth_date = map.get("birth_date");
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_child_add36);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.physique:
                    multiChoiceDialog(R.array.array_physique, physique, physique_mark);
                    break;
                case R.id.pneumonia_memo:
                    multiChoiceDialog(R.array.array_pneumonia_memo, pneumonia_memo, pneumonia_mark);
                    break;
                case R.id.guidance_con:
                    multiChoiceDialog(R.array.array_guidance_con, guidance_con, guidance_mark);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    @Override
    protected void initData() {
        controlBar(R.string.child_three, R.string.back, true, true);
        if (operation.equals("0")) {
            initExisting();
        }else{
            ViewUtils.setAllViewEnable(linearLayout);
        }
    }

    private void getNetworkData(String record_code) {
        GucNetEngineClient.getChildThree(record_code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //getNewBronThreeYearRecordResult
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getNewBronThreeYearRecordResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONObject objInfo = objResult.getJSONObject("recordInfo");
                    ChildVisitAdd dto = JSON.parseObject(objInfo.toJSONString(), ChildVisitAdd.class);
                    updateUI(dto);
                } else {
                    showToast(errInfo);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    private void updateUI(ChildVisitAdd dto) {
        visit_doctor.setText(GucApplication.visit_doctor);
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        current_agey.setSelection(convertToInteger(dto.getCurrent_agey()));
        weight.setText(dto.getWeight());
        weight_grade.setSelection(convertToInteger(dto.getWeight_grade()));
        height.setText(dto.getHeight());
        height_grade.setSelection(convertToInteger(dto.getHeight_grade()));

        physique.setSelection(convertToInteger(dto.getPhysique()));
        ue_myopia_left.setText(dto.getUe_myopia_left());
        ue_myopia_right.setText(dto.getUe_myopia_right());
        tooth.setText(dto.getTooth());
        tooth_decayed.setText(dto.getTooth_decayed());
        ear_result.setSelection(convertToInteger(dto.getEar_result()));
        ear_result_abn.setText(dto.getEar_result());
        complexion.setSelection(convertToInteger(dto.getComplexion()));
        complexion_abn.setText(dto.getComplexion_abn());
        gait.setSelection(convertToInteger(dto.getGait()));
        gait_abn.setText(dto.getGait_abn());
        eye.setSelection(convertToInteger(dto.getEye()));
        eye_abn.setText(dto.getEye_abn());
        ear.setSelection(convertToInteger(dto.getEar()));
        ear_abn.setText(dto.getEar_abn());

        heart_lung.setSelection(convertToInteger(dto.getHeart_lung()));
        heart_lung_abn.setText(dto.getHeart_lung_abn());

        liver_spleen.setSelection(convertToInteger(dto.getLiver_spleen()));
        liver_spleen_sbn.setText(dto.getLiver_spleen_sbn());
        growth_action.setSelection(convertToInteger(dto.getGrowth_action()));
        growth_action_abn.setText(dto.getGrowth_action_abn());

        blood_hb.setText(dto.getBlood_hb());
        irritability.setSelection(convertToInteger(dto.getIrritability()));
        irritability_con.setText(dto.getIrritability_con());
        other.setText(dto.getOther());
        pneumonia_memo.setText(dto.getPneumonia_memo());
        is_pneumonia_sum.setText(dto.getIs_pneumonia_sum());
        is_diarrheaih_sum.setText(dto.getIs_diarrheaih_sum());
        is_traumaih_sum.setText(dto.getIs_traumaih_sum());
        is_other_con.setText(dto.getIs_other_con());

        transfert.setSelection(convertToInteger(dto.getTransfert()));
        transfert_cause.setText(dto.getTransfert_cause());
        transfert_org_name.setText(dto.getTransfert_org_name());
        guidance_con.setText(dto.getGuidance_con());
        next_visit_date.setText(getFormatDateStr(dto.getNext_visit_date()));
        guidance_other_exp.setText(dto.getGuidance_other_exp());
        education_prescribe.setText(dto.getEducation_prescribe());
    }

    private void initExisting() {
        visit_doctor.setText(GucApplication.visit_doctor);
        current_date = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        visit_date.setText(current_date);
        next_visit_date.setText(current_date);
        tv_right.setText(R.string.commint);
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        right_layout.setOnClickListener(this);
        next_visit_date.setOnClickListener(this);
        visit_date.setOnClickListener(this);

        physique.setOnTouchListener(this);
        pneumonia_memo.setOnTouchListener(this);
        guidance_con.setOnTouchListener(this);
    }

    @Override
    protected void initWidget(View view) {
        visit_doctor = (EditText) view.findViewById(R.id.visit_doctor);
        visit_date = (EditText) view.findViewById(R.id.visit_date);
        current_agey = (Spinner) view.findViewById(R.id.current_agey);
        weight = (EditText) view.findViewById(R.id.weight);
        weight_grade = (Spinner) view.findViewById(R.id.weight_grade);
        height = (EditText) view.findViewById(R.id.height);
        height_grade = (Spinner) view.findViewById(R.id.height_grade);
        physique = (EditText) view.findViewById(R.id.physique);
        ue_myopia_left = (EditText) view.findViewById(R.id.ue_myopia_left);
        ue_myopia_right = (EditText) view.findViewById(R.id.ue_myopia_right);
        tooth = (EditText) view.findViewById(R.id.tooth);
        tooth_decayed = (EditText) view.findViewById(R.id.tooth_decayed);
        ear_result = (Spinner) view.findViewById(R.id.ear_result);
        ear_result_abn = (EditText) view.findViewById(R.id.ear_result_abn);

        complexion = (Spinner) view.findViewById(R.id.complexion);
        complexion_abn = (EditText) view.findViewById(R.id.complexion_abn);
        gait = (Spinner) view.findViewById(R.id.gait);
        gait_abn = (EditText) view.findViewById(R.id.gait_abn);
        eye = (Spinner) view.findViewById(R.id.eye);
        eye_abn = (EditText) view.findViewById(R.id.eye_abn);
        ear = (Spinner) view.findViewById(R.id.ear);
        ear_abn = (EditText) view.findViewById(R.id.ear_abn);
        heart_lung = (Spinner) view.findViewById(R.id.heart_lung);
        heart_lung_abn = (EditText) view.findViewById(R.id.heart_lung_abn);
        liver_spleen = (Spinner) view.findViewById(R.id.liver_spleen);
        liver_spleen_sbn = (EditText) view.findViewById(R.id.liver_spleen_sbn);
        growth_action = (Spinner) view.findViewById(R.id.growth_action);
        growth_action_abn = (EditText) view.findViewById(R.id.growth_action_abn);

        growth_gam = (Spinner) view.findViewById(R.id.growth_gam);
        growth_gam_abn = (EditText) view.findViewById(R.id.growth_gam_abn);

        blood_hb = (EditText) view.findViewById(R.id.blood_hb);
        irritability = (Spinner) view.findViewById(R.id.irritability);
        irritability_con = (EditText) view.findViewById(R.id.irritability_con);
        other = (EditText) view.findViewById(R.id.other);

        pneumonia_memo = (EditText) view.findViewById(R.id.pneumonia_memo);

        is_pneumonia_sum = (EditText) view.findViewById(R.id.is_pneumonia_sum);
        is_diarrheaih_sum = (EditText) view.findViewById(R.id.is_diarrheaih_sum);

        is_traumaih_sum = (EditText) view.findViewById(R.id.is_traumaih_sum);
        is_other_con = (EditText) view.findViewById(R.id.is_other_con);

        transfert = (Spinner) view.findViewById(R.id.transfert);
        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);
        transfert_org_name = (EditText) view.findViewById(R.id.transfert_org_name);

        guidance_con = (EditText) view.findViewById(R.id.guidance_con);

        guidance_other_exp = (EditText) view.findViewById(R.id.guidance_other_exp);
        next_visit_date = (EditText) view.findViewById(R.id.next_visit_date);

        education_prescribe = (EditText) view.findViewById(R.id.education_prescribe);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
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

    private void submit() {
        String json = buildJson();
        GucNetEngineClient.addChildThreeYear(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //{"UploadNewBronThreeYearResult":{"result":true,"errInfo":null}}
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("UploadNewBronThreeYearResult");
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
            }
        });

    }

    private String buildJson() {
        ChildVisitAdd dto = new ChildVisitAdd();
        dto.setEhr_id(ehr_id);
        dto.setDb_id(GucNetEngineClient.DBID);
        dto.setCr_time("");
        dto.setCr_operator(GucApplication.loginUserCode);
        dto.setCr_org_code(GucNetEngineClient.ORG_CODE);
        dto.setCr_org_name(GucApplication.cr_org_name);
        dto.setVisit_doctor(getToTrim(visit_doctor));
        dto.setName(name);
        dto.setVisit_date(getToTrim(visit_date));
        dto.setCurrent_agey(getSpinnerValue(current_agey));
        dto.setWeight(getToTrim(weight));
        dto.setWeight_grade(getSpinnerValue(weight_grade));
        dto.setHeight(getToTrim(height));
        dto.setHeight_grade(getSpinnerValue(height_grade));
        dto.setPhysique(getToTrim(physique));
        dto.setPhysique_mark(StrUtil.getString(physique_mark));
        dto.setUe_myopia_left(getToTrim(ue_myopia_left));
        dto.setUe_myopia_right(getToTrim(ue_myopia_right));
        dto.setTooth(getToTrim(tooth));
        dto.setTooth_decayed(getToTrim(tooth_decayed));
        dto.setEar_result(getSpinnerValue(ear_result));
        dto.setEar_result_abn(getToTrim(ear_result_abn));
        dto.setComplexion(getSpinnerValue(complexion));
        dto.setComplexion_abn(getToTrim(complexion_abn));
        dto.setGait(getSpinnerValue(gait));
        dto.setGait_abn(getToTrim(gait_abn));
        dto.setEye(getSpinnerValue(eye));
        dto.setEye_abn(getToTrim(eye_abn));
        dto.setEar(getSpinnerValue(ear));
        dto.setEar_abn(getToTrim(ear_abn));
        dto.setHeart_lung(getSpinnerValue(heart_lung));
        dto.setHeart_lung_abn(getToTrim(heart_lung_abn));
        dto.setLiver_spleen(getSpinnerValue(liver_spleen));
        dto.setLiver_spleen_sbn(getToTrim(liver_spleen_sbn));
        dto.setGrowth_action(getSpinnerValue(growth_action));
        dto.setGrowth_action_abn(getToTrim(growth_action_abn));
        dto.setGrowth_gam(getSpinnerValue(growth_gam));
        dto.setGrowth_gam_abn(getToTrim(growth_gam_abn));
        dto.setBlood_hb(getToTrim(blood_hb));
        dto.setIrritability(getSpinnerValue(irritability));
        dto.setIrritability_con(getToTrim(irritability_con));
        dto.setOther(getToTrim(other));
        dto.setPneumonia_memo(getToTrim(pneumonia_memo));
        dto.setPneumonia_mark(StrUtil.getString(pneumonia_mark));
        dto.setIs_pneumonia_sum(getToTrim(is_pneumonia_sum));
        dto.setIs_diarrheaih_sum(getToTrim(is_diarrheaih_sum));
        dto.setIs_traumaih_sum(getToTrim(is_traumaih_sum));
        dto.setIs_other_con(getToTrim(is_other_con));
        dto.setTransfert(getSpinnerValue(transfert));
        dto.setTransfert_org_name(getToTrim(transfert_org_name));
        dto.setTransfert_cause(getToTrim(transfert_cause));
        dto.setGuidance_con(getToTrim(guidance_con));
        dto.setGuidance_mark(StrUtil.getString(guidance_mark));
        dto.setGuidance_other_exp(getToTrim(guidance_other_exp));
        dto.setNext_visit_date(getToTrim(next_visit_date));
        dto.setEducation_prescribe(getToTrim(education_prescribe));
        return JSON.toJSONString(dto);
    }

    public static ChildAdd36Fragment newInstance(HashMap<String, String> map) {

        Bundle args = new Bundle();
        args.putSerializable("map", map);
        ChildAdd36Fragment fragment = new ChildAdd36Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
