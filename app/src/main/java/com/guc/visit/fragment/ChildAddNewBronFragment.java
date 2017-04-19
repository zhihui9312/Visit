package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
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


public class ChildAddNewBronFragment extends BaseFragment implements View.OnTouchListener {
    private EditText current_doctor;
    private EditText visit_date;
    private Spinner gdisease;
    private EditText gdisease_other;
    private Spinner asphyxia;
    private Spinner grade;
    private Spinner deformity;
    private EditText deformity_comments;
    private Spinner left_hearing_screen;
    private Spinner right_hearing_screen;
    private Spinner illness_screen;
    private EditText illness_other;
    private EditText currently_weight;
    private Spinner feed_manner_code;
    private EditText suckle_quantity;
    private EditText suckle_time;
    private Spinner puke;
    private Spinner defecate;
    private EditText defecate_time;
    private EditText temperature;
    private EditText pulse_rate;
    private EditText breathing_fn;
    private Spinner jaundice_part;
    private Spinner complexion;
    private EditText complexion_other;
    private EditText fontanel_x;
    private EditText fontanel_y;
    private Spinner fontanel_status;
    private EditText fontanel_other;
    private Spinner eye;
    private EditText eye_abn;
    private Spinner ear;
    private EditText ear_abn;
    private Spinner nose;
    private EditText nose_abn;
    private Spinner nonnasality;
    private EditText nonnasality_abn;
    private Spinner heart_lung;
    private EditText heart_lung_abn;
    private Spinner abdomen;
    private EditText abdomen_abn;
    private Spinner limbs;
    private EditText limbs_abn;

    private Spinner neck;
    private EditText neck_abn;

    private Spinner skin;
    private EditText skin_abn;

    private Spinner anus;
    private EditText anus_abn;

    private Spinner genitalia;
    private EditText genitalia_abn;

    private Spinner backbone;
    private EditText backbone_abn;

    private Spinner umbilical;
    private EditText umbilical_abn;

    private Spinner transfert;
    private EditText transfert_cause;
    private EditText transfert_org_name;
    private EditText guidance_memo;
    private StringBuilder guidance_mark = new StringBuilder("");
    private EditText next_visit_date;
    private EditText next_visit_site;
    private EditText education_prescribe;

    private TextView tv_right;
    private String ehr_id;
    private String name;
    private String sex;
    private String birth_date;
    private String operation;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_child_new_bron);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        HashMap<String, String> map = (HashMap<String, String>) bundle.getSerializable("map");
        operation = map.get("operation");
        name = map.get("name");
        if (operation.equals("1")) {
            String record_code = map.get("record_code");
            getNetworkData(record_code);
        } else {
            ehr_id = map.get("ehr_id");
            sex = map.get("sex");
            birth_date = bundle.getString("birth_date");
        }
        super.onCreate(savedInstanceState);
    }

    private void getNetworkData(final String record_code) {
        materialDialog= showIndeterminateProgressDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getChildNew(record_code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //getNewBronRecordResult
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getNewBronRecordResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONObject objInfo = objResult.getJSONObject("recordInfo");
                    ChildVisitAdd dto = JSON.parseObject(objInfo.toJSONString(), ChildVisitAdd.class);
                    updateUI(dto);
                } else {
                    showToast(errInfo);
                }
            }
        },null,materialDialog);
    }

    private void updateUI(ChildVisitAdd dto) {
        current_doctor.setText(GucApplication.visit_doctor);
        visit_date.setText(getFormatDateStr(dto.getVisit_date()));
        gdisease.setSelection(convertToInteger(dto.getGdisease()));
        gdisease_other.setText(dto.getGdisease_other());
        asphyxia.setSelection(convertToInteger(dto.getAsphyxia()));
        grade.setSelection(convertToInteger(dto.getGrade()));
        deformity.setSelection(convertToInteger(dto.getDeformity()));
        deformity_comments.setText(dto.getDeformity_comments());
        left_hearing_screen.setSelection(convertToInteger(dto.getLeft_hearing_screen()));
        right_hearing_screen.setSelection(convertToInteger(dto.getRight_hearing_screen()));
        illness_screen.setSelection(convertToInteger(dto.getIllness_screen()));
        illness_other.setText(dto.getIllness_other());
        currently_weight.setText(dto.getCurrently_weight());
        feed_manner_code.setSelection(convertToInteger(dto.getFeed_manner_code()));
        suckle_quantity.setText(dto.getSuckle_quantity());
        suckle_time.setText(dto.getSuckle_time());
        puke.setSelection(convertToInteger(dto.getPuke()));
        defecate.setSelection(convertToInteger(dto.getDefecate()));
        defecate_time.setText(dto.getDefecate_time());
        temperature.setText(dto.getTemperature());
        pulse_rate.setText(dto.getPulse_rate());
        breathing_fn.setText(dto.getBreathing_fn());
        jaundice_part.setSelection(convertToInteger(dto.getJaundice_part()));
        complexion.setSelection(convertToInteger(dto.getComplexion()));
        complexion_other.setText(dto.getComplexion_other());
        fontanel_x.setText(dto.getFontanel_x());
        fontanel_y.setText(dto.getFontanel_y());
        fontanel_status.setSelection(convertToInteger(dto.getFontanel_status()));
        fontanel_other.setText(dto.getFontanel_other());
        eye.setSelection(convertToInteger(dto.getEye()));
        eye_abn.setText(dto.getEye_abn());
        ear.setSelection(convertToInteger(dto.getEar()));
        ear_abn.setText(dto.getEar_abn());
        nose.setSelection(convertToInteger(dto.getNose()));
        nose_abn.setText(dto.getNose_abn());
        nonnasality.setSelection(convertToInteger(dto.getNonnasality()));
        nonnasality_abn.setText(dto.getNonnasality());
        heart_lung.setSelection(convertToInteger(dto.getHeart_lung()));
        heart_lung_abn.setText(dto.getHeart_lung_abn());
        abdomen.setSelection(convertToInteger(dto.getAbdomen()));
        abdomen_abn.setText(dto.getAbdomen());
        limbs.setSelection(convertToInteger(dto.getLimbs()));
        limbs_abn.setText(dto.getLimbs_abn());

        neck.setSelection(convertToInteger(dto.getNeck()));
        neck_abn.setText(dto.getNeck_abn());

        skin.setSelection(convertToInteger(dto.getSkin()));
        skin_abn.setText(dto.getSkin_abn());

        anus.setSelection(convertToInteger(dto.getAnus()));
        anus_abn.setText(dto.getAnus_abn());

        genitalia.setSelection(convertToInteger(dto.getGenitalia()));
        genitalia_abn.setText(dto.getGenitalia_abn());

        backbone.setSelection(convertToInteger(dto.getBackbone()));
        backbone_abn.setText(dto.getBackbone_abn());

        umbilical.setSelection(convertToInteger(dto.getUmbilical()));
        umbilical_abn.setText(dto.getUmbilical_abn());

        transfert.setSelection(convertToInteger(dto.getTransfert()));
        transfert_cause.setText(dto.getTransfert_cause());
        transfert_org_name.setText(dto.getTransfert_org_name());
        guidance_memo.setText(dto.getGuidance_memo());
        next_visit_date.setText(getFormatDateStr(dto.getNext_visit_date()));
        next_visit_site.setText(dto.getNext_visit_site());
        education_prescribe.setText(dto.getEducation_prescribe());
    }


    @Override
    protected void initData() {
        controlBar(R.string.child_new, R.string.back, true, true);
        if (operation.equals("0")) {
            initExisting();
        }else{
            ViewUtils.setAllViewEnable(linearLayout);
        }
    }

    private void initExisting() {
        current_doctor.setText(GucApplication.visit_doctor);
        String dateStr = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        visit_date.setText(dateStr);
        next_visit_date.setText(dateStr);
        tv_right.setText(R.string.commint);
    }

    @Override
    protected void setListeners() {
        guidance_memo.setOnTouchListener(this);
        next_visit_date.setOnTouchListener(this);
        visit_date.setOnTouchListener(this);
        ll_back.setOnClickListener(this);
        right_layout.setOnClickListener(this);
    }

    @Override
    protected void initWidget(View view) {
        current_doctor = (EditText) view.findViewById(R.id.current_doctor);
        visit_date = (EditText) view.findViewById(R.id.visit_date);
        gdisease = (Spinner) view.findViewById(R.id.gdisease);
        gdisease_other = (EditText) view.findViewById(R.id.gdisease_other);
        asphyxia = (Spinner) view.findViewById(R.id.asphyxia);
        grade = (Spinner) view.findViewById(R.id.grade);
        deformity = (Spinner) view.findViewById(R.id.deformity);
        deformity_comments = (EditText) view.findViewById(R.id.deformity_comments);
        left_hearing_screen = (Spinner) view.findViewById(R.id.left_hearing_screen);
        right_hearing_screen = (Spinner) view.findViewById(R.id.right_hearing_screen);
        illness_screen = (Spinner) view.findViewById(R.id.illness_screen);
        illness_other = (EditText) view.findViewById(R.id.illness_other);
        currently_weight = (EditText) view.findViewById(R.id.currently_weight);
        feed_manner_code = (Spinner) view.findViewById(R.id.feed_manner_code);
        suckle_quantity = (EditText) view.findViewById(R.id.suckle_quantity);
        suckle_time = (EditText) view.findViewById(R.id.suckle_time);
        puke = (Spinner) view.findViewById(R.id.puke);
        defecate = (Spinner) view.findViewById(R.id.defecate);
        defecate_time = (EditText) view.findViewById(R.id.defecate_time);
        temperature = (EditText) view.findViewById(R.id.temperature);
        pulse_rate = (EditText) view.findViewById(R.id.pulse_rate);
        breathing_fn = (EditText) view.findViewById(R.id.breathing_fn);
        jaundice_part = (Spinner) view.findViewById(R.id.jaundice_part);
        complexion = (Spinner) view.findViewById(R.id.complexion);
        complexion_other = (EditText) view.findViewById(R.id.complexion_other);
        fontanel_x = (EditText) view.findViewById(R.id.fontanel_x);
        fontanel_y = (EditText) view.findViewById(R.id.fontanel_y);
        fontanel_status = (Spinner) view.findViewById(R.id.fontanel_status);
        fontanel_other = (EditText) view.findViewById(R.id.fontanel_other);

        eye = (Spinner) view.findViewById(R.id.eye);
        eye_abn = (EditText) view.findViewById(R.id.eye_abn);

        ear = (Spinner) view.findViewById(R.id.ear);
        ear_abn = (EditText) view.findViewById(R.id.ear_abn);

        nose = (Spinner) view.findViewById(R.id.nose);
        nose_abn = (EditText) view.findViewById(R.id.nose_abn);

        nonnasality = (Spinner) view.findViewById(R.id.nonnasality);
        nonnasality_abn = (EditText) view.findViewById(R.id.nonnasality_abn);

        heart_lung = (Spinner) view.findViewById(R.id.heart_lung);
        heart_lung_abn = (EditText) view.findViewById(R.id.heart_lung_abn);

        abdomen = (Spinner) view.findViewById(R.id.abdomen);
        abdomen_abn = (EditText) view.findViewById(R.id.abdomen_abn);

        limbs = (Spinner) view.findViewById(R.id.limbs);
        limbs_abn = (EditText) view.findViewById(R.id.limbs_abn);

        neck = (Spinner) view.findViewById(R.id.neck);
        neck_abn = (EditText) view.findViewById(R.id.neck_abn);

        skin = (Spinner) view.findViewById(R.id.skin);
        skin_abn = (EditText) view.findViewById(R.id.skin_abn);

        anus = (Spinner) view.findViewById(R.id.anus);
        anus_abn = (EditText) view.findViewById(R.id.anus_abn);

        genitalia = (Spinner) view.findViewById(R.id.genitalia);
        genitalia_abn = (EditText) view.findViewById(R.id.genitalia_abn);

        backbone = (Spinner) view.findViewById(R.id.backbone);
        backbone_abn = (EditText) view.findViewById(R.id.backbone_abn);

        umbilical = (Spinner) view.findViewById(R.id.umbilical);
        umbilical_abn = (EditText) view.findViewById(R.id.umbilical_abn);

        transfert = (Spinner) view.findViewById(R.id.transfert);
        transfert_cause = (EditText) view.findViewById(R.id.transfert_cause);
        transfert_org_name = (EditText) view.findViewById(R.id.transfert_org_name);

        guidance_memo = (EditText) view.findViewById(R.id.guidance_memo);
        next_visit_date = (EditText) view.findViewById(R.id.next_visit_date);
        next_visit_site = (EditText) view.findViewById(R.id.next_visit_site);
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
        dto.setVisit_date(StrUtil.getToTrim(visit_date));
        dto.setName(StrUtil.getValue(name));
        dto.setSex(sex);
        dto.setBirth_date(birth_date);
        dto.setGdisease(getSpinnerValue(gdisease));
        dto.setGdisease_other(StrUtil.getToTrim(gdisease_other));
        dto.setAsphyxia(getSpinnerValue(asphyxia));
        dto.setGrade(getSpinnerValue(grade));
        dto.setDeformity(getSpinnerValue(deformity));
        dto.setDeformity_comments(StrUtil.getToTrim(deformity_comments));
        dto.setLeft_hearing_screen(getSpinnerValue(left_hearing_screen));
        dto.setRight_hearing_screen(getSpinnerValue(right_hearing_screen));
        dto.setIllness_screen(getSpinnerValue(illness_screen));
        dto.setIllness_other(StrUtil.getToTrim(illness_other));
        dto.setCurrently_weight(StrUtil.getToTrim(currently_weight));
        dto.setFeed_manner_code(getSpinnerValue(feed_manner_code));
        dto.setSuckle_quantity(StrUtil.getToTrim(suckle_quantity));
        dto.setSuckle_time(StrUtil.getToTrim(suckle_time));
        dto.setPuke(getSpinnerValue(puke));
        dto.setDefecate(getSpinnerValue(defecate));
        dto.setDefecate_time(StrUtil.getToTrim(defecate_time));
        dto.setTemperature(StrUtil.getToTrim(temperature));
        dto.setPulse_rate(StrUtil.getToTrim(pulse_rate));
        dto.setBreathing_fn(StrUtil.getToTrim(breathing_fn));
        dto.setJaundice_part(getSpinnerValue(jaundice_part));
        dto.setComplexion(getSpinnerValue(complexion));
        dto.setComplexion_other(StrUtil.getToTrim(complexion_other));
        dto.setFontanel_x(StrUtil.getToTrim(fontanel_x));
        dto.setFontanel_y(StrUtil.getToTrim(fontanel_y));
        dto.setFontanel_status(getSpinnerValue(fontanel_status));
        dto.setFontanel_other(StrUtil.getToTrim(fontanel_other));
        dto.setEye(getSpinnerValue(eye));
        dto.setEye_abn(StrUtil.getToTrim(eye_abn));
        dto.setEar(getSpinnerValue(ear));
        dto.setEar_abn(getToTrim(ear_abn));

        dto.setNose(getSpinnerValue(nose));
        dto.setNose_abn(getToTrim(nose_abn));
        dto.setNonnasality(getSpinnerValue(nonnasality));
        dto.setNonnasality_abn(getToTrim(nonnasality_abn));

        dto.setHeart_lung(getSpinnerValue(heart_lung));
        dto.setHeart_lung_abn(getToTrim(heart_lung_abn));
        dto.setAbdomen(getSpinnerValue(abdomen));
        dto.setAbdomen_abn(getToTrim(abdomen_abn));
        dto.setLimbs(getSpinnerValue(limbs));
        dto.setLimbs_abn(getToTrim(limbs_abn));
        dto.setNeck(getSpinnerValue(neck));
        dto.setNeck_abn(getToTrim(neck_abn));
        dto.setSkin(getSpinnerValue(skin));
        dto.setSkin_abn(getToTrim(skin_abn));
        dto.setAnus(getSpinnerValue(anus));
        dto.setAnus_abn(getToTrim(anus_abn));
        dto.setGenitalia(getSpinnerValue(genitalia));
        dto.setGenitalia_abn(getToTrim(genitalia_abn));
        dto.setBackbone(getSpinnerValue(backbone));
        dto.setBackbone_abn(getToTrim(backbone_abn));
        dto.setUmbilical(getSpinnerValue(umbilical));
        dto.setUmbilical_abn(getToTrim(umbilical_abn));
        dto.setTransfert_cause(getSpinnerValue(transfert));

        dto.setTransfert_cause(getToTrim(transfert_cause));
        dto.setTransfert_org_name(getToTrim(transfert_org_name));

        dto.setGuidance_memo(getToTrim(guidance_memo));
        dto.setGuidance_mark(StrUtil.getString(guidance_mark));
        dto.setNext_visit_date(getToTrim(next_visit_date));
        dto.setNext_visit_site(getToTrim(next_visit_site));
        dto.setEducation_prescribe(getToTrim(education_prescribe));
        return JSON.toJSONString(dto);
    }

    private void submit() {
        materialDialog= showIndeterminateProgressDialog(R.string.isSubmitting);
        String json = buildJson();
        GucNetEngineClient.addChildNew(json, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  {"UploadNewBronResult":{"result":true,"errInfo":null}}
                JSONObject jsonOjbect = JSON.parseObject(response);
                JSONObject objResult = jsonOjbect.getJSONObject("UploadNewBronResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    showToast(R.string.add_success);
                } else {
                    showToast(errInfo);
                }
            }
        },null,materialDialog);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.guidance_memo:
                    multiChoiceDialog(getIntArray(guidance_mark),R.array.array_guidance_memo, guidance_memo, guidance_mark);
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

    public static ChildAddNewBronFragment newInstance(HashMap<String, String> map) {

        Bundle args = new Bundle();
        args.putSerializable("map", map);
        ChildAddNewBronFragment fragment = new ChildAddNewBronFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
