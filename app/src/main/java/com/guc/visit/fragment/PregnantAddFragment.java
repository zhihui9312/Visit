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
import com.guc.visit.domain.PregnantAddDTO;
import com.guc.visit.domain.PregnantInDTO;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;
import com.guc.visit.utils.ViewUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.HashMap;

/**
 * 第一次孕妇随访
 */
public class PregnantAddFragment extends BaseFragment implements View.OnTouchListener {
    private EditText name;
    private EditText visit_doctor;
    private EditText visit_date;
    private EditText cyesis;
    private EditText parturient;
    private EditText eutocia;
    private EditText caesarean;
    private EditText menses_over_date;
    private EditText due_birth_date;
    private EditText birth_week;
    private EditText birth_week_days;
    private EditText anamnesis;
    private StringBuilder anamnesis_code;
    private EditText anamnesis_other;
    private EditText family_disease;
    private StringBuilder family_disease_code;
    private EditText family_disease_other;
    private EditText personal_history;
    private StringBuilder personal_history_code;
    private EditText personal_history_other;
    private Spinner gynaecology_operation_mark;
    private EditText gynaecology_operation;
    private EditText gynaecology_other;
    private EditText abort;
    private EditText die_foetus;
    private EditText die_birth;
    private EditText die_newbron;
    private EditText handicapped;
    private EditText height;
    private EditText weight;
    private EditText systolic_pressure;
    private EditText diastolic_pressure;
    private Spinner heart_result;
    private EditText heart_str;
    private Spinner lung_result;
    private EditText lung_str;
    private Spinner pudendum_result;
    private EditText pudendum_str;
    private Spinner cunt_result;
    private EditText cunt_str;
    private Spinner cervical_result;
    private EditText cervical_str;
    private Spinner uterus_result;
    private EditText uterus_str;
    private Spinner appendage_result;
    private EditText appendage_str;
    private EditText blood_hb;
    private EditText leukocyte;
    private EditText blood_plt;
    private EditText blood_other;
    private EditText blood_sugar;
    private EditText upro_value;
    private EditText glu_value;
    private Spinner qualitative_name;
    private Spinner occultblood_name;
    private EditText urine_other;
    private EditText serum_alt;
    private EditText serum_got;
    private EditText albumin_chroma;
    private EditText total_bilirubin;
    private EditText conjugated_bilirubin;
    private EditText blood_creatinine;
    private EditText blood_un;
    private Spinner hbs_ag;
    private Spinner hbs_ab;
    private Spinner hbe_ag;
    private Spinner hbe_ab;
    private Spinner hbc_ab;
    private Spinner cunt_secretion;
    private EditText cunt_secretion_other;
    private Spinner vagina_cleanliness;
    private Spinner syphilis_serology;
    private Spinner hiv;
    private EditText b_us;
    private Spinner evaluate_mark;
    private EditText evaluate;
    private EditText health_guide_str;
    private StringBuilder health_guide_code;
    private EditText health_guide_other;
    private Spinner transfert_mark;
    private EditText transfert_dept;
    private EditText transfert_cause;
    private EditText next_visit_date;
    private EditText education_prescribe;
    private TextView tv_right;

    private String ehr_id;
    private String record_code;
    private String nameStr;
    private LinearLayout linearLayout;
    /**
     * 0 add 1  view
     */
    private String operation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_pregnant_add);
    }

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

    private void getNetworkData(final String record_code) {
        GucNetEngineClient.getPregnantOne(record_code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getMaternalBeforeOneRecordResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONObject objInfo = objResult.getJSONObject("recordInfo");
                    PregnantAddDTO dto = JSON.parseObject(objInfo.toJSONString(), PregnantAddDTO.class);
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

    private void updateUI(PregnantAddDTO dto) {
        name.setText(nameStr);
        visit_doctor.setText(dto.getVisit_doctor());
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        cyesis.setText(dto.getCyesis());
        parturient.setText(dto.getParturient());
        eutocia.setText(dto.getEutocia());
        caesarean.setText(dto.getCaesarean());
        menses_over_date.setText(getFormatDateStr(dto.getMenses_over_date()));
        due_birth_date.setText(getFormatDateStr(dto.getDue_birth_date()));
        birth_week.setText(dto.getBirth_week());
        birth_week_days.setText(dto.getBirth_week_days());
        anamnesis.setText(dto.getAnamnesis());
        anamnesis_other.setText(dto.getAnamnesis_other());
        family_disease.setText(dto.getFamily_disease());
        family_disease_other.setText(dto.getFamily_disease_other());
        personal_history.setText(dto.getPersonal_history());
        personal_history_other.setText(dto.getPersonal_history_other());
        gynaecology_operation_mark.setSelection(convertToInteger(dto.getGynaecology_operation_mark()));
        gynaecology_operation.setText(dto.getGynaecology_operation());
        gynaecology_other.setText(dto.getGynaecology_other());
        abort.setText(dto.getAbort());
        die_foetus.setText(dto.getDie_foetus());
        die_birth.setText(dto.getDie_birth());
        die_newbron.setText(dto.getDie_newbron());
        handicapped.setText(dto.getHandicapped());
        height.setText(dto.getHeight());
        weight.setText(dto.getWeight());
        systolic_pressure.setText(dto.getSystolic_pressure());
        diastolic_pressure.setText(dto.getDiastolic_pressure());
        heart_result.setSelection(convertToInteger(dto.getHeart_result()));
        heart_str.setText(dto.getHeart_str());
        lung_result.setSelection(convertToInteger(dto.getLung_result()));
        lung_str.setText(dto.getLung_str());
        pudendum_result.setSelection(convertToInteger(dto.getPudendum_result()));
        pudendum_str.setText(dto.getPudendum_str());
        cunt_result.setSelection(convertToInteger(dto.getCunt_result()));
        cunt_str.setText(dto.getCunt_str());
        cervical_result.setSelection(convertToInteger(dto.getCervical_result()));
        cervical_str.setText(dto.getCervical_str());
        uterus_result.setSelection(convertToInteger(dto.getUterus()));
        uterus_str.setText(dto.getUterus_str());
        appendage_result.setSelection(convertToInteger(dto.getAppendage_result()));
        appendage_str.setText(dto.getAppendage_str());
        blood_hb.setText(dto.getBlood_hb());
        leukocyte.setText(dto.getLeukocyte());
        blood_plt.setText(dto.getBlood_plt());
        blood_other.setText(dto.getBlood_other());
        blood_sugar.setText(dto.getBlood_sugar());
        upro_value.setText(dto.getUpro_value());
        glu_value.setText(dto.getGlu_value());
        qualitative_name.setSelection(convertToInteger(dto.getQualitative_name()));
        occultblood_name.setSelection(convertToInteger(dto.getOccultblood_name()));
        urine_other.setText(dto.getUrine_other());
        serum_alt.setText(dto.getSerum_alt());
        serum_got.setText(dto.getSerum_got());
        albumin_chroma.setText(dto.getAlbumin_chroma());
        total_bilirubin.setText(dto.getTotal_bilirubin());
        conjugated_bilirubin.setText(dto.getConjugated_bilirubin());
        blood_creatinine.setText(dto.getBlood_creatinine());
        blood_un.setText(dto.getBlood_un());
        hbs_ag.setSelection(convertToInteger(dto.getHbs_ag()));
        hbs_ab.setSelection(convertToInteger(dto.getHbs_ab()));
        hbe_ag.setSelection(convertToInteger(dto.getHbe_ag()));
        hbe_ab.setSelection(convertToInteger(dto.getHbe_ab()));
        hbc_ab.setSelection(convertToInteger(dto.getHbc_ab()));
        cunt_secretion.setSelection(convertToInteger(dto.getCunt_secretion()));
        cunt_secretion_other.setText(dto.getCunt_secretion_other());
        vagina_cleanliness.setSelection(convertToInteger(dto.getVagina_cleanliness()));
        syphilis_serology.setSelection(convertToInteger(dto.getSyphilis_serology()));
        hiv.setSelection(convertToInteger(dto.getHiv()));
        b_us.setText(dto.getB_us());
        evaluate_mark.setSelection(convertToInteger(dto.getEvaluate_mark()));
        evaluate.setText(dto.getEvaluate());
        health_guide_str.setText(dto.getHealth_guide_str());
        health_guide_other.setText(dto.getHealth_guide_other());
        transfert_mark.setSelection(convertToInteger(dto.getTransfert_mark()));
        transfert_dept.setText(dto.getTransfert_dept());
        transfert_cause.setText(dto.getTransfert_cause());
        next_visit_date.setText(getFormatDateStr(dto.getNext_visit_date()));
        education_prescribe.setText(dto.getEducation_prescribe());
    }


    @Override
    protected void initData() {
        if (operation.equals("0")) {
            initExisting();
        }else{
            ViewUtils.setAllViewEnable(linearLayout);
        }
        controlBar(R.string.pregnant_one, R.string.back, true, true);
    }

    private void initExisting() {

        name.setText(nameStr);
        visit_doctor.setText(GucApplication.visit_doctor);
        String dateStr = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        visit_date.setText(dateStr);
        next_visit_date.setText(dateStr);
        due_birth_date.setText(dateStr);
        menses_over_date.setText(dateStr);
        tv_right.setText(R.string.commint);
    }

    @Override
    protected void setListeners() {
        health_guide_str.setOnTouchListener(this);
        anamnesis.setOnTouchListener(this);
        visit_date.setOnTouchListener(this);
        next_visit_date.setOnTouchListener(this);
        due_birth_date.setOnTouchListener(this);
        menses_over_date.setOnTouchListener(this);
        tv_right.setOnClickListener(this);
        ll_back.setOnClickListener(this);
    }
//    private void

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.health_guide_str:
                    health_guide_code = new StringBuilder("");
                    multiChoiceDialog(R.array.array_health_guide, health_guide_str, health_guide_code);
                    break;
                case R.id.anamnesis:
                    anamnesis_code = new StringBuilder("");
                    multiChoiceDialog2(R.array.array_anamnesis, anamnesis, anamnesis_code);
                    break;
                case R.id.family_disease:
                    family_disease_code = new StringBuilder("");
                    multiChoiceDialog2(R.array.array_family_disease, family_disease, family_disease_code);
                    break;
                case R.id.personal_history:
                    personal_history_code = new StringBuilder("");
                    multiChoiceDialog2(R.array.array_personal_history, personal_history, personal_history_code);
                    break;
                case R.id.visit_date:
                    showDatePicker(visit_date);
                    break;
                case R.id.next_visit_date:
                    showDatePicker(next_visit_date);
                    break;
                case R.id.due_birth_date:
                    showDatePicker(due_birth_date);
                    break;
                case R.id.menses_over_date:
                    showDatePicker(menses_over_date);
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
        visit_doctor = (EditText) view.findViewById(R.id.visit_doctor);
        visit_date = (EditText) view.findViewById(R.id.visit_date);
        cyesis = (EditText) view.findViewById(R.id.cyesis);
        parturient = (EditText) view.findViewById(R.id.parturient);
        eutocia = (EditText) view.findViewById(R.id.eutocia);
        caesarean = (EditText) view.findViewById(R.id.caesarean);
        menses_over_date = (EditText) view.findViewById(R.id.menses_over_date);
        due_birth_date = (EditText) view.findViewById(R.id.due_birth_date);
        birth_week = (EditText) view.findViewById(R.id.birth_week);
        birth_week_days = (EditText) view.findViewById(R.id.birth_week_days);
        anamnesis = (EditText) view.findViewById(R.id.anamnesis);
        anamnesis_other = (EditText) view.findViewById(R.id.anamnesis_other);
        family_disease = (EditText) view.findViewById(R.id.family_disease);
        family_disease_other = (EditText) view.findViewById(R.id.family_disease_other);
        personal_history = (EditText) view.findViewById(R.id.personal_history);
        personal_history_other = (EditText) view.findViewById(R.id.personal_history_other);
        gynaecology_operation_mark = (Spinner) view.findViewById(R.id.gynaecology_operation_mark);
        gynaecology_operation = (EditText) view.findViewById(R.id.gynaecology_operation);
        gynaecology_other = (EditText) view.findViewById(R.id.gynaecology_other);
        abort = (EditText) view.findViewById(R.id.abort);
        die_foetus = (EditText) view.findViewById(R.id.die_foetus);
        die_birth = (EditText) view.findViewById(R.id.die_birth);
        die_newbron = (EditText) view.findViewById(R.id.die_newbron);
        handicapped = (EditText) view.findViewById(R.id.handicapped);
        height = (EditText) view.findViewById(R.id.height);
        weight = (EditText) view.findViewById(R.id.weight);
        systolic_pressure = (EditText) view.findViewById(R.id.systolic_pressure);
        diastolic_pressure = (EditText) view.findViewById(R.id.diastolic_pressure);
        heart_result = (Spinner) view.findViewById(R.id.heart_result);
        heart_str = (EditText) view.findViewById(R.id.heart_str);
        lung_result = (Spinner) view.findViewById(R.id.lung_result);
        lung_str = (EditText) view.findViewById(R.id.lung_str);
        pudendum_result = (Spinner) view.findViewById(R.id.pudendum_result);
        pudendum_str = (EditText) view.findViewById(R.id.pudendum_str);
        cunt_result = (Spinner) view.findViewById(R.id.cunt_result);
        cunt_str = (EditText) view.findViewById(R.id.cunt_str);
        cervical_result = (Spinner) view.findViewById(R.id.cervical_result);
        cervical_str = (EditText) view.findViewById(R.id.cervical_str);
        uterus_result = (Spinner) view.findViewById(R.id.uterus_result);
        uterus_str = (EditText) view.findViewById(R.id.uterus_str);
        appendage_result = (Spinner) view.findViewById(R.id.appendage_result);
        appendage_str = (EditText) view.findViewById(R.id.appendage_str);
        blood_hb = (EditText) view.findViewById(R.id.blood_hb);
        leukocyte = (EditText) view.findViewById(R.id.leukocyte);
        blood_plt = (EditText) view.findViewById(R.id.blood_plt);
        blood_other = (EditText) view.findViewById(R.id.blood_other);
        blood_sugar = (EditText) view.findViewById(R.id.blood_sugar);
        upro_value = (EditText) view.findViewById(R.id.upro_value);
        glu_value = (EditText) view.findViewById(R.id.glu_value);
        qualitative_name = (Spinner) view.findViewById(R.id.qualitative_name);
        occultblood_name = (Spinner) view.findViewById(R.id.occultblood_name);
        urine_other = (EditText) view.findViewById(R.id.urine_other);
        serum_alt = (EditText) view.findViewById(R.id.serum_alt);
        serum_got = (EditText) view.findViewById(R.id.serum_got);
        albumin_chroma = (EditText) view.findViewById(R.id.albumin_chroma);
        total_bilirubin = (EditText) view.findViewById(R.id.total_bilirubin);
        conjugated_bilirubin = (EditText) view.findViewById(R.id.conjugated_bilirubin);
        blood_creatinine = (EditText) view.findViewById(R.id.blood_creatinine);
        blood_un = (EditText) view.findViewById(R.id.blood_un);
        hbs_ag = (Spinner) view.findViewById(R.id.hbs_ag);
        hbs_ab = (Spinner) view.findViewById(R.id.hbs_ab);
        hbe_ag = (Spinner) view.findViewById(R.id.hbe_ag);
        hbe_ab = (Spinner) view.findViewById(R.id.hbe_ab);
        hbc_ab = (Spinner) view.findViewById(R.id.hbc_ab);
        cunt_secretion = (Spinner) view.findViewById(R.id.cunt_secretion);
        cunt_secretion_other = (EditText) view.findViewById(R.id.cunt_secretion_other);
        vagina_cleanliness = (Spinner) view.findViewById(R.id.vagina_cleanliness);
        syphilis_serology = (Spinner) view.findViewById(R.id.syphilis_serology);
        hiv = (Spinner) view.findViewById(R.id.hiv);
        b_us = (EditText) view.findViewById(R.id.b_us);
        evaluate_mark = (Spinner) view.findViewById(R.id.evaluate_mark);
        evaluate = (EditText) view.findViewById(R.id.evaluate);
        health_guide_str = (EditText) view.findViewById(R.id.health_guide_str);
        health_guide_other = (EditText) view.findViewById(R.id.health_guide_other);
        transfert_mark = (Spinner) view.findViewById(R.id.transfert_mark);
        transfert_dept = (EditText) view.findViewById(R.id.transfert_dept);
        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);
        next_visit_date = (EditText) view.findViewById(R.id.next_visit_date);
        education_prescribe = (EditText) view.findViewById(R.id.education_prescribe);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);
        linearLayout=(LinearLayout) view.findViewById(R.id.linearLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_right:
                submit();
                break;
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            default:
                break;
        }
    }
//
//    private void selectAnamnesis() {
//        final boolean anamnesis_flag[] = new boolean[array_anamnesis.length];
//        anamnesis_code = "";
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setMultiChoiceItems(array_anamnesis, null, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                anamnesis_flag[which] = isChecked;
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
//                int length = anamnesis_flag.length;
//                String anamnesis_str = "";
//                for (int i = 0; i < length; i++) {
//                    if (anamnesis_flag[i]) {
//                        anamnesis_str += array_anamnesis[i] + ",";
//                        anamnesis_code += (i + 1) + ",";
//                    }
//                }
//                anamnesis_str = anamnesis_str.substring(0, anamnesis_str.length() - 1);
//                anamnesis_code = anamnesis_code.substring(0, anamnesis_code.length() - 1);
//                anamnesis.setText(anamnesis_str);
//            }
//        });
//        builder.setTitle("选择症状");
//        builder.show();
//    }

//    private void selectFamilyDisease() {
//        final boolean family_disease_flag[] = new boolean[array_family_disease.length];
//        family_disease_code = "";
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setMultiChoiceItems(array_family_disease, null, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                family_disease_flag[which] = isChecked;
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
//                int length = family_disease_flag.length;
//                String family_disease_str = "";
//                for (int i = 0; i < length; i++) {
//                    if (family_disease_flag[i]) {
//                        family_disease_str += array_family_disease[i] + ",";
//                        family_disease_code += (i + 1) + ",";
//                    }
//                }
//                family_disease_str = family_disease_str.substring(0, family_disease_str.length() - 1);
//                family_disease_code = family_disease_code.substring(0, family_disease_code.length() - 1);
//                anamnesis.setText(family_disease_str);
//            }
//        });
//        builder.setTitle("选择症状");
//        builder.show();
//    }

//    private void selectPersonalHistory() {
//        final boolean array_personal_history_flag[] = new boolean[array_personal_history.length];
//        personal_history_code = "";
//        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
//        builder.setMultiChoiceItems(array_family_disease, null, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                array_personal_history_flag[which] = isChecked;
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });

//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                int length = array_personal_history_flag.length;
//                String personal_history_str = "";
//                for (int i = 0; i < length; i++) {
//                    if (array_personal_history_flag[i]) {
//                        personal_history_str += array_family_disease[i] + ",";
//                        personal_history_code += (i + 1) + ",";
//                    }
//                }
//                personal_history_str = personal_history_str.substring(0, personal_history_str.length() - 1);
//                personal_history_code = personal_history_code.substring(0, personal_history_code.length() - 1);
//                anamnesis.setText(personal_history_str);
//            }
//        });
//        builder.setTitle("选择症状");
//        builder.show();
//    }

    private String buildJson() {
        PregnantAddDTO dto = new PregnantAddDTO();
        dto.setEhr_id(ehr_id);
        dto.setDb_id(GucNetEngineClient.DBID);
        dto.setCr_time("");
        dto.setCr_operator(GucApplication.loginUserCode);
        dto.setCr_org_code(GucNetEngineClient.ORG_CODE);
        dto.setCr_org_name(GucApplication.cr_org_name);
        dto.setVisit_doctor(StrUtil.getToTrim(visit_doctor));
        dto.setName(StrUtil.getToTrim(name));
        dto.setVisit_date(StrUtil.getToTrim(visit_date));
        dto.setRecord_code_enregister(record_code);
        dto.setCyesis(StrUtil.getToTrim(cyesis));
        dto.setParturient(StrUtil.getToTrim(parturient));
        dto.setEutocia(StrUtil.getToTrim(eutocia));
        dto.setCaesarean(StrUtil.getToTrim(caesarean));
        dto.setMenses_over_date(StrUtil.getToTrim(menses_over_date));
        dto.setDue_birth_date(StrUtil.getToTrim(due_birth_date));
        dto.setBirth_week(StrUtil.getToTrim(birth_week));
        dto.setBirth_week_days(StrUtil.getToTrim(birth_week_days));
        dto.setAnamnesis(StrUtil.getToTrim(anamnesis));
        dto.setAnamnesis_code(StrUtil.getString(anamnesis_code));
        dto.setAnamnesis_other(StrUtil.getToTrim(anamnesis_other));
        dto.setFamily_disease(StrUtil.getToTrim(family_disease));
        dto.setFamily_disease_code(StrUtil.getString(family_disease_code));
        dto.setFamily_disease_other(StrUtil.getToTrim(family_disease_other));
        dto.setPersonal_history(StrUtil.getToTrim(personal_history));
        dto.setPersonal_history_code(StrUtil.getString(personal_history_code));
        dto.setPersonal_history_other(StrUtil.getToTrim(personal_history_other));
        dto.setGynaecology_operation_mark(getSpinnerValue(gynaecology_operation_mark));
        dto.setGynaecology_operation(StrUtil.getToTrim(gynaecology_operation));
        dto.setGynaecology_other(StrUtil.getToTrim(gynaecology_other));
        dto.setAbort(StrUtil.getToTrim(abort));
        dto.setDie_foetus(StrUtil.getToTrim(die_foetus));
        dto.setDie_birth(StrUtil.getToTrim(die_birth));
        dto.setDie_newbron(StrUtil.getToTrim(die_newbron));
        dto.setHandicapped(StrUtil.getToTrim(handicapped));
        dto.setHeight(StrUtil.getToTrim(height));
        dto.setWeight(StrUtil.getToTrim(weight));
        dto.setSystolic_pressure(StrUtil.getToTrim(systolic_pressure));
        dto.setDiastolic_pressure(StrUtil.getToTrim(diastolic_pressure));
        dto.setHeart_result(getSpinnerValue(heart_result));
        dto.setHeart_str(StrUtil.getToTrim(heart_str));
        dto.setLung_result(getSpinnerValue(lung_result));
        dto.setLung_str(StrUtil.getToTrim(lung_str));
        dto.setPudendum_result(getSpinnerValue(pudendum_result));
        dto.setPudendum_str(StrUtil.getToTrim(pudendum_str));

        dto.setCunt_result(getSpinnerValue(cunt_result));
        dto.setCunt_str(StrUtil.getToTrim(cunt_str));

        dto.setCervical_result(getSpinnerValue(cervical_result));
        dto.setCervical_str(StrUtil.getToTrim(cervical_str));

        dto.setUterus_result(getSpinnerValue(uterus_result));
        dto.setUterus_str(StrUtil.getToTrim(uterus_str));

        dto.setAppendage_result(getSpinnerValue(appendage_result));
        dto.setAppendage_str(StrUtil.getToTrim(appendage_str));

        dto.setBlood_hb(StrUtil.getToTrim(blood_hb));
        dto.setLeukocyte(StrUtil.getToTrim(leukocyte));
        dto.setBlood_plt(StrUtil.getToTrim(blood_plt));
        dto.setBlood_other(StrUtil.getToTrim(blood_other));
        dto.setBlood_sugar(StrUtil.getToTrim(blood_sugar));
        dto.setUpro_value(StrUtil.getToTrim(upro_value));
        dto.setGlu_value(StrUtil.getToTrim(glu_value));
        String[] arrayAualitativeName = mActivity.getResources().getStringArray(R.array.array_qualitative_name);
        int qualitative_name_position = qualitative_name.getSelectedItemPosition();
        dto.setQualitative_name(arrayAualitativeName[qualitative_name_position]);
        String[] arrayOccultBloodName = mActivity.getResources().getStringArray(R.array.array_occultblood_name);
        int occultBloodNamePosition = occultblood_name.getSelectedItemPosition();
        dto.setOccultblood_name(arrayOccultBloodName[occultBloodNamePosition]);
        dto.setUrine_other(StrUtil.getToTrim(urine_other));
        dto.setSerum_alt(StrUtil.getToTrim(serum_alt));
        dto.setSerum_got(StrUtil.getToTrim(serum_got));
        dto.setAlbumin_chroma(StrUtil.getToTrim(albumin_chroma));
        dto.setTotal_bilirubin(StrUtil.getToTrim(total_bilirubin));
        dto.setConjugated_bilirubin(StrUtil.getToTrim(conjugated_bilirubin));
        dto.setBlood_creatinine(StrUtil.getToTrim(blood_creatinine));
        dto.setBlood_un(StrUtil.getToTrim(blood_un));
        dto.setHbs_ag(getSpinnerValue(hbs_ag));
        dto.setHbs_ab(getSpinnerValue(hbs_ab));
        dto.setHbe_ag(getSpinnerValue(hbe_ag));
        dto.setHbe_ab(getSpinnerValue(hbe_ab));
        dto.setHbc_ab(getSpinnerValue(hbc_ab));
        int cuntSecretionPosition = cunt_secretion.getSelectedItemPosition();
        String cuntSecretionArray[] = mActivity.getResources().getStringArray(R.array.array_cunt_secretion);
        dto.setCunt_secretion(cuntSecretionArray[cuntSecretionPosition]);
        dto.setCunt_secretion_code((cuntSecretionPosition + 1) + "");
        dto.setCunt_secretion_other(StrUtil.getToTrim(cunt_secretion_other));
        dto.setVagina_cleanliness(getSpinnerValue(vagina_cleanliness));
        dto.setSyphilis_serology(getSpinnerValue(syphilis_serology));
        dto.setHiv(getSpinnerValue(hiv));
        dto.setB_us(StrUtil.getToTrim(b_us));
        dto.setEvaluate_mark(getSpinnerValue(evaluate_mark));
        dto.setEvaluate(StrUtil.getToTrim(evaluate));
        dto.setHealth_guide_code(StrUtil.getString(health_guide_code));
        dto.setHealth_guide_str(StrUtil.getToTrim(health_guide_str));
        dto.setHealth_guide_other(StrUtil.getToTrim(health_guide_other));
        dto.setTransfert_mark(getSpinnerValue(transfert_mark));
        dto.setTransfert_dept(StrUtil.getToTrim(transfert_dept));
        dto.setTransfert_cause(StrUtil.getToTrim(transfert_cause));
        dto.setNext_visit_date(StrUtil.getToTrim(next_visit_date));
        dto.setEducation_prescribe(StrUtil.getToTrim(education_prescribe));
        return JSON.toJSONString(dto);
    }

    private void submit() {
        String json = buildJson();
        GucNetEngineClient.addMaternalBeforeOne(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.toString());
            }
        });
    }
//
//    public static PregnantAddFragment newInstance(String record_code) {
//        Bundle args = new Bundle();
//        args.putString("record_code", "record_code");
//        PregnantAddFragment fragment = new PregnantAddFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static PregnantAddFragment newInstance(HashMap<String, String> map) {
        Bundle args = new Bundle();
        args.putSerializable("map", map);
        PregnantAddFragment fragment = new PregnantAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
