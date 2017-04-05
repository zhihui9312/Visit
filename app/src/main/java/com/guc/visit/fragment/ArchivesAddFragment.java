package com.guc.visit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.ArchivesAddDTO;
import com.guc.visit.domain.Nation;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.StrUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ArchivesAddFragment extends BaseFragment implements View.OnTouchListener, AdapterView.OnItemSelectedListener {
    private AppCompatEditText name;
    private AppCompatSpinner sex;
    private AppCompatEditText birth_date;
    private AppCompatEditText crid_code;
    private AppCompatEditText work_unit;

    private AppCompatEditText telephone;
    private AppCompatEditText telephone_linkman;
    private AppCompatEditText telephone_no;
    private AppCompatEditText org_code;
    private AppCompatSpinner census_register;
    private AppCompatEditText nation_code;
    private AppCompatSpinner blood_type;
    private AppCompatSpinner blood_rh;
    private AppCompatSpinner education_code;
    private AppCompatSpinner job_type;
    private AppCompatSpinner marriage_code;
    private AppCompatSpinner pay_type;
    private AppCompatEditText pay_type_str;
    private AppCompatEditText irritability;
    private StringBuilder irritability_code = new StringBuilder("");
    private AppCompatEditText irritability_other;
    private AppCompatEditText expose;
    private StringBuilder expose_code = new StringBuilder("");

    private AppCompatSpinner disease1;
    private AppCompatEditText disease1_time;
    private AppCompatEditText disease1_other;
    private LinearLayoutCompat layout_disease1_other;

    private AppCompatSpinner disease2;
    private AppCompatEditText disease2_time;
    private AppCompatEditText disease2_other;
    private LinearLayoutCompat layout_disease2_other;

    private AppCompatSpinner disease3;
    private AppCompatEditText disease3_time;
    private AppCompatEditText disease3_other;
    private LinearLayoutCompat layout_disease3_other;

    private AppCompatSpinner disease4;
    private AppCompatEditText disease4_time;
    private AppCompatEditText disease4_other;
    private LinearLayoutCompat layout_disease4_other;

    private AppCompatSpinner disease5;
    private AppCompatEditText disease5_time;
    private AppCompatEditText disease5_other;
    private LinearLayoutCompat layout_disease5_other;

    private AppCompatSpinner disease6;
    private AppCompatEditText disease6_time;
    private AppCompatEditText disease6_other;
    private LinearLayoutCompat layout_disease6_other;

    private AppCompatSpinner operation1;
    private AppCompatEditText operation1_name;
    private AppCompatEditText operation1_time;

    private AppCompatSpinner operation2;
    private AppCompatEditText operation2_name;
    private AppCompatEditText operation2_time;

    private AppCompatSpinner trauma1;
    private AppCompatEditText trauma1_name;
    private AppCompatEditText trauma1_time;

    private AppCompatSpinner trauma2;
    private AppCompatEditText trauma2_name;
    private AppCompatEditText trauma2_time;

    private AppCompatSpinner transfuse1;
    private AppCompatEditText transfuse1_time;
    private AppCompatEditText transfuse1_name;

    private AppCompatSpinner transfuse2;
    private AppCompatEditText transfuse2_time;
    private AppCompatEditText transfuse2_name;

    private AppCompatEditText familys_f;
    private StringBuilder familys_f_code = new StringBuilder("");
    private AppCompatEditText familys_fother;

    private AppCompatEditText familys_m;
    private StringBuilder familys_m_code = new StringBuilder("");
    private AppCompatEditText familys_mother;

    private AppCompatEditText familys_s;
    private StringBuilder familys_s_code = new StringBuilder("");
    private AppCompatEditText familys_sother;

    private AppCompatEditText familys_c;
    private StringBuilder familys_c_code = new StringBuilder("");
    private AppCompatEditText familys_cother;

    private AppCompatSpinner hinherit;
    private AppCompatEditText hinherit_disease;

    private AppCompatSpinner deformity1;
    private AppCompatEditText deformity1_crid;

    private AppCompatSpinner deformity2;
    private AppCompatEditText deformity2_crid;

    private AppCompatSpinner deformity3;
    private AppCompatEditText deformity3_crid;

    private AppCompatSpinner deformity4;
    private AppCompatEditText deformity4_crid;

    private AppCompatSpinner deformity5;
    private AppCompatEditText deformity5_crid;

    private AppCompatSpinner deformity6;
    private AppCompatEditText deformity6_crid;

    private AppCompatSpinner exhaust_equipment;
    private AppCompatSpinner fuel_type;
    private AppCompatSpinner drinking_water;
    private AppCompatSpinner livestock_column;
    private AppCompatSpinner toilet;


    private TextView tv_right;
    private String nationCode;
    private String nationStr = "";
    private String organizationId = "";
    private String orgName = "";
    private ArrayList<Nation> nations = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_archives_add);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        nationStr = "汉族";
        nationCode = "01";
        List<Nation> temp = getFromAssets("nation.json");
        if (temp != null) {
            nations.addAll(temp);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        controlBar(R.string.archives_entry, R.string.back, true, true);
        org_code.setText(orgName);
        nation_code.setText(nationStr);
        tv_right.setText(R.string.commint);
    }

    private List<Nation> getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String result = "";
            while ((line = bufReader.readLine()) != null) {
                result += line;
            }
            return JSON.parseArray(result, Nation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void setListeners() {
        birth_date.setOnTouchListener(this);
        org_code.setOnTouchListener(this);
        nation_code.setOnTouchListener(this);
        irritability.setOnTouchListener(this);
        expose.setOnTouchListener(this);
        ll_back.setOnClickListener(this);
        familys_f.setOnTouchListener(this);
        familys_m.setOnTouchListener(this);
        familys_s.setOnTouchListener(this);
        familys_c.setOnTouchListener(this);
        right_layout.setOnClickListener(this);

        disease1.setOnItemSelectedListener(this);
        disease2.setOnItemSelectedListener(this);
        disease3.setOnItemSelectedListener(this);
        disease4.setOnItemSelectedListener(this);
        disease5.setOnItemSelectedListener(this);
        disease6.setOnItemSelectedListener(this);
        disease6.setOnItemSelectedListener(this);

        disease1_time.setOnTouchListener(this);
        disease2_time.setOnTouchListener(this);
        disease3_time.setOnTouchListener(this);
        disease4_time.setOnTouchListener(this);
        disease5_time.setOnTouchListener(this);
        disease6_time.setOnTouchListener(this);

        operation1_time.setOnTouchListener(this);
        operation2_time.setOnTouchListener(this);

        trauma1_time.setOnTouchListener(this);
        trauma2_time.setOnTouchListener(this);

        transfuse1_time.setOnTouchListener(this);
        transfuse2_time.setOnTouchListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 11) {
            switch (parent.getId()) {
                case R.id.disease1:
                    layout_disease1_other.setVisibility(View.VISIBLE);
                    break;
                case R.id.disease2:
                    layout_disease2_other.setVisibility(View.VISIBLE);
                    break;
                case R.id.disease3:
                    layout_disease3_other.setVisibility(View.VISIBLE);
                    break;
                case R.id.disease4:
                    layout_disease4_other.setVisibility(View.VISIBLE);
                    break;
                case R.id.disease5:
                    layout_disease5_other.setVisibility(View.VISIBLE);
                    break;
                case R.id.disease6:
                    layout_disease6_other.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onResume() {
        org_code.setText(orgName);
        super.onResume();
    }

    @Override
    protected void initWidget(View view) {
        name = (AppCompatEditText) view.findViewById(R.id.name);
        sex = (AppCompatSpinner) view.findViewById(R.id.sex);
        birth_date = (AppCompatEditText) view.findViewById(R.id.birth_date);
        crid_code = (AppCompatEditText) view.findViewById(R.id.crid_code);
        work_unit = (AppCompatEditText) view.findViewById(R.id.work_unit);
        telephone = (AppCompatEditText) view.findViewById(R.id.telephone);
        telephone_linkman = (AppCompatEditText) view.findViewById(R.id.telephone_linkman);
        telephone_no = (AppCompatEditText) view.findViewById(R.id.telephone_no);
        org_code = (AppCompatEditText) view.findViewById(R.id.org_code);
        census_register = (AppCompatSpinner) view.findViewById(R.id.census_register);
        nation_code = (AppCompatEditText) view.findViewById(R.id.nation_code);
        blood_type = (AppCompatSpinner) view.findViewById(R.id.blood_type);
        blood_rh = (AppCompatSpinner) view.findViewById(R.id.blood_rh);
        education_code = (AppCompatSpinner) view.findViewById(R.id.education_code);
        job_type = (AppCompatSpinner) view.findViewById(R.id.job_type);
        marriage_code = (AppCompatSpinner) view.findViewById(R.id.marriage_code);
        pay_type = (AppCompatSpinner) view.findViewById(R.id.pay_type);
        pay_type_str = (AppCompatEditText) view.findViewById(R.id.pay_type_str);
        irritability = (AppCompatEditText) view.findViewById(R.id.irritability);
        irritability_other = (AppCompatEditText) view.findViewById(R.id.irritability_other);
        expose = (AppCompatEditText) view.findViewById(R.id.expose);

        disease1 = (AppCompatSpinner) view.findViewById(R.id.disease1);
        disease1_time = (AppCompatEditText) view.findViewById(R.id.disease1_time);
        disease1_other = (AppCompatEditText) view.findViewById(R.id.disease1_other);
        layout_disease1_other = (LinearLayoutCompat) view.findViewById(R.id.layout_disease1_other);

        disease2 = (AppCompatSpinner) view.findViewById(R.id.disease2);
        disease2_time = (AppCompatEditText) view.findViewById(R.id.disease2_time);
        disease2_other = (AppCompatEditText) view.findViewById(R.id.disease2_other);
        layout_disease2_other = (LinearLayoutCompat) view.findViewById(R.id.layout_disease2_other);

        disease3 = (AppCompatSpinner) view.findViewById(R.id.disease3);
        disease3_time = (AppCompatEditText) view.findViewById(R.id.disease3_time);
        disease3_other = (AppCompatEditText) view.findViewById(R.id.disease3_other);
        layout_disease3_other = (LinearLayoutCompat) view.findViewById(R.id.layout_disease3_other);

        disease4 = (AppCompatSpinner) view.findViewById(R.id.disease4);
        disease4_time = (AppCompatEditText) view.findViewById(R.id.disease4_time);
        disease4_other = (AppCompatEditText) view.findViewById(R.id.disease4_other);
        layout_disease4_other = (LinearLayoutCompat) view.findViewById(R.id.layout_disease4_other);

        disease5 = (AppCompatSpinner) view.findViewById(R.id.disease5);
        disease5_time = (AppCompatEditText) view.findViewById(R.id.disease5_time);
        disease5_other = (AppCompatEditText) view.findViewById(R.id.disease5_other);
        layout_disease5_other = (LinearLayoutCompat) view.findViewById(R.id.layout_disease5_other);

        disease6 = (AppCompatSpinner) view.findViewById(R.id.disease6);
        disease6_time = (AppCompatEditText) view.findViewById(R.id.disease6_time);
        disease6_other = (AppCompatEditText) view.findViewById(R.id.disease6_other);
        layout_disease6_other = (LinearLayoutCompat) view.findViewById(R.id.layout_disease6_other);

        operation1 = (AppCompatSpinner) view.findViewById(R.id.operation1);
        operation1_name = (AppCompatEditText) view.findViewById(R.id.operation1_name);
        operation1_time = (AppCompatEditText) view.findViewById(R.id.operation1_time);

        operation2 = (AppCompatSpinner) view.findViewById(R.id.operation2);
        operation2_name = (AppCompatEditText) view.findViewById(R.id.operation2_name);
        operation2_time = (AppCompatEditText) view.findViewById(R.id.operation2_time);

        trauma1 = (AppCompatSpinner) view.findViewById(R.id.trauma1);
        trauma1_name = (AppCompatEditText) view.findViewById(R.id.trauma1_name);
        trauma1_time = (AppCompatEditText) view.findViewById(R.id.trauma1_time);

        trauma2 = (AppCompatSpinner) view.findViewById(R.id.trauma2);
        trauma2_name = (AppCompatEditText) view.findViewById(R.id.trauma2_name);
        trauma2_time = (AppCompatEditText) view.findViewById(R.id.trauma2_time);

        transfuse1 = (AppCompatSpinner) view.findViewById(R.id.transfuse1);
        transfuse1_time = (AppCompatEditText) view.findViewById(R.id.transfuse1_time);
        transfuse1_name = (AppCompatEditText) view.findViewById(R.id.transfuse1_name);

        transfuse2 = (AppCompatSpinner) view.findViewById(R.id.transfuse2);
        transfuse2_time = (AppCompatEditText) view.findViewById(R.id.transfuse2_time);
        transfuse2_name = (AppCompatEditText) view.findViewById(R.id.transfuse2_name);

        familys_f = (AppCompatEditText) view.findViewById(R.id.familys_f);
        familys_fother = (AppCompatEditText) view.findViewById(R.id.familys_fother);

        familys_m = (AppCompatEditText) view.findViewById(R.id.familys_m);
        familys_mother = (AppCompatEditText) view.findViewById(R.id.familys_mother);

        familys_s = (AppCompatEditText) view.findViewById(R.id.familys_s);
        familys_sother = (AppCompatEditText) view.findViewById(R.id.familys_sother);

        familys_c = (AppCompatEditText) view.findViewById(R.id.familys_c);
        familys_cother = (AppCompatEditText) view.findViewById(R.id.familys_cother);

        hinherit = (AppCompatSpinner) view.findViewById(R.id.hinherit);
        hinherit_disease = (AppCompatEditText) view.findViewById(R.id.hinherit_disease);

        deformity1 = (AppCompatSpinner) view.findViewById(R.id.deformity1);
        deformity1_crid = (AppCompatEditText) view.findViewById(R.id.deformity1_crid);

        deformity2 = (AppCompatSpinner) view.findViewById(R.id.deformity2);
        deformity2_crid = (AppCompatEditText) view.findViewById(R.id.deformity2_crid);

        deformity3 = (AppCompatSpinner) view.findViewById(R.id.deformity3);
        deformity3_crid = (AppCompatEditText) view.findViewById(R.id.deformity3_crid);

        deformity4 = (AppCompatSpinner) view.findViewById(R.id.deformity4);
        deformity4_crid = (AppCompatEditText) view.findViewById(R.id.deformity4_crid);

        deformity5 = (AppCompatSpinner) view.findViewById(R.id.deformity5);
        deformity5_crid = (AppCompatEditText) view.findViewById(R.id.deformity5_crid);

        deformity6 = (AppCompatSpinner) view.findViewById(R.id.deformity6);
        deformity6_crid = (AppCompatEditText) view.findViewById(R.id.deformity6_crid);

        exhaust_equipment = (AppCompatSpinner) view.findViewById(R.id.exhaust_equipment);
        fuel_type = (AppCompatSpinner) view.findViewById(R.id.fuel_type);
        drinking_water = (AppCompatSpinner) view.findViewById(R.id.drinking_water);
        livestock_column = (AppCompatSpinner) view.findViewById(R.id.livestock_column);
        toilet = (AppCompatSpinner) view.findViewById(R.id.toilet);

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
                checkData();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.birth_date:
                    showDatePicker(birth_date);
                    break;
                case R.id.org_code:
                    mActivity.replace("archivesOrganizationFragment", OrganizationFragment.newInstance(this, 1), true);
                    break;
                case R.id.nation_code:
                    List<String> list = new ArrayList<>();
                    for (Nation nation : nations) {
                        list.add(nation.getName());
                    }
                    new MaterialDialog.Builder(mActivity).title("选择民族").items(list).itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            Nation nation = nations.get(which);
                            nationCode = nation.getCode();
                            nationStr = nation.getName();
                            nation_code.setText(nation.getName());
                            return true;
                        }
                    }).positiveText(R.string.ok).show();
                    break;
                case R.id.irritability:
                    multiChoiceDialog(getIntArray(irritability_code), R.array.array_irritability, irritability, irritability_code);
                    break;
                case R.id.expose:
                    multiChoiceDialog(getIntArray(expose_code), R.array.array_expose, expose, expose_code);
                    break;
                case R.id.familys_f:
                    multiChoiceDialog(getIntArray(familys_f_code), R.array.array_familys, familys_f, familys_f_code);
                    break;
                case R.id.familys_m:
                    multiChoiceDialog(getIntArray(familys_m_code), R.array.array_familys, familys_m, familys_m_code);
                    break;
                case R.id.familys_s:
                    multiChoiceDialog(getIntArray(familys_s_code), R.array.array_familys, familys_s, familys_s_code);
                    break;
                case R.id.familys_c:
                    multiChoiceDialog(getIntArray(familys_c_code), R.array.array_familys, familys_c, familys_c_code);
                    break;
                case R.id.disease1_time:
                    showDatePicker(disease1_time);
                    break;
                case R.id.disease2_time:
                    showDatePicker(disease2_time);
                    break;
                case R.id.disease3_time:
                    showDatePicker(disease3_time);
                    break;
                case R.id.disease4_time:
                    showDatePicker(disease4_time);
                    break;
                case R.id.disease5_time:
                    showDatePicker(disease5_time);
                    break;
                case R.id.disease6_time:
                    showDatePicker(disease6_time);
                    break;
                case R.id.operation1_time:
                    showDatePicker(operation1_time);
                    break;
                case R.id.operation2_time:
                    showDatePicker(operation2_time);
                    break;
                case R.id.trauma1_time:
                    showDatePicker(trauma1_time);
                    break;
                case R.id.trauma2_time:
                    showDatePicker(trauma2_time);
                    break;
                case R.id.transfuse1_time:
                    showDatePicker(transfuse1_time);
                    break;
                case R.id.transfuse2_time:
                    showDatePicker(transfuse2_time);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    private void checkData() {
        String nameStr = name.getText().toString();
        if (StringUtils.isBlank(nameStr)) {
            showToast("姓名不可以为空！");
            return;
        }
        String birth_dateStr = birth_date.getText().toString();
        if (StringUtils.isBlank(birth_dateStr)) {
            showToast("出生日期不可以为空！");
            return;
        }
        String crid_codeStr = crid_code.getText().toString();
        if (StringUtils.isBlank(crid_codeStr)) {
            showToast("身份证号码不可以为空！");
            return;
        }
        if (StringUtils.isBlank(organizationId)) {
            showToast("请选择建档案机构！");
            return;
        }
        submit();
    }

    private String buildJson() {
        ArchivesAddDTO dto = new ArchivesAddDTO();
        dto.setName(getToTrim(name));
        dto.setSex(getSpinnerValue0(sex));
        dto.setBirth_date(getToTrim(birth_date));
        dto.setCrid_code(getToTrim(crid_code));
        dto.setWork_unit(getToTrim(work_unit));
        dto.setTelephone(getToTrim(telephone));
        dto.setTelephone_linkman(getToTrim(telephone_linkman));
        dto.setTelephone_no(getToTrim(telephone_no));

        dto.setOrg_code(organizationId);
        dto.setCensus_register(getSpinnerValue1(census_register));
        dto.setNation_code(nationCode);
        dto.setCr_org_name(GucApplication.cr_org_name);
        dto.setCr_org_code(GucNetEngineClient.ORG_CODE);
        dto.setCr_operator(GucApplication.loginUserCode);
        dto.setDb_id(GucNetEngineClient.DBID);
        dto.setBlood_type(getSpinnerValue0(blood_type));
        dto.setBlood_rh(getSpinnerValue1(blood_rh));
        dto.setEducation_code(getSpinnerValue1(education_code));
        dto.setMarriage_code(getSpinnerValue1(marriage_code));
        dto.setJob_type(getSpinnerValue1(job_type));
        dto.setPay_type(getSpinnerValue1(pay_type));
        dto.setPay_type_str(getToTrim(pay_type_str));
        dto.setIrritability(StrUtil.getString(irritability_code));
        dto.setIrritability_str(getToTrim(irritability));
        dto.setIrritability_other(getToTrim(irritability_other));
        dto.setExpose(StrUtil.getString(expose_code));

        dto.setDisease1(getSpinnerValue01(disease1));
        dto.setDisease1_other(getToTrim(disease1_other));
        dto.setDisease1_time(getToTrim(disease1_time));

        dto.setDisease2(getSpinnerValue01(disease2));
        dto.setDisease2_other(getToTrim(disease2_other));
        dto.setDisease2_time(getToTrim(disease2_time));

        dto.setDisease3(getSpinnerValue01(disease3));
        dto.setDisease3_other(getToTrim(disease3_other));
        dto.setDisease3_time(getToTrim(disease3_time));

        dto.setDisease4(getSpinnerValue01(disease4));
        dto.setDisease4_other(getToTrim(disease4_other));
        dto.setDisease4_time(getToTrim(disease4_time));

        dto.setDisease5(getSpinnerValue01(disease5));
        dto.setDisease5_other(getToTrim(disease5_other));
        dto.setDisease5_time(getToTrim(disease5_time));

        dto.setDisease6(getSpinnerValue01(disease6));
        dto.setDisease6_other(getToTrim(disease6_other));
        dto.setDisease6_time(getToTrim(disease6_time));

        dto.setOperation1(getSpinnerValue1(operation1));
        dto.setOperation1_name(getToTrim(operation1_name));
        dto.setOperation1_time(getToTrim(operation1_time));

        dto.setOperation2(getSpinnerValue1(operation2));
        dto.setOperation2_name(getToTrim(operation2_name));
        dto.setOperation2_time(getToTrim(operation2_time));

        dto.setTrauma1(getSpinnerValue1(trauma1));
        dto.setTrauma1_name(getToTrim(trauma1_name));
        dto.setTrauma1_time(getToTrim(trauma1_time));

        dto.setTrauma2(getSpinnerValue1(trauma2));
        dto.setTrauma2_name(getToTrim(trauma2_name));
        dto.setTrauma2_time(getToTrim(trauma2_time));

        dto.setTransfuse1(getSpinnerValue1(transfuse1));
        dto.setTransfuse1_name(getToTrim(transfuse1_name));
        dto.setTransfuse1_time(getToTrim(transfuse1_time));

        dto.setTransfuse2(getSpinnerValue1(transfuse2));
        dto.setTransfuse2_name(getToTrim(transfuse2_name));
        dto.setTransfuse2_time(getToTrim(transfuse2_time));

        dto.setFamilys_f(StrUtil.getString(familys_f_code));
        dto.setFamilys_f_str(getToTrim(familys_f));
        dto.setFamilys_fother(getToTrim(familys_fother));

        dto.setFamilys_m(StrUtil.getString(familys_m_code));
        dto.setFamilys_m_str(getToTrim(familys_m));
        dto.setFamilys_mother(getToTrim(familys_mother));

        dto.setFamilys_s(StrUtil.getString(familys_s_code));
        dto.setFamilys_s_str(getToTrim(familys_s));
        dto.setFamilys_sother(getToTrim(familys_sother));

        dto.setFamilys_c(StrUtil.getString(familys_c_code));
        dto.setFamilys_c_str(getToTrim(familys_c));
        dto.setFamilys_cother(getToTrim(familys_cother));

        dto.setHinherit(getSpinnerValue1(hinherit));
        dto.setHinherit_disease(getToTrim(hinherit_disease));

        dto.setExhaust_equipment(getSpinnerValue1(exhaust_equipment));
        dto.setFuel_type(getSpinnerValue1(fuel_type));
        dto.setDrinking_water(getSpinnerValue1(drinking_water));
        dto.setToilet(getSpinnerValue1(toilet));
        dto.setLivestock_column(getSpinnerValue1(livestock_column));

        dto.setDeformity1(getSpinnerValue1(deformity1));
        dto.setDeformity1_crid(getToTrim(deformity1_crid));

        dto.setDeformity2(getSpinnerValue1(deformity2));
        dto.setDeformity2_crid(getToTrim(deformity2_crid));

        dto.setDeformity3(getSpinnerValue1(deformity3));
        dto.setDeformity3_crid(getToTrim(deformity3_crid));

        dto.setDeformity4(getSpinnerValue1(deformity4));
        dto.setDeformity4_crid(getToTrim(deformity4_crid));

        dto.setDeformity5(getSpinnerValue1(deformity5));
        dto.setDeformity5_crid(getToTrim(deformity5_crid));

        dto.setDeformity6(getSpinnerValue1(deformity6));
        dto.setDeformity6_crid(getToTrim(deformity6_crid));

        return JSON.toJSONString(dto);
    }

    private void submit() {
        materialDialog = showIndeterminateProgressDialog(R.string.isSubmitting);
        String json = buildJson();
        GucNetEngineClient.archivesAdd(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("hrPersonBaseRegisterResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    showToast(R.string.add_success);
                } else {
                    showToast(errInfo);
                }
            }
        }, null, materialDialog);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            @SuppressWarnings("unchecked")
            HashMap<String, String> map = (HashMap<String, String>) data.getSerializableExtra("map");
            organizationId = map.get("organizationId");
            orgName = map.get("orgName");
        }
    }

    public static ArchivesAddFragment newInstance() {
        Bundle args = new Bundle();
        ArchivesAddFragment fragment = new ArchivesAddFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
