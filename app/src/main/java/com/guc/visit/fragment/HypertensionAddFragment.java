package com.guc.visit.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.HypertensionAddDTO;
import com.guc.visit.domain.HypertensionBaseDTO;
import com.guc.visit.net.DefaultErrorListener;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ToastUtils;
import com.guc.visit.utils.ViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class HypertensionAddFragment extends BaseFragment {
    private TextView visit_date;
    private TextView symptom;
    private StringBuilder symptom_code = new StringBuilder("");
    private EditText symptom_other;
    public final static String[] ABSORBSALT_CASE_TARGET = {"", "轻", "中", "重"};
    public final static String[] ZERO_TO_ONE = {"", "0", "1"};
    public final static String[] ONE_TO_FOUR = {"", "1", "2", "3", "4"};
    public final static String[] UNIT_TYPE = {"", "mg", "mm", "片", "粒"};
    public final static String[] VISIT_MODE_ID = {"", "1", "2", "3", "9"};
    public final static String[] ONE_TO_zero = {"", "1", "0"};

    private EditText name;
    /**
     * 血压
     */
    private EditText systolic_pressure;
    private EditText systolic_pressure_target;
    /**
     * 舒张压
     */
    private EditText diastolic_pressure;
    private EditText diastolic_pressure_target;
    /**
     * 体重
     */
    private EditText weight;
    private EditText weight_target;
    /**
     * 身高
     */
    private EditText height;
    private TextView BIM;
    /**
     * 心率
     */
    private EditText heart_rate;
    private EditText heart_rate_target;
    /**
     * 其他体征
     */
    private EditText objective_sign_other;
    /**
     * 吸烟量
     */
    private EditText smoking_dnum;
    private EditText smoking_dnum_target;
    /**
     * 饮酒量
     */
    private EditText drinking_dnum;
    private EditText drinking_dnum_target;
    /**
     * 周运动次数
     */
    private EditText sport_weeknum;
    private EditText sport_weeknum_target;
    /**
     * 周运动时间
     */
    private EditText sport_times;
    private EditText sport_times_target;
    /**
     * 摄盐
     */
    private Spinner absorbsalt_case;
    private Spinner absorbsalt_case_target;
    /**
     * 辅助检查
     */
    private EditText aid_mtest_result;
    /**
     * 药物不良反应
     */
    private Spinner drug_side_effect;
    private LinearLayout layout_drug_side_effects;
    private EditText drug_side_effects;
    /**
     * 心态调整
     */
    private Spinner mental_adjust;
    /**
     * 遵医行为
     */
    private Spinner compiance;
    /**
     * 服药依从
     */
    private Spinner rule_dose;
    /**
     * 随访分类
     */
    private Spinner visit_appraise;
    /**
     * 药物名称1
     */
    private EditText drug1_name;
    private EditText drug1_fn;
    private EditText drug1_dosage_one;
    private Spinner drug1_dosage_unit_code;
    /**
     * 药物名称2
     */
    private EditText drug2_name;
    private EditText drug2_fn;
    private EditText drug2_dosage_one;
    private Spinner drug2_dosage_unit_code;
    /**
     * 药物名称3
     */
    private EditText drug3_name;
    private EditText drug3_fn;
    private EditText drug3_dosage_one;
    private Spinner drug3_dosage_unit_code;
    /**
     * 转诊原因
     */
    private EditText transfert_cause;
    /**
     * 转诊科别
     */
    private EditText transfert_dept;
    /**
     * 下次随访日期
     */
    private TextView next_visit_date;
    /**
     * 拜访模式
     */
    private Spinner visit_mode;
    /**
     * 随访医师
     */
    private TextView visit_doctor;
    /**
     * 中医指导
     */
    private Spinner cm_guidance;
    /**
     * 指导内容
     */
    private EditText cm_guidance_str;

    private PopupWindow popupWindow;

    private HypertensionBaseDTO dto;

    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_hypertension_add);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        /**0 add   1 view*/
        if (type == 0) {
            dto = (HypertensionBaseDTO) getArguments().getSerializable("data");
            controlBar(R.string.add_visit, R.string.back, true, true);
            Calendar calendar = Calendar.getInstance();
            String cur_str = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            name.setText(dto.getName());
            visit_date.setText(cur_str);
            next_visit_date.setText(cur_str);
            visit_doctor.setText(GucApplication.visit_doctor);
        } else {
            String record_code = bundle.getString("record_code");
            String name_str = bundle.getString("name");
            getHistoryHypertension(record_code);
            name.setText(name_str);
            controlBar(name_str, R.string.back, true, false);
            ViewUtils.setAllViewEnable(linearLayout);
        }

    }

    private void getHistoryHypertension(final String record_code) {
        GucNetEngineClient.getHistoryHypertension(record_code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response);
                com.alibaba.fastjson.JSONObject obj_res = jsonObject.getJSONObject("getHypertensionRecordResult");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    com.alibaba.fastjson.JSONObject hypertensionRecord = obj_res.getJSONObject("hypertensionRecord");
                    HypertensionAddDTO hypertensionAddDTO = JSON.parseObject(hypertensionRecord.toJSONString(), HypertensionAddDTO.class);
                    updateUI(hypertensionAddDTO);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        visit_date.setOnClickListener(this);
        next_visit_date.setOnClickListener(this);
        right_layout.setOnClickListener(this);
        symptom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    switch (v.getId()) {
                        case R.id.symptom:
                            multiChoiceDialog(getIntArray(symptom_code), R.array.array_hypertension_symptom, symptom, symptom_code);
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        drug_side_effect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 2) {
                    layout_drug_side_effects.setVisibility(View.VISIBLE);
                } else {
                    layout_drug_side_effects.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initWidget(View view) {
        ImageView iv_add = (ImageView) view.findViewById(R.id.iv_add);
        iv_add.setImageResource(R.mipmap.ic_menu);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        name = (EditText) view.findViewById(R.id.name);
        visit_date = (TextView) view.findViewById(R.id.visit_date);
        symptom = (TextView) view.findViewById(R.id.symptom);
        symptom_other = (EditText) view.findViewById(R.id.symptom_other);

        systolic_pressure = (EditText) view.findViewById(R.id.systolic_pressure);
        systolic_pressure_target = (EditText) view.findViewById(R.id.systolic_pressure_target);

        diastolic_pressure = (EditText) view.findViewById(R.id.diastolic_pressure);
        diastolic_pressure_target = (EditText) view.findViewById(R.id.diastolic_pressure_target);

        weight = (EditText) view.findViewById(R.id.weight);
        weight_target = (EditText) view.findViewById(R.id.weight_target);

        height = (EditText) view.findViewById(R.id.height);
        BIM = (TextView) view.findViewById(R.id.BIM);

        heart_rate = (EditText) view.findViewById(R.id.heart_rate);
        heart_rate_target = (EditText) view.findViewById(R.id.heart_rate_target);

        objective_sign_other = (EditText) view.findViewById(R.id.objective_sign_other);

        smoking_dnum = (EditText) view.findViewById(R.id.smoking_dnum);
        smoking_dnum_target = (EditText) view.findViewById(R.id.smoking_dnum_target);

        drinking_dnum = (EditText) view.findViewById(R.id.drinking_dnum);
        drinking_dnum_target = (EditText) view.findViewById(R.id.drinking_dnum_target);

        sport_weeknum = (EditText) view.findViewById(R.id.sport_weeknum);
        sport_weeknum_target = (EditText) view.findViewById(R.id.sport_weeknum_target);

        sport_times = (EditText) view.findViewById(R.id.sport_times);
        sport_times_target = (EditText) view.findViewById(R.id.sport_times_target);

        absorbsalt_case = (Spinner) view.findViewById(R.id.absorbsalt_case);
        absorbsalt_case_target = (Spinner) view.findViewById(R.id.absorbsalt_case_target);

        aid_mtest_result = (EditText) view.findViewById(R.id.aid_mtest_result);

        drug_side_effect = (Spinner) view.findViewById(R.id.drug_side_effect);
        layout_drug_side_effects = (LinearLayout) view.findViewById(R.id.layout_drug_side_effects);
        drug_side_effects = (EditText) view.findViewById(R.id.drug_side_effects);

        mental_adjust = (Spinner) view.findViewById(R.id.mental_adjust);

        compiance = (Spinner) view.findViewById(R.id.compiance);

        rule_dose = (Spinner) view.findViewById(R.id.rule_dose);

        visit_appraise = (Spinner) view.findViewById(R.id.visit_appraise);

        drug1_name = (EditText) view.findViewById(R.id.drug1_name);
        drug1_fn = (EditText) view.findViewById(R.id.drug1_fn);
        drug1_dosage_one = (EditText) view.findViewById(R.id.drug1_dosage_one);
        drug1_dosage_unit_code = (Spinner) view.findViewById(R.id.drug3_dosage_unit_code);

        drug2_name = (EditText) view.findViewById(R.id.drug2_name);
        drug2_fn = (EditText) view.findViewById(R.id.drug2_fn);
        drug2_dosage_one = (EditText) view.findViewById(R.id.drug2_dosage_one);
        drug2_dosage_unit_code = (Spinner) view.findViewById(R.id.drug2_dosage_unit_code);

        drug3_name = (EditText) view.findViewById(R.id.drug3_name);
        drug3_fn = (EditText) view.findViewById(R.id.drug3_fn);
        drug3_dosage_one = (EditText) view.findViewById(R.id.drug3_dosage_one);
        drug3_dosage_unit_code = (Spinner) view.findViewById(R.id.drug3_dosage_unit_code);

        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);

        transfert_dept = (EditText) view.findViewById(R.id.transfert_dept);

        next_visit_date = (TextView) view.findViewById(R.id.next_visit_date);

        visit_mode = (Spinner) view.findViewById(R.id.visit_mode);

        visit_doctor = (TextView) view.findViewById(R.id.visit_doctor);

        cm_guidance = (Spinner) view.findViewById(R.id.cm_guidance);

        cm_guidance_str = (EditText) view.findViewById(R.id.cm_guidance_str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.visit_date:
                showDatePicker(visit_date);
                break;
            case R.id.next_visit_date:
                showDatePicker(next_visit_date);
                break;
            case R.id.right_layout:
                popWindows(right_layout);
                break;
            case R.id.tv_previous:
                getLastData(dto.getEhr_id());
                popupWindow.dismiss();
                break;
            case R.id.tv_submit:
                if (checkValueLimit()) {
                    submit();
                }
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    private void getLastData(String ehr_id) {
        GucNetEngineClient.getLastHypertension(ehr_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response);
                com.alibaba.fastjson.JSONObject obj_res = jsonObject.getJSONObject("getLastHypertensionRecordResult");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    com.alibaba.fastjson.JSONObject hypertensionRecord = obj_res.getJSONObject("hypertensionRecord");
                    HypertensionAddDTO dto = JSON.parseObject(hypertensionRecord.toJSONString(), HypertensionAddDTO.class);
                    updateUI(dto);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void updateUI(HypertensionAddDTO dto) {
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        symptom.setText(dto.getSymptom());
        symptom_other.setText(dto.getSymptom_other());

        systolic_pressure.setText(dto.getSystolic_pressure());
        systolic_pressure_target.setText(dto.getSystolic_pressure_target());

        diastolic_pressure.setText(dto.getDiastolic_pressure());
        diastolic_pressure_target.setText(dto.getDiastolic_pressure_target());

        weight.setText(dto.getWeight());
        weight_target.setText(dto.getWeight_target());

        height.setText(dto.getHeight());
        //  BIM .setText(dto.getB);

        heart_rate.setText(dto.getHeart_rate());
        heart_rate_target.setText(dto.getHeart_rate_target());

        objective_sign_other.setText(dto.getObjective_sign_other());

        smoking_dnum.setText(dto.getSmoking_dnum());
        smoking_dnum_target.setText(dto.getSmoking_dnum_target());

        drinking_dnum.setText(dto.getDrinking_dnum());
        drinking_dnum_target.setText(dto.getDrinking_dnum_target());

        sport_weeknum.setText(dto.getSport_weeknum());
        sport_weeknum_target.setText(dto.getSport_weeknum_target());

        sport_times.setText(dto.getSport_times());
        sport_times_target.setText(dto.getSport_times_target());
        int length = ABSORBSALT_CASE_TARGET.length;
        String absorbsalt_case_str = dto.getAbsorbsalt_case();
        int absorbsalt_case_int = 0;
        String absorbsalt_case_target_str = dto.getAbsorbsalt_case_target();
        int absorbsalt_case_target_int = 0;
        for (int i = 0; i < length; i++) {
            if (ABSORBSALT_CASE_TARGET[i].equals(absorbsalt_case_str)) {
                absorbsalt_case_int = i;
            }
        }
        for (int i = 0; i < length; i++) {
            if (ABSORBSALT_CASE_TARGET[i].equals(absorbsalt_case_target_str)) {
                absorbsalt_case_target_int = i;
            }
        }
        absorbsalt_case.setSelection(absorbsalt_case_int);
        absorbsalt_case_target.setSelection(absorbsalt_case_target_int);

        aid_mtest_result.setText(dto.getAid_mtest_result());

        String drug_side_effect_str = dto.getDrug_side_effect();
        if (!TextUtils.isEmpty(drug_side_effect_str)) {
            drug_side_effect.setSelection(drug_side_effect_str.endsWith("true") ? 1 : 2);
        } else {
            drug_side_effect.setSelection(0);
        }

        drug_side_effects.setText(dto.getDrug_side_effects());

        mental_adjust.setSelection(StrUtil.getIntvalue(dto.getMental_adjust()));
        compiance.setSelection(StrUtil.getIntvalue(dto.getCompiance()));


        rule_dose.setSelection(StrUtil.getIntvalue(dto.getRule_dose()));

        visit_appraise.setSelection(StrUtil.getIntvalue(dto.getVisit_appraise()));

        drug1_name.setText(dto.getDrug1_name());
        drug1_fn.setText(dto.getDrug1_fn());
        drug1_dosage_one.setText(dto.getDrug1_dosage_one());
        drug1_dosage_unit_code.setSelection(getIntValue(dto.getDrug1_dosage_unit()));

        drug2_name.setText(dto.getDrug1_name());
        drug2_fn.setText(dto.getDrug1_fn());
        drug2_dosage_one.setText(dto.getDrug2_dosage_one());
        drug2_dosage_unit_code.setSelection(getIntValue(dto.getDrug2_dosage_unit()));

        drug3_name.setText(dto.getDrug3_name());
        drug3_fn.setText(dto.getDrug3_fn());
        drug3_dosage_one.setText(dto.getDrug3_dosage_one());
        drug3_dosage_unit_code.setSelection(getIntValue(dto.getDrug3_dosage_unit()));

        transfert_cause.setText(dto.getTransfert_cause());

        transfert_dept.setText(dto.getTransfert_dept());

        next_visit_date.setText(getFormatDateStr(dto.getNext_visit_date()));

        String visit_mode_str = dto.getVisit_mode();
        int visit_mode_int = 0;
        length = VISIT_MODE_ID.length;
        for (int i = 0; i < length; i++) {
            if (VISIT_MODE_ID[i].equals(visit_mode_str)) {
                visit_mode_int = i;
            }
        }

        visit_mode.setSelection(visit_mode_int);

        visit_doctor.setText(dto.getVisit_doctor());

        String cm_guidance_strr = dto.getCm_guidance();
        if (cm_guidance_strr.equals("true")) {
            cm_guidance.setSelection(1);
        } else if (cm_guidance.equals("false")) {
            cm_guidance.setSelection(2);
        } else {
            cm_guidance.setSelection(0);
        }
        cm_guidance_str.setText(dto.getCm_guidance_str());
    }

    private int getIntValue(String string) {
        int lenght = UNIT_TYPE.length;
        for (int i = 0; i < lenght; i++) {
            if (UNIT_TYPE[i].equals(string)) {
                return i;
            }
        }
        return 0;
    }

    public boolean checkValueLimit() {

        boolean checkFlag = true;
        String text_value = "";
        float intValue = 0;

        // <!--收缩压、舒张压 -->
        text_value = systolic_pressure.getText().toString();
        // System.out.println("--------------------->"+text_value);
        if (!text_value.trim().equals("")) {
            intValue = Float.valueOf(text_value);
            if (intValue < 40 || intValue > 250) {
                ToastUtils.showLong(mActivity, "收缩压数据范围错误: 40≤ 数据 ≤ 250");
                systolic_pressure.requestFocus();
                return false;
            }
        }
        text_value = diastolic_pressure.getText().toString();
        if (!text_value.trim().equals("")) {
            intValue = Float.valueOf(text_value);
            if (intValue < 40 || intValue > 250) {
                ToastUtils.showLong(mActivity, "舒张压数据范围错误: 40≤ 数据 ≤ 250");
                diastolic_pressure.requestFocus();
                return false;
            }
        }
        // <!--体重、身高-->
        text_value = weight.getText().toString();
        if (!text_value.trim().equals("")) {
            intValue = Float.valueOf(text_value);
            if (intValue < 0.5 || intValue > 300) {
                ToastUtils.showLong(mActivity, "体重数据范围错误: 0.5≤ 体重 ≤ 300");
                weight.requestFocus();
                return false;
            }
        }
        text_value = height.getText().toString();
        if (!text_value.trim().equals("")) {
            intValue = Float.valueOf(text_value);
            if (intValue < 20 || intValue > 250) {
                ToastUtils.showLong(mActivity, "身高数据范围错误: 20≤ 数据 ≤ 250");
                height.requestFocus();
                return false;
            }
        }
        return checkFlag;
    }

    private void submit() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("visit_date", StrUtil.getToTrim(visit_date));
            jsonObject.put("symptom_code", StrUtil.getString(symptom_code).replace("28", "99"));
            jsonObject.put("symptom", StrUtil.getToTrim(symptom));
            jsonObject.put("symptom_other", StrUtil.getToTrim(symptom_other));
            jsonObject.put("systolic_pressure", StrUtil.getToTrim(systolic_pressure));
            jsonObject.put("systolic_pressure_target", StrUtil.getToTrim(systolic_pressure_target));
            jsonObject.put("diastolic_pressure", StrUtil.getToTrim(diastolic_pressure));
            jsonObject.put("diastolic_pressure_target", StrUtil.getToTrim(diastolic_pressure_target));
            jsonObject.put("height", StrUtil.getToTrim(height));
            jsonObject.put("weight", StrUtil.getToTrim(weight));
            jsonObject.put("weight_target", StrUtil.getToTrim(weight_target));
            jsonObject.put("heart_rate", StrUtil.getToTrim(heart_rate));
            jsonObject.put("heart_rate_target", StrUtil.getToTrim(heart_rate_target));
            jsonObject.put("objective_sign_other", StrUtil.getToTrim(objective_sign_other));
            jsonObject.put("smoking_dnum", StrUtil.getToTrim(smoking_dnum));
            jsonObject.put("smoking_dnum_target", StrUtil.getToTrim(smoking_dnum_target));
            jsonObject.put("drinking_dnum", StrUtil.getToTrim(drinking_dnum));
            jsonObject.put("drinking_dnum_target", StrUtil.getToTrim(drinking_dnum_target));
            jsonObject.put("sport_weeknum", StrUtil.getToTrim(sport_weeknum));
            jsonObject.put("sport_weeknum_target", StrUtil.getToTrim(sport_weeknum_target));
            jsonObject.put("sport_times", StrUtil.getToTrim(sport_times));
            jsonObject.put("sport_times_target", StrUtil.getToTrim(sport_times_target));
            jsonObject.put("absorbsalt_case", StrUtil.getJsonString(ABSORBSALT_CASE_TARGET[absorbsalt_case.getSelectedItemPosition()]));
            jsonObject.put("absorbsalt_case_target", StrUtil.getJsonString(ABSORBSALT_CASE_TARGET[absorbsalt_case_target.getSelectedItemPosition()]));
            jsonObject.put("aid_mtest_result", StrUtil.getToTrim(aid_mtest_result));
            jsonObject.put("drug_side_effect", StrUtil.getJsonString(ZERO_TO_ONE[drug_side_effect.getSelectedItemPosition()]));
            jsonObject.put("drug_side_effects", StrUtil.getToTrim(drug_side_effects));
            int mental_adjust_sel = mental_adjust.getSelectedItemPosition();
            jsonObject.put("mental_adjust", mental_adjust_sel == 0 ? null : mental_adjust.getSelectedItemPosition() + "");
            int compiance_sel = compiance.getSelectedItemPosition();
            jsonObject.put("compiance", compiance_sel == 0 ? null : compiance.getSelectedItemPosition() + "");
            int rule_dose_sel = rule_dose.getSelectedItemPosition();
            jsonObject.put("rule_dose", rule_dose_sel == 0 ? null : rule_dose.getSelectedItemPosition() + "");
            jsonObject.put("visit_appraise", StrUtil.getJsonString(ONE_TO_FOUR[visit_appraise.getSelectedItemPosition()]));

            jsonObject.put("drug1_name", StrUtil.getToTrim(drug1_name));
            jsonObject.put("drug1_fn", StrUtil.getToTrim(drug1_fn));
            jsonObject.put("drug1_dosage_unit_code", StrUtil.getJsonString(UNIT_TYPE[drug1_dosage_unit_code.getSelectedItemPosition()]));
            jsonObject.put("drug1_dosage_one", StrUtil.getToTrim(drug1_dosage_one));

            jsonObject.put("drug2_name", StrUtil.getToTrim(drug2_name));
            jsonObject.put("drug2_fn", StrUtil.getToTrim(drug2_fn));
            jsonObject.put("drug2_dosage_unit_code", StrUtil.getJsonString(UNIT_TYPE[drug2_dosage_unit_code.getSelectedItemPosition()]));
            jsonObject.put("drug2_dosage_one", StrUtil.getToTrim(drug2_dosage_one));

            jsonObject.put("drug3_name", StrUtil.getToTrim(drug3_name));
            jsonObject.put("drug3_fn", StrUtil.getToTrim(drug3_fn));
            jsonObject.put("drug3_dosage_unit_code", StrUtil.getJsonString(UNIT_TYPE[drug3_dosage_unit_code.getSelectedItemPosition()]));
            jsonObject.put("drug3_dosage_one", StrUtil.getToTrim(drug3_dosage_one));

            jsonObject.put("transfert_cause", StrUtil.getToTrim(transfert_cause));

            jsonObject.put("transfert_dept", StrUtil.getToTrim(transfert_dept));

            jsonObject.put("visit_mode", StrUtil.getJsonString(VISIT_MODE_ID[visit_mode.getSelectedItemPosition()]));

            jsonObject.put("cm_guidance", StrUtil.getJsonString(ONE_TO_zero[cm_guidance.getSelectedItemPosition()]));

            jsonObject.put("cm_guidance_str", StrUtil.getToTrim(cm_guidance_str));

            jsonObject.put("visit_doctor", StrUtil.getToTrim(visit_doctor));
            jsonObject.put("next_visit_date", StrUtil.getToTrim(next_visit_date));
            jsonObject.put("cr_org_code", GucNetEngineClient.ORG_CODE);
            jsonObject.put("cr_org_name", GucApplication.cr_org_name);
            jsonObject.put("cr_operator", GucApplication.loginUserCode);
            jsonObject.put("cr_time", "");
            jsonObject.put("db_id", GucNetEngineClient.DBID);
            jsonObject.put("ehr_id", dto.getEhr_id());
            jsonObject.put("record_code", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
        addHypertension(json);
    }

    private void addHypertension(final String json) {
        GucNetEngineClient.addHypertension(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response);
                com.alibaba.fastjson.JSONObject jsonObject1 = jsonObject.getJSONObject("UploadHypertensionVisitResult");
                String errInfo = jsonObject1.getString("errInfo");
                if (errInfo != null) {
                    ToastUtils.showLong(mActivity, errInfo);
                } else {
                    ToastUtils.showLong(mActivity, R.string.add_success);
                }
            }
        }, new DefaultErrorListener());
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

//    private void selectionSymptom() {
//        symptom_flag = new boolean[SYMPTOM_TYPE.length];
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setMultiChoiceItems(SYMPTOM_TYPE, null, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                symptom_flag[which] = isChecked;
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                int length = symptom_flag.length;
//                String symptom_str = "";
//                for (int i = 0; i < length; i++) {
//                    if (symptom_flag[i]) {
//                        symptom_str += SYMPTOM_TYPE[i] + ",";
//                        symptom_code += SYMPTOM_TYPE_ID[i] + ",";
//                    }
//                }
//                symptom_str = symptom_str.substring(0, symptom_str.length() - 1);
//                symptom_code = symptom_code.substring(0, symptom_code.length() - 1);
//                symptom.setText(symptom_str);
//            }
//        });
//        builder.setTitle("选择症状");
//        builder.show();
//    }

    public static HypertensionAddFragment newInstance(HypertensionBaseDTO dto, int type) {
        Bundle args = new Bundle();
        args.putSerializable("data", dto);
        args.putInt("type", type);
        HypertensionAddFragment fragment = new HypertensionAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static HypertensionAddFragment newInstance(String record_code, int type, String name) {
        Bundle args = new Bundle();
        args.putString("record_code", record_code);
        args.putInt("type", type);
        args.putString("name", name);
        HypertensionAddFragment fragment = new HypertensionAddFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
