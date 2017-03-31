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
import com.guc.visit.utils.DateUtils;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ViewUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.HashMap;

public class ChildAdd12Fragment extends BaseFragment implements View.OnTouchListener {
    private EditText current_doctor;
    private EditText name;
    private EditText visit_date;
    private Spinner current_agem;
    private EditText weight;
    private Spinner weight_grade;
    private EditText height;
    private Spinner height_grade;
    private Spinner complexion;
    private EditText complexion_other;
    private Spinner skin;
    private EditText skin_abn;
    private Spinner fontanel_close;
    private EditText fontanel_x;
    private EditText fontanel_y;

    private Spinner eye;
    private EditText eye_abn;
    private Spinner ear;
    private EditText ear_abn;

    private Spinner ear_result;
    private EditText ear_result_abn;
    private EditText tooth_start_num;
    private EditText tooth_decayed;
    private Spinner heart_lung;
    private EditText heart_lung_abn;

    private Spinner abdomen;
    private EditText abdomen_abn;

    private Spinner limbs;
    private EditText limbs_abn;

    private Spinner gait;
    private EditText gait_abn;

    private EditText rachitis_sign;
    private StringBuilder rachitis_sign_mark = new StringBuilder("");

    private EditText blood_hb;
    private EditText outdoor_activities;

    private EditText vitamin_amount;

    private Spinner growth;
    private EditText growth_con;

    private Spinner between_disease;
    private EditText between_disease_con;
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
    private String nameStr;
    private String birth_date;
    private String current_date;
    private String operation;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_child_add12);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        HashMap<String, String> map = (HashMap<String, String>) bundle.getSerializable("map");
        nameStr = map.get("name");
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

    private void getNetworkData(final String record_code) {
        GucNetEngineClient.getChildOneTwo(record_code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getNewBronOneTwoYearRecordResult");
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
        current_doctor.setText(GucApplication.visit_doctor);
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        name.setText(nameStr);
        current_agem.setSelection(convertToInteger(dto.getCurrent_agem()));
        weight.setText(dto.getWeight());
        weight_grade.setSelection(convertToInteger(dto.getWeight_grade()));
        height.setText(dto.getHeight());
        height_grade.setSelection(convertToInteger(dto.getHeight_grade()));

        complexion.setSelection(convertToInteger(dto.getComplexion()));
        complexion_other.setText(dto.getComplexion_other());
        skin.setSelection(convertToInteger(dto.getSkin()));
        skin_abn.setText(dto.getSkin_abn());
        fontanel_x.setText(dto.getFontanel_x());
        fontanel_y.setText(dto.getFontanel_y());
        fontanel_close.setSelection(convertToInteger(dto.getFontanel_close()));

        eye.setSelection(convertToInteger(dto.getEye()));
        eye_abn.setText(dto.getEye_abn());
        ear.setSelection(convertToInteger(dto.getEar()));
        ear_abn.setText(dto.getEar_abn());
        ear_result.setSelection(convertToInteger(dto.getEar_result()));
        ear_result_abn.setText(dto.getEar_result());
        tooth_start_num.setText(dto.getTooth_start_num());
        tooth_decayed.setText(dto.getTooth_decayed());
        heart_lung.setSelection(convertToInteger(dto.getHeart_lung()));
        heart_lung_abn.setText(dto.getHeart_lung_abn());


        abdomen.setSelection(convertToInteger(dto.getAbdomen()));
        abdomen_abn.setText(dto.getAbdomen());



        limbs.setSelection(convertToInteger(dto.getLimbs()));
        limbs_abn.setText(dto.getLimbs_abn());
        gait.setSelection(convertToInteger(dto.getGait()));
        gait_abn.setText(dto.getGait_abn());

        rachitis_sign.setText(dto.getRachitis_sign());
        blood_hb.setText(dto.getBlood_hb());
        outdoor_activities.setText(dto.getOutdoor_activities());
        vitamin_amount.setText(dto.getVitamin_amount());
        growth.setSelection(convertToInteger(dto.getGrowth()));
        growth_con.setText(dto.getGrowth_con());
        between_disease.setSelection(convertToInteger(dto.getBetween_disease()));
        between_disease_con.setText(dto.getBetween_disease_con());
        transfert.setSelection(convertToInteger(dto.getTransfert()));
        transfert_cause.setText(dto.getTransfert_cause());
        transfert_org_name.setText(dto.getTransfert_org_name());
        guidance_con.setText(dto.getGuidance_con());
        next_visit_date.setText(getFormatDateStr(dto.getNext_visit_date()));
        guidance_other_exp.setText(dto.getGuidance_other_exp());
        education_prescribe.setText(dto.getEducation_prescribe());
    }

    @Override
    protected void initData() {
        controlBar(R.string.child_2, R.string.back, true, true);
        if (operation.equals("0")) {
            initExisting();
        }else{
            ViewUtils.setAllViewEnable(linearLayout);
        }
    }

    private void initExisting() {
        current_doctor.setText(GucApplication.visit_doctor);
        current_date = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        visit_date.setText(current_date);
        name.setText(nameStr);
        next_visit_date.setText(current_date);
        tv_right.setText(R.string.commint);
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        right_layout.setOnClickListener(this);

        rachitis_sign.setOnTouchListener(this);
        guidance_con.setOnTouchListener(this);
        visit_date.setOnTouchListener(this);
        next_visit_date.setOnTouchListener(this);

    }

    @Override
    protected void initWidget(View view) {
        current_doctor = (EditText) view.findViewById(R.id.current_doctor);
        name = (EditText) view.findViewById(R.id.name);
        visit_date = (EditText) view.findViewById(R.id.visit_date);
        current_agem = (Spinner) view.findViewById(R.id.current_agem);
        weight = (EditText) view.findViewById(R.id.weight);
        weight_grade = (Spinner) view.findViewById(R.id.weight_grade);
        height = (EditText) view.findViewById(R.id.height);
        height_grade = (Spinner) view.findViewById(R.id.height_grade);
        complexion = (Spinner) view.findViewById(R.id.complexion);
        complexion_other = (EditText) view.findViewById(R.id.complexion_other);
        skin = (Spinner) view.findViewById(R.id.skin);
        skin_abn = (EditText) view.findViewById(R.id.skin_abn);


        fontanel_x = (EditText) view.findViewById(R.id.fontanel_x);
        fontanel_y = (EditText) view.findViewById(R.id.fontanel_y);

        fontanel_close = (Spinner) view.findViewById(R.id.fontanel_close);

        eye = (Spinner) view.findViewById(R.id.eye);
        eye_abn = (EditText) view.findViewById(R.id.eye_abn);
        ear = (Spinner) view.findViewById(R.id.ear);
        ear_abn = (EditText) view.findViewById(R.id.ear_abn);

        ear_result = (Spinner) view.findViewById(R.id.ear_result);
        ear_result_abn = (EditText) view.findViewById(R.id.ear_result_abn);

        tooth_start_num = (EditText) view.findViewById(R.id.tooth_start_num);
        tooth_decayed = (EditText) view.findViewById(R.id.tooth_decayed);
        heart_lung = (Spinner) view.findViewById(R.id.heart_lung);
        heart_lung_abn = (EditText) view.findViewById(R.id.heart_lung_abn);

        abdomen = (Spinner) view.findViewById(R.id.abdomen);
        abdomen_abn = (EditText) view.findViewById(R.id.abdomen_abn);


        limbs = (Spinner) view.findViewById(R.id.limbs);
        limbs_abn = (EditText) view.findViewById(R.id.limbs_abn);

        gait = (Spinner) view.findViewById(R.id.gait);
        gait_abn = (EditText) view.findViewById(R.id.gait_abn);

        rachitis_sign = (EditText) view.findViewById(R.id.rachitis_sign);


        blood_hb = (EditText) view.findViewById(R.id.blood_hb);
        outdoor_activities = (EditText) view.findViewById(R.id.outdoor_activities);

        vitamin_amount = (EditText) view.findViewById(R.id.vitamin_amount);

        growth = (Spinner) view.findViewById(R.id.growth);
        growth_con = (EditText) view.findViewById(R.id.growth_con);

        between_disease = (Spinner) view.findViewById(R.id.between_disease);
        between_disease_con = (EditText) view.findViewById(R.id.between_disease_con);

        transfert = (Spinner) view.findViewById(R.id.transfert);
        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);
        transfert_org_name = (EditText) view.findViewById(R.id.transfert_org_name);
        guidance_con = (EditText) view.findViewById(R.id.guidance_con);
        guidance_other_exp = (EditText) view.findViewById(R.id.guidance_other_exp);
        next_visit_date = (EditText) view.findViewById(R.id.next_visit_date);

        education_prescribe = (EditText) view.findViewById(R.id.education_prescribe);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);
        linearLayout=(LinearLayout)view.findViewById(R.id.linearLayout);
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
        ChildVisitAdd dto = new ChildVisitAdd();
        dto.setEhr_id(ehr_id);
        dto.setDb_id(GucNetEngineClient.DBID);
        dto.setCr_time("");
        dto.setCr_operator(GucApplication.loginUserCode);
        dto.setCr_org_code(GucNetEngineClient.ORG_CODE);
        dto.setCr_org_name(GucApplication.cr_org_name);
        dto.setCurrent_doctor(StrUtil.getToTrim(current_doctor));
        dto.setName(getToTrim(name));
        dto.setVisit_date(StrUtil.getToTrim(visit_date));
        dto.setAuto_agem(DateUtils.getBetweenMonth(birth_date, current_date) + "");
        dto.setCurrent_agem(getSpinnerValue(current_agem));
        dto.setWeight(getToTrim(weight));
        dto.setWeight_grade(getSpinnerValue(weight_grade));
        dto.setHeight(getToTrim(height));
        dto.setHeight_grade(getSpinnerValue(height_grade));
        dto.setComplexion(getSpinnerValue(complexion));
        dto.setComplexion_other(getToTrim(complexion_other));
        dto.setSkin(getSpinnerValue(skin));
        dto.setSkin_abn(getToTrim(skin_abn));
        dto.setFontanel_x(getToTrim(fontanel_x));
        dto.setFontanel_y(getToTrim(fontanel_y));
        dto.setFontanel_close(getSpinnerValue(fontanel_close));
        dto.setEye(getSpinnerValue(eye));
        dto.setEye_abn(getToTrim(eye_abn));
        dto.setEar(getSpinnerValue(ear));
        dto.setEar_abn(getToTrim(ear_abn));
        dto.setTooth_start_num(getToTrim(tooth_start_num));
        dto.setTooth_decayed(getToTrim(tooth_decayed));
        dto.setEar_result(getSpinnerValue(ear_result));
        dto.setEar_result_abn(getToTrim(ear_result_abn));
        dto.setHeart_lung(getSpinnerValue(heart_lung));
        dto.setHeart_lung_abn(getToTrim(heart_lung_abn));
        dto.setAbdomen(getSpinnerValue(abdomen));
        dto.setAbdomen_abn(getToTrim(abdomen_abn));
        dto.setLimbs(getSpinnerValue(limbs));
        dto.setLimbs_abn(getToTrim(limbs_abn));
        dto.setGait(getSpinnerValue(gait));
        dto.setGait_abn(getToTrim(gait_abn));
        dto.setRachitis_sign(getToTrim(rachitis_sign));
        dto.setRachitis_sign_mark(StrUtil.getString(rachitis_sign_mark));
        dto.setBlood_hb(getToTrim(blood_hb));
        dto.setOutdoor_activities(getToTrim(outdoor_activities));
        dto.setVitamin_amount(getToTrim(vitamin_amount));
        dto.setGrowth(getSpinnerValue(growth));
        dto.setGrowth_con(getToTrim(growth_con));
        dto.setBetween_disease(getSpinnerValue(between_disease));
        dto.setBetween_disease_con(getToTrim(between_disease_con));
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

    private void submit() {
        showDialog(R.string.isSubmitting);
        String json = buildJson();
        GucNetEngineClient.addChildTwoYear(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                //{"UploadNewBronOneTwoYearResult":{"result":true,"errInfo":null}}
                JSONObject jsonOject = JSON.parseObject(response);
                JSONObject objResult = jsonOject.getJSONObject("UploadNewBronOneTwoYearResult");
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


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.rachitis_sign:
                    //两位数
                    multiChoiceDialog(R.array.array_rachitis_sign, rachitis_sign, rachitis_sign_mark);
                    break;
                case R.id.guidance_con:
                    multiChoiceDialog(R.array.array_guidance_con, guidance_con, guidance_mark);
                    break;
                case R.id.visit_date:
                    showDatePicker(visit_date);
                    break;
                case R.id.next_visit_date:
                    showDatePicker(next_visit_date);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    public static ChildAdd12Fragment newInstance(HashMap<String, String> map) {

        Bundle args = new Bundle();
        args.putSerializable("map", map);
        ChildAdd12Fragment fragment = new ChildAdd12Fragment();
        fragment.setArguments(args);
        return fragment;
    }

}
