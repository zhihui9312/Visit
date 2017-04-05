package com.guc.visit.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.MentalBaseDTO;
import com.guc.visit.domain.MentalDTO;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ToastUtils;
import com.guc.visit.utils.ViewUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;


public class MentalAddFragment extends BaseFragment implements View.OnTouchListener {
    private TextView name;
    private TextView visit_date;
    /**
     * 症状
     */
    private TextView symptom;
    private String symptom_code = "";
    /**
     * 其它症状
     */
    private EditText symptom_other;
    /**
     * 危险性、自知力、睡眠状况、饮食情况、个人生活、家务劳动、劳动工作、学习能力、社会交往
     */
    private Spinner danger_grade;
    private Spinner insight_appraise;
    private Spinner sleep_type;
    private Spinner diet_type;
    private Spinner living;
    private Spinner housework;
    private Spinner work;
    private Spinner study;
    private Spinner sociality;
    /**
     * 轻度滋事
     */
    private EditText affray;
    private EditText cause_trouble;
    private EditText disaster;
    private EditText autolesion;
    private EditText attempted_suicide;
    private Spinner captivity;
    private Spinner in_hospital;
    /**
     * 末次出院时间
     */
    private TextView leave_hospital_time;
    private Spinner laboratory_abn;
    private EditText laboratory_examination;
    private Spinner rule_dose;
    private Spinner drug_side_effect;
    private EditText drug_side_effects;
    private Spinner anamnesis_tr_type;
    private Spinner transfert;
    private EditText transfert_dept;
    /**
     * 药物1名称、每次量、服药量、量的单位
     */
    private EditText drug1_name;
    private EditText drug1_fn;
    private EditText drug1_dosage_one;
    private Spinner drug1_dosage_unit;
    /**
     * 药物2名称、每次量、服药量、量的单位
     */
    private EditText drug2_name;
    private EditText drug2_fn;
    private EditText drug2_dosage_one;
    private Spinner drug2_dosage_unit;
    /**
     * 药物3名称、每次量、服药量、量的单位
     */
    private EditText drug3_name;
    private EditText drug3_fn;
    private EditText drug3_dosage_one;
    private Spinner drug3_dosage_unit;

    private TextView health_guide_adv;
    private TextView next_visit_date;
    private Spinner visit_class;
    private TextView visit_doctor;
    private Spinner cm_guidance;
    private EditText cm_guidance_str;
    private MentalBaseDTO dto;
    private PopupWindow popupWindow;
    public final static String[] UNIT_TYPE = {"", "mg", "mm", "片", "粒"};
    public final static String[] SYMPTOM_ITEM = {"幻觉", "交流困难", "猜疑",
            "喜怒无常", "行为怪异", "兴奋话多", "伤人毁物", "悲观厌世", "无故外走", "自语自笑", "孤僻懒散"};
    private boolean symptom_flag[];

    public final static String[] HEALTH_GUIDE_ADV_ITEM = {"心里康复", "生活劳动力",
            "职业训练", "学习能力", "社会交往", "其他"};
    public final static int[] HEALTH_GUIDE_ADV_ID = {1, 2, 3, 4, 5, 9};
    private boolean health_flag[];
    private String health_code = "";
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_mental_add);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        if (type == 1) {
            String record_code = bundle.getString("record_code");
            String name_str = bundle.getString("name");
            name.setText(name_str);
            getMental(record_code);
            controlBar(name_str, R.string.back, true, false);
            ViewUtils.setAllViewEnable(linearLayout);
        } else {
            dto = (MentalBaseDTO) bundle.getSerializable("data");
            if (dto != null) {
                controlBar(dto.getName(), R.string.back, true, true);
                name.setText(dto.getName());
                visit_doctor.setText(GucApplication.visit_doctor);
            }
            Calendar calendar = Calendar.getInstance();
            String cur_str = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            visit_date.setText(cur_str);
            calendar.set(Calendar.DAY_OF_MONTH, 90);
            String next_str = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            next_visit_date.setText(next_str);
            visit_doctor.setText(GucApplication.visit_doctor);
            controlBar(R.string.add_visit, R.string.back, true, true);
        }
    }

    @Override
    protected void setListeners() {
        right_layout.setOnClickListener(this);
        ll_back.setOnClickListener(this);
        visit_date.setOnTouchListener(this);
        next_visit_date.setOnTouchListener(this);
        symptom.setOnTouchListener(this);
        leave_hospital_time.setOnTouchListener(this);
        health_guide_adv.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.symptom:
                    selectionSymptom();
                    break;
                case R.id.next_visit_date:
                    showDatePicker(next_visit_date);
                    break;
                case R.id.visit_date:
                    showDatePicker(visit_date);
                    break;
                case R.id.leave_hospital_time:
                    showDatePicker(leave_hospital_time);
                    break;
                case R.id.health_guide_adv:
                    selectionHealth();
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    @Override
    protected void initWidget(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        name = (TextView) view.findViewById(R.id.name);
        visit_date = (TextView) view.findViewById(R.id.visit_date);
        symptom = (TextView) view.findViewById(R.id.symptom);
        symptom_other = (EditText) view.findViewById(R.id.symptom_other);
        danger_grade = (Spinner) view.findViewById(R.id.danger_grade);
        insight_appraise = (Spinner) view.findViewById(R.id.insight_appraise);
        sleep_type = (Spinner) view.findViewById(R.id.sleep_type);
        diet_type = (Spinner) view.findViewById(R.id.diet_type);
        living = (Spinner) view.findViewById(R.id.living);
        housework = (Spinner) view.findViewById(R.id.housework);
        work = (Spinner) view.findViewById(R.id.work);
        study = (Spinner) view.findViewById(R.id.study);
        sociality = (Spinner) view.findViewById(R.id.sociality);

        affray = (EditText) view.findViewById(R.id.affray);
        cause_trouble = (EditText) view.findViewById(R.id.cause_trouble);
        disaster = (EditText) view.findViewById(R.id.disaster);
        autolesion = (EditText) view.findViewById(R.id.autolesion);
        attempted_suicide = (EditText) view.findViewById(R.id.attempted_suicide);
        captivity = (Spinner) view.findViewById(R.id.captivity);
        in_hospital = (Spinner) view.findViewById(R.id.in_hospital);

        leave_hospital_time = (TextView) view.findViewById(R.id.leave_hospital_time);
        laboratory_abn = (Spinner) view.findViewById(R.id.laboratory_abn);
        laboratory_examination = (EditText) view.findViewById(R.id.laboratory_examination);
        rule_dose = (Spinner) view.findViewById(R.id.rule_dose);
        drug_side_effect = (Spinner) view.findViewById(R.id.drug_side_effect);
        drug_side_effects = (EditText) view.findViewById(R.id.drug_side_effects);
        anamnesis_tr_type = (Spinner) view.findViewById(R.id.anamnesis_tr_type);
        transfert = (Spinner) view.findViewById(R.id.transfert);
        transfert_dept = (EditText) view.findViewById(R.id.transfert_dept);

        drug1_name = (EditText) view.findViewById(R.id.drug1_name);
        drug1_fn = (EditText) view.findViewById(R.id.drug1_fn);
        drug1_dosage_one = (EditText) view.findViewById(R.id.drug1_dosage_one);
        drug1_dosage_unit = (Spinner) view.findViewById(R.id.drug1_dosage_unit);

        drug2_name = (EditText) view.findViewById(R.id.drug2_name);
        drug2_fn = (EditText) view.findViewById(R.id.drug2_fn);
        drug2_dosage_one = (EditText) view.findViewById(R.id.drug2_dosage_one);
        drug2_dosage_unit = (Spinner) view.findViewById(R.id.drug2_dosage_unit);

        drug3_name = (EditText) view.findViewById(R.id.drug3_name);
        drug3_fn = (EditText) view.findViewById(R.id.drug3_fn);
        drug3_dosage_one = (EditText) view.findViewById(R.id.drug3_dosage_one);
        drug3_dosage_unit = (Spinner) view.findViewById(R.id.drug3_dosage_unit);

        health_guide_adv = (TextView) view.findViewById(R.id.health_guide_adv);
        next_visit_date = (TextView) view.findViewById(R.id.next_visit_date);
        visit_class = (Spinner) view.findViewById(R.id.visit_class);
        visit_doctor = (TextView) view.findViewById(R.id.visit_doctor);
        cm_guidance = (Spinner) view.findViewById(R.id.cm_guidance);
        cm_guidance_str = (EditText) view.findViewById(R.id.cm_guidance_str);

        ImageView iv_add = (ImageView) view.findViewById(R.id.iv_add);
        iv_add.setImageResource(R.mipmap.ic_menu);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.symptom:
                selectionSymptom();
                break;
            case R.id.right_layout:
                popWindows(right_layout);
                break;
            case R.id.tv_previous:
                getLastInfo(dto.getEhr_id());
                popupWindow.dismiss();
                break;
            case R.id.tv_submit:
                addMental(buildJson());
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    private void selectionSymptom() {
        symptom_flag = new boolean[SYMPTOM_ITEM.length];
        symptom_code = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMultiChoiceItems(SYMPTOM_ITEM, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                symptom_flag[which] = isChecked;
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int length = symptom_flag.length;
                String symptom_str = "";
                for (int i = 0; i < length; i++) {
                    if (symptom_flag[i]) {
                        symptom_str += SYMPTOM_ITEM[i] + ",";
                        symptom_code += (i + 1) + ",";
                    }
                }
                symptom_str = symptom_str.substring(0, symptom_str.length() - 1);
                symptom_code = symptom_code.substring(0, symptom_code.length() - 1);
                symptom.setText(symptom_str);
            }
        });
        builder.setTitle("选择症状");
        builder.show();
    }

    private void selectionHealth() {
        health_flag = new boolean[HEALTH_GUIDE_ADV_ITEM.length];
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMultiChoiceItems(HEALTH_GUIDE_ADV_ITEM, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                health_flag[which] = isChecked;
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int length = health_flag.length;
                String headth_str = "";
                for (int i = 0; i < length; i++) {
                    if (health_flag[i]) {
                        headth_str += HEALTH_GUIDE_ADV_ITEM[i] + ",";
                        health_code += HEALTH_GUIDE_ADV_ID[i] + ",";
                    }
                }
                headth_str = headth_str.substring(0, headth_str.length() - 1);
                health_code = health_code.substring(0, health_code.length() - 1);
                health_guide_adv.setText(headth_str);
            }
        });
        builder.setTitle("选择症状");
        builder.show();
    }

    private void addMental(String json) {
        materialDialog= showIndeterminateProgressDialog(R.string.isSubmitting);
        GucNetEngineClient.addMental(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //{"UploadMentalVisitResult":{"result":false,"errInfo":"错误[B01],同一随访日期无法重复创建数据"}}
                JSONObject jsonObject= JSON.parseObject(response);
                JSONObject objResult=jsonObject.getJSONObject("UploadMentalVisitResult");
                String errInfo=objResult.getString("errInfo");
                if(StringUtils.isBlank(errInfo)){
                    showToast(R.string.add_success);
                }else{
                    showToast(errInfo);
                }
            }
        }, null,materialDialog);
    }

    private void getMental(String recordCode) {
        materialDialog= showIndeterminateProgressDialog(R.string.is_loading_please_waite);

        GucNetEngineClient.getMental(recordCode, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = JSON.parseObject(response);
                com.alibaba.fastjson.JSONObject obj_res = jsonObject.getJSONObject("getMentalRecordResult");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    JSONObject obj_mentalRecord = obj_res.getJSONObject("mentalRecord");
                    MentalDTO dto = JSON.parseObject(obj_mentalRecord.toJSONString(), MentalDTO.class);
                    updateUI(dto);
                } else {
                    showToast(errInfo);
                }
            }
        }, null,materialDialog);
    }

    private void getLastInfo(final String ehr_id) {
         materialDialog=showIndeterminateProgressDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getLastMental(ehr_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response);
                com.alibaba.fastjson.JSONObject obj_res = jsonObject.getJSONObject("getLastMentalRecordResult");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    com.alibaba.fastjson.JSONObject obj_mentalRecord = obj_res.getJSONObject("mentalRecord");
                    MentalDTO dto = JSON.parseObject(obj_mentalRecord.toJSONString(), MentalDTO.class);
                    updateUI(dto);
                } else {
                    showToast(errInfo);
                }
            }
        },null,materialDialog);
    }

    private void updateUI(MentalDTO dto) {
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        symptom.setText(dto.getSymptom());
        symptom_other.setText(dto.getSymptom_other());
        danger_grade.setSelection(StrUtil.getIntvalue(dto.getDanger_grade()) + 1);
        insight_appraise.setSelection(StrUtil.getIntvalue(dto.getInsight_appraise()));
        sleep_type.setSelection(StrUtil.getIntvalue(dto.getSleep_type()));
        diet_type.setSelection(StrUtil.getIntvalue(dto.getDiet_type()));
        living.setSelection(StrUtil.getIntvalue(dto.getLiving()));
        housework.setSelection(StrUtil.getIntvalue(dto.getHousework()));
        String work_position = dto.getWork();
        int work_value = StrUtil.getIntvalue(work_position);
        if (work_value < 3) {
            work.setSelection(work_value + 1);
        } else {
            work.setSelection(4);
        }
        study.setSelection(StrUtil.getIntvalue(dto.getStudy()));
        sociality.setSelection(StrUtil.getIntvalue(dto.getSociality()));

        affray.setText(StrUtil.getValue(dto.getAffray()));
        cause_trouble.setText(StrUtil.getValue(dto.getCause_trouble()));
        disaster.setText(StrUtil.getValue(dto.getDisaster()));
        autolesion.setText(StrUtil.getValue(dto.getAutolesion()));
        attempted_suicide.setText(StrUtil.getValue(dto.getAttempted_suicide()));

        captivity.setSelection(StrUtil.getIntvalue(dto.getCaptivity()) + 1);
        in_hospital.setSelection(StrUtil.getIntvalue(dto.getIn_hospital()) + 1);
        leave_hospital_time.setText(StrUtil.getValue(dto.getLeave_hospital_time()));

        laboratory_abn.setSelection(StrUtil.getIntvalue(dto.getLaboratory_abn()));
        laboratory_examination.setText(StrUtil.getValue(dto.getLaboratory_examination()));
        rule_dose.setSelection(StrUtil.getIntvalue(dto.getRule_dose()));

        String drug_side_effect_str = dto.getDrug_side_effect();
        if (!TextUtils.isEmpty(drug_side_effect_str)) {
            drug_side_effect.setSelection(drug_side_effect_str.endsWith("true") ? 2 : 1);
        } else {
            drug_side_effect.setSelection(0);
        }
        drug_side_effects.setText(StrUtil.getValue(dto.getDrug_side_effects()));

        anamnesis_tr_type.setSelection(StrUtil.getIntvalue(dto.getAnamnesis_tr_type()));
        String transfert_str = dto.getTransfert();
        if (!TextUtils.isEmpty(transfert_str)) {
            transfert.setSelection(transfert_str.endsWith("true") ? 2 : 1);
        } else {
            transfert.setSelection(0);
        }
        transfert_dept.setText(dto.getTransfert_dept());
        drug1_name.setText(dto.getDrug1_name());
        int length = UNIT_TYPE.length;
        String drug1_dosage_unit_str = dto.getDrug1_dosage_unit();
        for (int i = 0; i < length && !TextUtils.isEmpty(drug1_dosage_unit_str); i++) {
            if (drug1_dosage_unit_str.equals(UNIT_TYPE[i])) {
                drug1_dosage_unit.setSelection(i);
            }
        }
        drug1_fn.setText(dto.getDrug1_fn());
        drug1_dosage_one.setText(dto.getDrug1_dosage_one());

        drug2_name.setText(dto.getDrug2_name());
        String drug2_dosage_unit_str = dto.getDrug2_dosage_unit();
        for (int i = 0; i < length && !TextUtils.isEmpty(drug2_dosage_unit_str); i++) {
            if (drug2_dosage_unit_str.equals(UNIT_TYPE[i])) {
                drug2_dosage_unit.setSelection(i);
            }
        }
        drug2_fn.setText(dto.getDrug2_fn());
        drug2_dosage_one.setText(dto.getDrug2_dosage_one());

        drug3_name.setText(dto.getDrug3_name());
        String drug3_dosage_unit_str = dto.getDrug3_dosage_unit();
        for (int i = 0; i < length && !TextUtils.isEmpty(drug2_dosage_unit_str); i++) {
            if (drug3_dosage_unit_str.equals(UNIT_TYPE[i])) {
                drug3_dosage_unit.setSelection(i);
            }
        }
        drug3_fn.setText(dto.getDrug3_fn());
        drug3_dosage_one.setText(dto.getDrug3_dosage_one());
        health_guide_adv.setText(dto.getHealth_guide_adv_str());
        next_visit_date.setText(getFormatDateStr(StrUtil.getValue(dto.getNext_visit_date())));
        visit_class.setSelection(StrUtil.getIntvalue(dto.getVisit_class()));
        visit_doctor.setText(StrUtil.getValue(dto.getVisit_doctor()));

        String cm_guidance_strr = dto.getCm_guidance();
        if (!TextUtils.isEmpty(cm_guidance_strr)) {
            cm_guidance.setSelection(cm_guidance_strr.endsWith("true") ? 2 : 1);
        } else {
            cm_guidance.setSelection(0);
        }
        cm_guidance_str.setText(StrUtil.getValue(dto.getCm_guidance_str()));
    }


    private String buildJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("visit_date", StrUtil.getToTrim(visit_date));
            jsonObject.put("symptom_code", symptom_code);
            jsonObject.put("symptom", StrUtil.getToTrim(symptom));
            jsonObject.put("symptom_other", StrUtil.getToTrim(symptom_other));

            int danger_grade_position = danger_grade.getSelectedItemPosition();
            jsonObject.put("danger_grade", danger_grade_position == 0 ? null : danger_grade_position - 1);

            int insight_appraise_position = insight_appraise.getSelectedItemPosition();
            jsonObject.put("insight_appraise", insight_appraise_position == 0 ? null : (insight_appraise_position - 1) + "");

            int sleep_type_position = sleep_type.getSelectedItemPosition();
            jsonObject.put("sleep_type", sleep_type_position == 0 ? null : (sleep_type_position - 1) + "");

            int diet_type_position = diet_type.getSelectedItemPosition();
            jsonObject.put("diet_type", diet_type_position == 0 ? null : (diet_type_position - 1) + "");

            int living_position = living.getSelectedItemPosition();
            jsonObject.put("living", living_position == 0 ? null : (living_position - 1) + "");

            int housework_position = housework.getSelectedItemPosition();
            jsonObject.put("housework", housework_position == 0 ? null : (housework_position - 1) + "");

            int work_position = study.getSelectedItemPosition();
            jsonObject.put("work", work_position == 0 ? null : (work_position - 1) + "");

            int study_position = study.getSelectedItemPosition();
            jsonObject.put("study", study_position == 0 ? null : (study_position - 1) + "");


            int sociality_position = sociality.getSelectedItemPosition();
            jsonObject.put("sociality", sociality_position == 0 ? null : (sociality_position - 1) + "");

            jsonObject.put("affray", StrUtil.getToTrim(affray));
            jsonObject.put("cause_trouble", StrUtil.getToTrim(cause_trouble));
            jsonObject.put("disaster", StrUtil.getToTrim(disaster));
            jsonObject.put("autolesion", StrUtil.getToTrim(autolesion));
            jsonObject.put("attempted_suicide", StrUtil.getToTrim(attempted_suicide));

            int captivity_position = captivity.getSelectedItemPosition();
            jsonObject.put("attempted_suicide", captivity_position == 0 ? null : captivity_position + "");

            int in_hospital_position = in_hospital.getSelectedItemPosition();
            jsonObject.put("in_hospital", in_hospital_position == 0 ? null : in_hospital_position + "");

            jsonObject.put("leave_hospital_time", StrUtil.getToTrim(leave_hospital_time));

            int laboratory_abn_position = laboratory_abn.getSelectedItemPosition();
            jsonObject.put("laboratory_abn", laboratory_abn_position == 0 ? null : laboratory_abn_position + "");
            jsonObject.put("laboratory_examination", StrUtil.getToTrim(laboratory_examination));

            int rule_dose_position = rule_dose.getSelectedItemPosition();
            jsonObject.put("rule_dose", rule_dose_position == 0 ? null : rule_dose_position + "");

            int drug_side_effect_position = drug_side_effect.getSelectedItemPosition();
            jsonObject.put("drug_side_effect", drug_side_effect_position == 0 ? null : (drug_side_effect_position - 1) + "");
            jsonObject.put("drug_side_effects", StrUtil.getToTrim(drug_side_effects));

            int anamnesis_tr_type_position = anamnesis_tr_type.getSelectedItemPosition();
            jsonObject.put("anamnesis_tr_type", anamnesis_tr_type_position == 0 ? null : anamnesis_tr_type_position + "");

            int transfert_position = transfert.getSelectedItemPosition();
            jsonObject.put("transfert", transfert_position == 0 ? null : (transfert_position - 1) + "");
            jsonObject.put("transfert_dept", StrUtil.getToTrim(transfert_dept));

            jsonObject.put("drug1_name", StrUtil.getToTrim(drug1_name));
            jsonObject.put("drug1_fn", StrUtil.getToTrim(drug1_fn));
            jsonObject.put("drug1_dosage_unit", StrUtil.getJsonString(UNIT_TYPE[drug1_dosage_unit.getSelectedItemPosition()]));
            jsonObject.put("drug1_dosage_one", StrUtil.getToTrim(drug1_dosage_one));

            jsonObject.put("drug2_name", StrUtil.getToTrim(drug2_name));
            jsonObject.put("drug2_fn", StrUtil.getToTrim(drug2_fn));
            jsonObject.put("drug2_dosage_unit", StrUtil.getJsonString(UNIT_TYPE[drug2_dosage_unit.getSelectedItemPosition()]));
            jsonObject.put("drug2_dosage_one", StrUtil.getToTrim(drug2_dosage_one));

            jsonObject.put("drug3_name", StrUtil.getToTrim(drug3_name));
            jsonObject.put("drug3_fn", StrUtil.getToTrim(drug3_fn));
            jsonObject.put("drug3_dosage_unit", StrUtil.getJsonString(UNIT_TYPE[drug3_dosage_unit.getSelectedItemPosition()]));
            jsonObject.put("drug3_dosage_one", StrUtil.getToTrim(drug3_dosage_one));

            jsonObject.put("health_guide_adv", StrUtil.getJsonString(health_code));
            jsonObject.put("health_guide_adv_str", StrUtil.getToTrim(health_guide_adv));

            jsonObject.put("next_visit_date", StrUtil.getToTrim(next_visit_date));

            jsonObject.put("next_visit_date", StrUtil.getToTrim(next_visit_date));

            int visit_class_position = visit_class.getSelectedItemPosition();
            jsonObject.put("visit_class", visit_class_position == 0 ? null : visit_class_position + "");

            jsonObject.put("visit_doctor", StrUtil.getToTrim(visit_doctor));

            int cm_guidance_position = cm_guidance.getSelectedItemPosition();
            jsonObject.put("cm_guidance", cm_guidance_position == 0 ? null : (cm_guidance_position - 1) + "");

            jsonObject.put("cm_guidance_str", StrUtil.getToTrim(cm_guidance_str));

            jsonObject.put("cr_org_name", GucApplication.cr_org_name);
            jsonObject.put("record_code", "");
            jsonObject.put("ehr_id", dto.getEhr_id());
            jsonObject.put("db_id", GucNetEngineClient.DBID);
            jsonObject.put("cr_operator", GucApplication.loginUserCode);
            jsonObject.put("cr_org_code", GucNetEngineClient.ORG_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public void popWindows(View parent) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.layout_list, null);
        popupWindow = new PopupWindow(view, (int) getResources().getDimension(R.dimen.y100), (int) getResources().getDimension(R.dimen.y60));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(parent, 0, 0);
        TextView tv_previous = (TextView) view.findViewById(R.id.tv_previous);
        TextView tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        tv_previous.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    public static MentalAddFragment newInstance(MentalBaseDTO dto, int type) {
        Bundle args = new Bundle();
        args.putSerializable("data", dto);
        args.putInt("type", type);
        MentalAddFragment fragment = new MentalAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MentalAddFragment newInstance(String record_code, int type, String name) {
        Bundle args = new Bundle();
        args.putString("record_code", record_code);
        args.putInt("type", type);
        args.putString("name", name);
        MentalAddFragment fragment = new MentalAddFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
