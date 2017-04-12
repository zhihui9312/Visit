package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.MedicalEntry;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;

import org.apache.commons.lang3.StringUtils;


public class MedicalEntryFragment extends BaseFragment implements View.OnTouchListener {
    private TextView tv_right;
    private AppCompatEditText mtest_date;
    private AppCompatEditText doctor_name;
    private AppCompatEditText symptom;
    private StringBuilder symptom_code = new StringBuilder("");
    private AppCompatEditText symptom_other;
    private AppCompatEditText temperature;
    private AppCompatEditText pulse_rate;
    private AppCompatEditText breathing_fn;
    private AppCompatEditText blood_pressure_left;
    private AppCompatEditText diastolic_pressure_left;
    private AppCompatEditText blood_pressure_right;
    private AppCompatEditText diastolic_pressure_right;
    private AppCompatEditText height;
    private AppCompatEditText weight;
    private AppCompatEditText waistline;
    private AppCompatSpinner old_health_status;
    private AppCompatSpinner old_lifeself;
    private AppCompatSpinner old_cognizefun;
    private AppCompatEditText old_cognizefun_grade;
    private AppCompatSpinner old_sensibility;
    private AppCompatEditText old_blahs_grade;

    private AppCompatSpinner physical_exercise_fre;
    private AppCompatEditText physical_exercise_minute;
    private AppCompatEditText physical_exercise_year;
    private AppCompatEditText exercise_way;

    private AppCompatSpinner diet_habit_abn;

    private AppCompatSpinner smoke_situation;
    private AppCompatEditText smoke_qty;
    private AppCompatEditText begin_smoke_age;
    private AppCompatEditText stop_smoke_age;

    private AppCompatSpinner drink_frequency;
    private AppCompatEditText drink_measure_day;
    private AppCompatSpinner if_stopdrink_flage;
    private AppCompatEditText stop_drink_age;
    private AppCompatEditText begin_drink_age;
    private AppCompatSpinner in_one_year_intoxication;
    private AppCompatEditText drink_type;
    private StringBuilder drink_type_abn = new StringBuilder("");

    private AppCompatSpinner job_mark;
    private AppCompatEditText job_occupation;
    private AppCompatEditText job_year;

    private AppCompatEditText dust;
    private AppCompatSpinner dust_protect;
    private AppCompatEditText dust_abn;

    private AppCompatEditText ray;
    private AppCompatSpinner ray_protect;
    private AppCompatEditText ray_abn;

    private AppCompatEditText pest;
    private AppCompatSpinner pest_protect;
    private AppCompatEditText pest_abn;

    private AppCompatEditText chemicla;
    private AppCompatSpinner chemicla_protect;
    private AppCompatEditText chemicla_abn;

    private AppCompatEditText toxicant_other;
    private AppCompatSpinner toxicant_other_protect;
    private AppCompatEditText toxicant_other_abn;
    private String ehr_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ehr_id = getArguments().getString("ehr_id");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_medical_entry);
    }

    @Override
    protected void initData() {
        controlBar(R.string.medicalEntry, R.string.back, true, true);
        tv_right.setText(R.string.commint);
        doctor_name.setText(GucApplication.visit_doctor);
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        symptom.setOnTouchListener(this);
        drink_type.setOnTouchListener(this);
        mtest_date.setOnTouchListener(this);
        right_layout.setOnClickListener(this);
    }

    @Override
    protected void initWidget(View view) {
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        view.findViewById(R.id.iv_add).setVisibility(View.GONE);

        mtest_date = (AppCompatEditText) view.findViewById(R.id.mtest_date);
        doctor_name = (AppCompatEditText) view.findViewById(R.id.doctor_name);
        symptom = (AppCompatEditText) view.findViewById(R.id.symptom);
        symptom_other = (AppCompatEditText) view.findViewById(R.id.symptom_other);
        temperature = (AppCompatEditText) view.findViewById(R.id.temperature);
        pulse_rate = (AppCompatEditText) view.findViewById(R.id.pulse_rate);
        breathing_fn = (AppCompatEditText) view.findViewById(R.id.breathing_fn);
        blood_pressure_left = (AppCompatEditText) view.findViewById(R.id.blood_pressure_left);
        diastolic_pressure_left = (AppCompatEditText) view.findViewById(R.id.diastolic_pressure_left);
        blood_pressure_right = (AppCompatEditText) view.findViewById(R.id.blood_pressure_right);
        diastolic_pressure_right = (AppCompatEditText) view.findViewById(R.id.diastolic_pressure_right);
        height = (AppCompatEditText) view.findViewById(R.id.height);
        weight = (AppCompatEditText) view.findViewById(R.id.weight);
        waistline = (AppCompatEditText) view.findViewById(R.id.waistline);
        old_health_status = (AppCompatSpinner) view.findViewById(R.id.old_health_status);
        old_lifeself = (AppCompatSpinner) view.findViewById(R.id.old_lifeself);
        old_cognizefun = (AppCompatSpinner) view.findViewById(R.id.old_cognizefun);
        old_cognizefun_grade = (AppCompatEditText) view.findViewById(R.id.old_cognizefun_grade);
        old_sensibility = (AppCompatSpinner) view.findViewById(R.id.old_sensibility);
        old_blahs_grade = (AppCompatEditText) view.findViewById(R.id.old_blahs_grade);

        physical_exercise_fre = (AppCompatSpinner) view.findViewById(R.id.physical_exercise_fre);
        physical_exercise_minute = (AppCompatEditText) view.findViewById(R.id.physical_exercise_minute);
        physical_exercise_year = (AppCompatEditText) view.findViewById(R.id.physical_exercise_year);
        exercise_way = (AppCompatEditText) view.findViewById(R.id.exercise_way);

        diet_habit_abn = (AppCompatSpinner) view.findViewById(R.id.diet_habit_abn);

        smoke_situation = (AppCompatSpinner) view.findViewById(R.id.smoke_situation);
        smoke_qty = (AppCompatEditText) view.findViewById(R.id.smoke_qty);
        begin_smoke_age = (AppCompatEditText) view.findViewById(R.id.begin_smoke_age);
        stop_smoke_age = (AppCompatEditText) view.findViewById(R.id.stop_smoke_age);

        drink_frequency = (AppCompatSpinner) view.findViewById(R.id.drink_frequency);
        drink_measure_day = (AppCompatEditText) view.findViewById(R.id.drink_measure_day);
        if_stopdrink_flage = (AppCompatSpinner) view.findViewById(R.id.if_stopdrink_flage);
        stop_drink_age = (AppCompatEditText) view.findViewById(R.id.stop_drink_age);
        begin_drink_age = (AppCompatEditText) view.findViewById(R.id.begin_drink_age);
        in_one_year_intoxication = (AppCompatSpinner) view.findViewById(R.id.in_one_year_intoxication);
        drink_type = (AppCompatEditText) view.findViewById(R.id.drink_type);


        job_mark = (AppCompatSpinner) view.findViewById(R.id.job_mark);
        job_occupation = (AppCompatEditText) view.findViewById(R.id.job_occupation);
        job_year = (AppCompatEditText) view.findViewById(R.id.job_year);

        dust = (AppCompatEditText) view.findViewById(R.id.dust);
        dust_protect = (AppCompatSpinner) view.findViewById(R.id.dust_protect);
        dust_abn = (AppCompatEditText) view.findViewById(R.id.dust_abn);

        ray = (AppCompatEditText) view.findViewById(R.id.ray);
        ray_protect = (AppCompatSpinner) view.findViewById(R.id.ray_protect);
        ray_abn = (AppCompatEditText) view.findViewById(R.id.ray_abn);

        pest = (AppCompatEditText) view.findViewById(R.id.pest);
        pest_protect = (AppCompatSpinner) view.findViewById(R.id.pest_protect);
        pest_abn = (AppCompatEditText) view.findViewById(R.id.pest_abn);

        chemicla = (AppCompatEditText) view.findViewById(R.id.chemicla);
        chemicla_protect = (AppCompatSpinner) view.findViewById(R.id.chemicla_protect);
        chemicla_abn = (AppCompatEditText) view.findViewById(R.id.chemicla_abn);

        toxicant_other = (AppCompatEditText) view.findViewById(R.id.toxicant_other);
        toxicant_other_protect = (AppCompatSpinner) view.findViewById(R.id.toxicant_other_protect);
        toxicant_other_abn = (AppCompatEditText) view.findViewById(R.id.toxicant_other_abn);
    }

    private String buildJson() {
        MedicalEntry dto = new MedicalEntry();
        dto.setEhr_id(ehr_id);
        dto.setMtest_date(getToTrim(mtest_date));
        dto.setCr_time("");
        dto.setCr_operator(GucApplication.loginUserCode);
        dto.setRecord_code("");
        dto.setDb_id(GucNetEngineClient.DBID);
        dto.setCr_org_code(GucNetEngineClient.ORG_CODE);
        dto.setCr_org_name(GucApplication.cr_org_name);
        dto.setMtest_date(getToTrim(mtest_date));
        dto.setDoctor_name(getToTrim(doctor_name));
        dto.setSymptom(StrUtil.getString(symptom_code));
        dto.setSymptom_str(getToTrim(symptom));
        dto.setSymptom_other(getToTrim(symptom_other));
        dto.setTemperature(getToTrim(temperature));
        dto.setPulse_rate(getToTrim(pulse_rate));
        dto.setBreathing_fn(getToTrim(breathing_fn));
        dto.setBlood_pressure_left(getToTrim(blood_pressure_left));
        dto.setDiastolic_pressure_left(getToTrim(diastolic_pressure_left));
        dto.setBlood_pressure_right(getToTrim(blood_pressure_right));
        dto.setDiastolic_pressure_right(getToTrim(diastolic_pressure_right));
        dto.setHeight(getToTrim(height));
        dto.setWeight(getToTrim(weight));
        dto.setWaistline(getToTrim(waistline));
        dto.setOld_health_status(getSpinnerValue1(old_health_status));
        dto.setOld_lifeself(getSpinnerValue1(old_lifeself));
        dto.setOld_cognizefun(getSpinnerValue1(old_cognizefun));
        dto.setOld_cognizefun_grade(getToTrim(old_cognizefun_grade));
        dto.setOld_sensibility(getSpinnerValue1(old_sensibility));
        dto.setOld_blahs_grade(getToTrim(old_blahs_grade));
        dto.setPhysical_exercise_fre(getSpinnerValue1(physical_exercise_fre));
        dto.setPhysical_exercise_minute(getToTrim(physical_exercise_minute));
        dto.setPhysical_exercise_year(getToTrim(physical_exercise_year));
        dto.setExercise_way(getToTrim(exercise_way));
        dto.setDiet_habit(getResources().getStringArray(R.array.array_diet_habit_abn)[diet_habit_abn.getSelectedItemPosition()]);
        dto.setDiet_habit_abn(getSpinnerValue1(diet_habit_abn)+",");
        dto.setSmoke_situation(getSpinnerValue1(smoke_situation));
        dto.setSmoke_qty(getToTrim(smoke_qty));
        dto.setBegin_smoke_age(getToTrim(begin_smoke_age));
        dto.setStop_smoke_age(getToTrim(stop_smoke_age));
        dto.setDrink_frequency(getSpinnerValue1(drink_frequency));
        dto.setDrink_measure_day(getToTrim(drink_measure_day));
        dto.setIf_stopdrink_flage(getSpinnerValue1(if_stopdrink_flage));
        dto.setStop_drink_age(getToTrim(stop_drink_age));
        dto.setBegin_drink_age(getToTrim(begin_drink_age));
        dto.setIn_one_year_intoxication(getSpinnerValue1(in_one_year_intoxication));
        dto.setDrink_type_abn(StrUtil.getString(drink_type_abn));
        dto.setDrink_type(getToTrim(drink_type));
        dto.setJob_mark(getSpinnerValue1(job_mark));
        dto.setJob_occupation(getToTrim(job_occupation));
        dto.setJob_year(getToTrim(job_year));

        dto.setDust(getToTrim(dust));
        dto.setDust_protect(getSpinnerValue(dust_protect));
        dto.setDust_abn(getToTrim(dust_abn));

        dto.setRay(getToTrim(ray));
        dto.setRay_protect(getSpinnerValue(ray_protect));
        dto.setRay_abn(getToTrim(ray_abn));

        dto.setPest(getToTrim(pest));
        dto.setPest_protect(getSpinnerValue(pest_protect));
        dto.setPest_abn(getToTrim(pest_abn));

        dto.setChemicla(getToTrim(chemicla));
        dto.setChemicla_protect(getSpinnerValue(chemicla_protect));
        dto.setChemicla_abn(getToTrim(chemicla_abn));

        dto.setToxicant_other(getToTrim(toxicant_other));
        dto.setToxicant_other_protect(getSpinnerValue(toxicant_other_protect));
        dto.setToxicant_other_abn(getToTrim(toxicant_other_abn));

        return JSON.toJSONString(dto);
    }

    private void submit() {
        materialDialog = showIndeterminateProgressDialog(R.string.isSubmitting);
        String json = buildJson();
        GucNetEngineClient.addMedicalEntry(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.e("TAG", response);
                // {"UploadMsPhysicalExaminationResult":{"result":"CPE20170000166242","errInfo":null}}
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("UploadMsPhysicalExaminationResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    showToast(R.string.add_success);
                } else {
                    showToast(errInfo);
                }
            }
        }, null,materialDialog);
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
                case R.id.symptom:
                    multiChoiceDialog(getIntArray(symptom_code),R.array.array_symptom, symptom, symptom_code);
                    break;
                case R.id.drink_type:
                    multiChoiceDialog(getIntArray(drink_type_abn),R.array.array_drink_type, drink_type, drink_type_abn);
                    break;
                case R.id.mtest_date:
                    showDatePicker(mtest_date);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    public static MedicalEntryFragment newInstance(String ehr_id) {
        Bundle args = new Bundle();
        args.putString("ehr_id", ehr_id);
        MedicalEntryFragment fragment = new MedicalEntryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
