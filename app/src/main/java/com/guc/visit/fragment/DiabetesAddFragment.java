package com.guc.visit.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
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
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.DiabetesAddDTO;
import com.guc.visit.domain.DiabetesDTO;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ToastUtils;
import com.guc.visit.utils.ViewUtils;

import org.json.JSONException;

import java.util.Calendar;


public class DiabetesAddFragment extends BaseFragment implements View.OnTouchListener {
    /**
     * 姓名、随访时间
     */
    private TextView name;
    private TextView visit_date;

    /**
     * 症状、其他症状
     */
    private EditText symptom;
    private EditText symptom_other;
    /**
     * 收缩压
     */
    private EditText systolic_pressure;
    private EditText systolic_pressure_target;
    /**
     * 舒张压
     */
    private EditText diastolic_pressure;
    private EditText diastolic_pressure_target;
    /**
     * 身高、BIM
     */
    private EditText height;
    private TextView BIM;
    /**
     * 体重
     */
    private EditText weight;
    private EditText weight_target;
    /**
     * 其他体征
     */
    private EditText objective_sign_other;
    /**
     * 足背动脉博动
     */
    private Spinner dorsalis_pedis;
    /**
     * 吸烟
     */
    private EditText smoking_dnum;
    private EditText smoking_dnum_target;
    /**
     * 饮酒
     */
    private EditText drinking_dnum;
    private EditText drinking_dnum_target;
    /**
     * 周运动次数
     */
    private EditText sport_weeknum;
    private EditText sport_weeknum_target;
    /**
     * 每周运动时间
     */
    private EditText sport_times;
    private EditText sport_times_target;
    /**
     * 主食
     */
    private EditText eday_main_food;
    private EditText eday_main_food_target;
    /**
     * 心态调整、遵医行为
     */
    private Spinner mental_adjust;
    private Spinner compiance;
    /**
     * 空腹血糖、随机血糖
     */
    private EditText begin_blood_sugar;
    private EditText eatend_blood_sugar;
    /**
     * 糖化血红蛋白
     */
    private EditText blood_ghb;
    /**
     * 检查日期
     */
    private EditText mtest_date;
    /**
     * 其他检查1、2、3
     */
    private EditText other_mtest1;
    private EditText other_mtest2;
    private EditText other_mtest3;
    /**
     * 服药依从
     */
    private Spinner rule_dose;
    /**
     * 药物不良反应、药物不良反应说明
     */
    private Spinner drug_side_effect;
    private EditText drug_side_effects;
    /**
     * 低糖反应
     */
    private Spinner glucopenia;
    /**
     * 随访评价
     */
    private Spinner visit_appraise;
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
    /**
     * 胰岛素用药
     */
    private EditText insulin_drug;
    /**
     * 转诊原因、转诊科别
     */
    private EditText transfert_cause;
    private EditText transfert_dept;
    /**
     * 下次随访时间、随访方式
     */
    private TextView next_visit_date;
    private Spinner visit_mode;
    /**
     * 随访医师
     */
    private TextView visit_doctor;
    /**
     * 中医指导、指导内容
     */
    private Spinner cm_guidance;
    private EditText cm_guidance_str;

    private PopupWindow popupWindow;
    private LinearLayout right_layout;
    private DiabetesDTO dto;
    private final static String[] SYMPTOM_TYPE = {"无症状", "多饮", "多食", "多尿", "视力模糊", "感染", "手脚麻木", "下肢浮肿", "体重明显下降", "其它"};
    private final static int[] SYMPTOM_TYPE_ID = {1, 2, 3, 4, 5, 6, 7, 8, 9, 99};
    private boolean[] symptom_flag;
    private String symptom_code = "";
    private final static String[] UNIT_TYPE = {null, "mg", "mm", "片", "粒"};
    private final static String[] VISIT_MODE = {null, "1", "2", "3", "9"};
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_diabetes_add);
    }

    @Override
    protected void initWidget(View view) {
        ImageView iv_add = (ImageView) view.findViewById(R.id.iv_add);
        iv_add.setImageResource(R.mipmap.ic_menu);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        name = (TextView) view.findViewById(R.id.name);
        visit_date = (TextView) view.findViewById(R.id.visit_date);

        symptom = (EditText) view.findViewById(R.id.symptom);
        symptom_other = (EditText) view.findViewById(R.id.symptom_other);

        systolic_pressure = (EditText) view.findViewById(R.id.systolic_pressure);
        systolic_pressure_target = (EditText) view.findViewById(R.id.systolic_pressure_target);

        diastolic_pressure = (EditText) view.findViewById(R.id.diastolic_pressure);
        diastolic_pressure_target = (EditText) view.findViewById(R.id.diastolic_pressure_target);

        height = (EditText) view.findViewById(R.id.height);
        BIM = (TextView) view.findViewById(R.id.BIM);

        weight = (EditText) view.findViewById(R.id.weight);
        weight_target = (EditText) view.findViewById(R.id.weight_target);

        objective_sign_other = (EditText) view.findViewById(R.id.objective_sign_other);

        dorsalis_pedis = (Spinner) view.findViewById(R.id.dorsalis_pedis);

        smoking_dnum = (EditText) view.findViewById(R.id.smoking_dnum);
        smoking_dnum_target = (EditText) view.findViewById(R.id.smoking_dnum_target);

        drinking_dnum = (EditText) view.findViewById(R.id.drinking_dnum);
        drinking_dnum_target = (EditText) view.findViewById(R.id.drinking_dnum_target);

        sport_weeknum = (EditText) view.findViewById(R.id.sport_weeknum);
        sport_weeknum_target = (EditText) view.findViewById(R.id.sport_weeknum_target);

        sport_times = (EditText) view.findViewById(R.id.sport_times);
        sport_times_target = (EditText) view.findViewById(R.id.sport_times_target);

        eday_main_food = (EditText) view.findViewById(R.id.eday_main_food);
        eday_main_food_target = (EditText) view.findViewById(R.id.eday_main_food_target);

        mental_adjust = (Spinner) view.findViewById(R.id.mental_adjust);
        compiance = (Spinner) view.findViewById(R.id.compiance);

        begin_blood_sugar = (EditText) view.findViewById(R.id.begin_blood_sugar);
        eatend_blood_sugar = (EditText) view.findViewById(R.id.eatend_blood_sugar);

        blood_ghb = (EditText) view.findViewById(R.id.blood_ghb);

        mtest_date = (EditText) view.findViewById(R.id.mtest_date);

        other_mtest1 = (EditText) view.findViewById(R.id.other_mtest1);
        other_mtest2 = (EditText) view.findViewById(R.id.other_mtest2);
        other_mtest3 = (EditText) view.findViewById(R.id.other_mtest3);

        rule_dose = (Spinner) view.findViewById(R.id.rule_dose);

        drug_side_effect = (Spinner) view.findViewById(R.id.drug_side_effect);
        drug_side_effects = (EditText) view.findViewById(R.id.drug_side_effects);

        glucopenia = (Spinner) view.findViewById(R.id.glucopenia);

        visit_appraise = (Spinner) view.findViewById(R.id.visit_appraise);

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

        insulin_drug = (EditText) view.findViewById(R.id.insulin_drug);

        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);
        transfert_dept = (EditText) view.findViewById(R.id.transfert_dept);

        next_visit_date = (TextView) view.findViewById(R.id.next_visit_date);
        visit_mode = (Spinner) view.findViewById(R.id.visit_mode);

        visit_doctor = (TextView) view.findViewById(R.id.visit_doctor);

        cm_guidance = (Spinner) view.findViewById(R.id.cm_guidance);
        cm_guidance_str = (EditText) view.findViewById(R.id.cm_guidance_str);

        right_layout = (LinearLayout) view.findViewById(R.id.right_layout);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        if (type == 1) {
            String record_code = bundle.getString("record_code");
            String name_str = bundle.getString("name");
            name.setText(name_str);
            getDiabetesByRecordCode(record_code);
            controlBar(name_str, R.string.back, true, false);
            ViewUtils.setAllViewEnable(linearLayout);
        } else {
            dto = (DiabetesDTO) bundle.getSerializable("data");
            if (dto != null) {
                name.setText(dto.getName());
            }
            Calendar calendar = Calendar.getInstance();
            String cur_str = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            visit_date.setText(cur_str);
            calendar.set(Calendar.DAY_OF_MONTH, 90);
            String next_str = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
            next_visit_date.setText(next_str);
            visit_doctor.setText(GucApplication.visit_doctor);
            symptom_flag = new boolean[SYMPTOM_TYPE.length];
            controlBar(R.string.add_visit, R.string.back, true, true);
        }
    }

    @Override
    protected void setListeners() {
        right_layout.setOnClickListener(this);
        ll_back.setOnClickListener(this);
        visit_date.setOnTouchListener(this);
        symptom.setOnTouchListener(this);
        mtest_date.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.symptom:
                    selectionSymptom();
                    break;
                case R.id.mtest_date:
                    showDatePicker(mtest_date);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_layout:
                popWindows(right_layout);
                break;
            case R.id.tv_previous:
                getLast(dto.getEhr_id());
                popupWindow.dismiss();
                break;
            case R.id.tv_submit:
                if (checkValueLimit()) {
                    addDiabetes(buildJson());
                }
                popupWindow.dismiss();
                break;
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.symptom:
                selectionSymptom();
                break;
            default:
                break;
        }
    }

    public boolean checkValueLimit() {
        String text_value;
        float intValue;
        text_value = systolic_pressure.getText().toString();
        if (!TextUtils.isEmpty(text_value)) {
            intValue = Float.valueOf(text_value);
            if (intValue < 40 || intValue > 250) {
                ToastUtils.showLong(mActivity, "收缩压数据范围错误: 40≤ 数据 ≤ 250");
                systolic_pressure.requestFocus();
                return false;
            }
        }
        text_value = diastolic_pressure.getText().toString();
        if (!TextUtils.isEmpty(text_value)) {
            intValue = Float.valueOf(text_value);
            if (intValue < 40 || intValue > 250) {
                ToastUtils.showLong(mActivity, "舒张压数据范围错误: 40≤ 数据 ≤ 250");
                diastolic_pressure.requestFocus();
                return false;
            }
        }
        text_value = weight.getText().toString();
        if (!TextUtils.isEmpty(text_value)) {
            intValue = Float.valueOf(text_value);
            if (intValue < 0.5 || intValue > 300) {
                ToastUtils.showLong(mActivity, "体重数据范围错误: 0.5≤ 体重 ≤ 300");
                weight.requestFocus();
                return false;
            }
        }
        text_value = height.getText().toString();
        if (!TextUtils.isEmpty(text_value)) {
            intValue = Float.valueOf(text_value);
            if (intValue < 20 || intValue > 250) {
                ToastUtils.showLong(mActivity, "身高数据范围错误: 20≤ 数据 ≤ 250");
                height.requestFocus();
                return false;
            }
        }
        return true;
    }

    private String buildJson() {
        org.json.JSONObject jsonObject = new org.json.JSONObject();
        try {
            jsonObject.put("record_code", "");
            jsonObject.put("visit_date", StrUtil.getToTrim(visit_date));
            jsonObject.put("symptom_code", symptom_code);
            jsonObject.put("symptom", StrUtil.getToTrim(symptom));
            jsonObject.put("symptom_other", StrUtil.getToTrim(symptom_other));
            jsonObject.put("systolic_pressure", StrUtil.getToTrim(systolic_pressure));
            jsonObject.put("systolic_pressure_target", StrUtil.getToTrim(systolic_pressure_target));
            jsonObject.put("diastolic_pressure", StrUtil.getToTrim(diastolic_pressure));
            jsonObject.put("diastolic_pressure_target", StrUtil.getToTrim(diastolic_pressure_target));
            jsonObject.put("height", StrUtil.getToTrim(height));
            jsonObject.put("weight", StrUtil.getToTrim(weight));
            jsonObject.put("weight_target", StrUtil.getToTrim(weight_target));
            jsonObject.put("objective_sign_other", StrUtil.getToTrim(objective_sign_other));

            int dorsalis_pedis_value = dorsalis_pedis.getSelectedItemPosition();
            jsonObject.put("dorsalis_pedis", dorsalis_pedis_value == 0 ? "" : dorsalis_pedis_value + "");

            jsonObject.put("smoking_dnum", StrUtil.getToTrim(smoking_dnum));
            jsonObject.put("smoking_dnum_target", StrUtil.getToTrim(smoking_dnum_target));
            jsonObject.put("drinking_dnum", StrUtil.getToTrim(drinking_dnum));
            jsonObject.put("drinking_dnum_target", StrUtil.getToTrim(drinking_dnum_target));
            jsonObject.put("sport_weeknum", StrUtil.getToTrim(sport_weeknum));
            jsonObject.put("sport_weeknum_target", StrUtil.getToTrim(sport_weeknum_target));
            jsonObject.put("sport_times", StrUtil.getToTrim(sport_times));
            jsonObject.put("sport_times_target", StrUtil.getToTrim(sport_times_target));
            jsonObject.put("eday_main_food", StrUtil.getToTrim(eday_main_food));
            jsonObject.put("eday_main_food_target", StrUtil.getToTrim(eday_main_food_target));

            int mental_adjust_value = mental_adjust.getSelectedItemPosition();
            jsonObject.put("mental_adjust", mental_adjust_value == 0 ? null : mental_adjust_value + "");

            int compiance_value = compiance.getSelectedItemPosition();
            jsonObject.put("compiance", compiance_value == 0 ? null : compiance_value + "");

            jsonObject.put("begin_blood_sugar", StrUtil.getToTrim(begin_blood_sugar));
            jsonObject.put("eatend_blood_sugar", StrUtil.getToTrim(eatend_blood_sugar));
            jsonObject.put("blood_ghb", StrUtil.getToTrim(blood_ghb));
            jsonObject.put("mtest_date", StrUtil.getToTrim(mtest_date));
            jsonObject.put("other_mtest1", StrUtil.getToTrim(other_mtest1));
            jsonObject.put("other_mtest2", StrUtil.getToTrim(other_mtest2));
            jsonObject.put("other_mtest3", StrUtil.getToTrim(other_mtest3));

            int rule_dose_value = rule_dose.getSelectedItemPosition();
            jsonObject.put("rule_dose", rule_dose_value == 0 ? null : rule_dose_value + "");

            int drug_side_effect_value = rule_dose.getSelectedItemPosition();
            jsonObject.put("drug_side_effect", drug_side_effect_value == 0 ? null : (drug_side_effect_value - 1) + "");

            jsonObject.put("drug_side_effects", StrUtil.getToTrim(drug_side_effects));

            int glucopenia_value = glucopenia.getSelectedItemPosition();
            jsonObject.put("glucopenia", glucopenia_value == 0 ? null : glucopenia_value);

            int visit_appraise_value = visit_appraise.getSelectedItemPosition();
            jsonObject.put("visit_appraise", visit_appraise_value == 0 ? null : visit_appraise_value);

            jsonObject.put("drug1_name", StrUtil.getToTrim(drug1_name));
            jsonObject.put("drug1_fn", StrUtil.getToTrim(drug1_fn));
            int drug1_dosage_unit_value = drug1_dosage_unit.getSelectedItemPosition();
            jsonObject.put("drug1_dosage_unit", UNIT_TYPE[drug1_dosage_unit_value]);
            jsonObject.put("drug1_dosage_one", StrUtil.getToTrim(drug1_dosage_one));

            jsonObject.put("drug2_name", StrUtil.getToTrim(drug2_name));
            jsonObject.put("drug2_fn", StrUtil.getToTrim(drug2_fn));
            int drug2_dosage_unit_value = drug2_dosage_unit.getSelectedItemPosition();
            jsonObject.put("drug2_dosage_unit", UNIT_TYPE[drug2_dosage_unit_value]);
            jsonObject.put("drug2_dosage_one", StrUtil.getToTrim(drug2_dosage_one));

            jsonObject.put("drug3_name", StrUtil.getToTrim(drug3_name));
            jsonObject.put("drug3_fn", StrUtil.getToTrim(drug3_fn));
            int drug3_dosage_unit_value = drug3_dosage_unit.getSelectedItemPosition();
            jsonObject.put("drug3_dosage_unit", UNIT_TYPE[drug3_dosage_unit_value]);
            jsonObject.put("drug3_dosage_one", StrUtil.getToTrim(drug3_dosage_one));

            jsonObject.put("transfert_cause", StrUtil.getToTrim(transfert_cause));
            jsonObject.put("transfert_dept", StrUtil.getToTrim(transfert_dept));
            jsonObject.put("next_visit_date", StrUtil.getToTrim(next_visit_date));

            int visit_mode_value = visit_mode.getSelectedItemPosition();
            jsonObject.put("visit_mode", VISIT_MODE[visit_mode_value]);

            jsonObject.put("visit_doctor", StrUtil.getToTrim(visit_doctor));
            jsonObject.put("insulin_drug", StrUtil.getToTrim(insulin_drug));
            int cm_guidance_value = cm_guidance.getSelectedItemPosition();
            jsonObject.put("cm_guidance", cm_guidance_value == 0 ? null : (cm_guidance_value - 1) + "");
            jsonObject.put("cm_guidance_str", StrUtil.getToTrim(cm_guidance_str));
            jsonObject.put("ehr_id", dto.getEhr_id());
            jsonObject.put("db_id", GucNetEngineClient.DBID);
            jsonObject.put("cr_org_code", GucNetEngineClient.ORG_CODE);
            jsonObject.put("cr_time", "");
            jsonObject.put("cr_operator", GucApplication.loginUserCode);
            jsonObject.put("cr_org_name", GucApplication.cr_org_name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }


    private void selectionSymptom() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMultiChoiceItems(SYMPTOM_TYPE, symptom_flag, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                symptom_flag[which] = isChecked;
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int length = symptom_flag.length;
                String symptom_str = "";
                symptom_code = "";
                for (int i = 0; i < length; i++) {
                    if (symptom_flag[i]) {
                        symptom_str += SYMPTOM_TYPE[i] + ",";
                        symptom_code += SYMPTOM_TYPE_ID[i] + ",";
                    }
                }
                /***检查是够合法*/
                if (symptom_code.contains("99") && symptom_code.length() > 2) {
                    ToastUtils.showLong(mActivity, R.string.illegal);
                    symptom_flag = new boolean[length];
                    return;
                }
                /**检查是否为空*/
                if (!TextUtils.isEmpty(symptom_code)) {
                    symptom_str = symptom_str.substring(0, symptom_str.length() - 1);
                    symptom_code = symptom_code.substring(0, symptom_code.length() - 1);
                    symptom.setText(symptom_str);
                }
            }
        });
        builder.setTitle("选择症状");
        builder.show();
    }

    private void getLast(String ehrId) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getDiabetesLast(ehrId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", response);
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject obj_res = jsonObject.getJSONObject("getLastDiabetesRecordResult");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    JSONObject diabetes = obj_res.getJSONObject("diabetesRecord");
                    DiabetesAddDTO dto = JSON.parseObject(diabetes.toJSONString(), DiabetesAddDTO.class);
                    upadteUI(dto);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });
    }

    private void addDiabetes(String json) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.addDiabetes(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject obj_res = jsonObject.getJSONObject("UploadDiabetesVisitResult");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    ToastUtils.showLong(mActivity, R.string.add_success);
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

    public void getDiabetesByRecordCode(String recordCode) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getDiabetesRecord(recordCode, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject obj_res = jsonObject.getJSONObject("getDiabetesRecordResult");
                String errInfo = obj_res.getString("errInfo");
                if (errInfo == null) {
                    JSONObject diabetesRecord = obj_res.getJSONObject("diabetesRecord");
                    DiabetesAddDTO dto = JSON.parseObject(diabetesRecord.toJSONString(), DiabetesAddDTO.class);
                    upadteUI(dto);
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

    private void upadteUI(DiabetesAddDTO dto) {
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        symptom.setText(dto.getSymptom());
        symptom_code = dto.getSymptom_code();
        symptom_other.setText(dto.getSymptom_other());
        systolic_pressure.setText(dto.getSystolic_pressure());
        systolic_pressure_target.setText(dto.getSystolic_pressure_target());

        diastolic_pressure.setText(dto.getDiastolic_pressure());
        diastolic_pressure_target.setText(dto.getDiastolic_pressure_target());
        height.setText(dto.getHeight());
        weight.setText(dto.getWeight());
        weight_target.setText(dto.getWeight_target());
        objective_sign_other.setText(dto.getObjective_sign_other());

        dorsalis_pedis.setSelection(StrUtil.getIntvalue(dto.getDorsalis_pedis()));

        smoking_dnum.setText(dto.getSmoking_dnum());
        smoking_dnum_target.setText(dto.getSmoking_dnum_target());

        drinking_dnum.setText(dto.getDrinking_dnum());
        drinking_dnum_target.setText(dto.getDrinking_dnum_target());

        sport_weeknum.setText(dto.getSport_weeknum());
        sport_weeknum_target.setText(dto.getSport_weeknum_target());

        sport_times.setText(dto.getSport_times());
        sport_times.setText(dto.getSport_times_target());

        mental_adjust.setSelection(StrUtil.getIntvalue(dto.getMental_adjust()));
        compiance.setSelection(StrUtil.getIntvalue(dto.getCompiance()));

        begin_blood_sugar.setText(dto.getBegin_blood_sugar());
        eatend_blood_sugar.setText(dto.getEatend_blood_sugar());
        blood_ghb.setText(dto.getBlood_ghb());
        mtest_date.setText(dto.getMtest_date());
        other_mtest1.setText(dto.getOther_mtest1());
        other_mtest2.setText(dto.getOther_mtest2());
        other_mtest3.setText(dto.getOther_mtest3());
        rule_dose.setSelection(StrUtil.getIntvalue(dto.getRule_dose()));
        String drug_side_effect_str = dto.getDrug_side_effect();
        if (drug_side_effect_str.equals("true") && !TextUtils.isEmpty(drug_side_effect_str)) {
            drug_side_effect.setSelection(2);
        } else {
            drug_side_effect.setSelection(1);
        }
        drug_side_effects.setText(dto.getDrug_side_effects());
        glucopenia.setSelection(StrUtil.getIntvalue(dto.getGlucopenia()));
        visit_appraise.setSelection(StrUtil.getIntvalue(dto.getVisit_appraise()));

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
        transfert_cause.setText(dto.getTransfert_cause());
        transfert_dept.setText(dto.getTransfert_dept());
        next_visit_date.setText(getFormatDateStr(dto.getNext_visit_date()));
        visit_mode.setSelection(StrUtil.getIntvalue(dto.getVisit_mode()));
        visit_doctor.setText(dto.getVisit_doctor());
        insulin_drug.setText(dto.getInsulin_drug());
        String cm_guidance_strr = dto.getCm_guidance();
        if (cm_guidance_strr.equals("true") && !TextUtils.isEmpty(cm_guidance_strr)) {
            cm_guidance.setSelection(2);
        } else {
            cm_guidance.setSelection(1);
        }
        cm_guidance_str.setText(dto.getCm_guidance_str());
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


    public static DiabetesAddFragment newInstance(DiabetesDTO dto, int type) {
        Bundle args = new Bundle();
        args.putSerializable("data", dto);
        args.putInt("type", type);
        DiabetesAddFragment fragment = new DiabetesAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DiabetesAddFragment newInstance(String record_code, int type, String name) {
        Bundle args = new Bundle();
        args.putString("record_code", record_code);
        args.putInt("type", type);
        args.putString("name", name);
        DiabetesAddFragment fragment = new DiabetesAddFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
