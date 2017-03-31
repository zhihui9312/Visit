package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;


public class ChildVisitAdd extends BaseDTO {
    private String ehr_id;
    private String db_id;
    private String cr_time;
    private String cr_operator;
    private String cr_org_code;
    private String cr_org_name;
    private String current_doctor;
    private String visit_date;
    private String name;
    private String sex;
    private String birth_date;
    private String gdisease;
    private String gdisease_other;
    private String asphyxia;
    private String grade;
    private String deformity;
    private String deformity_comments;
    private String left_hearing_screen;
    private String right_hearing_screen;
    private String illness_screen;
    private String illness_other;
    private String currently_weight;
    private String feed_manner_code;
    private String suckle_quantity;
    private String suckle_time;
    private String puke;
    private String defecate;
    private String defecate_time;
    private String temperature;
    private String pulse_rate;
    private String breathing_fn;
    private String jaundice_part;
    private String complexion;
    private String complexion_other;
    private String fontanel_x;
    private String fontanel_y;
    private String fontanel_status;
    private String fontanel_other;
    private String eye;
    private String eye_abn;
    private String ear;
    private String ear_abn;
    private String nose;
    private String nose_abn;
    private String nonnasality;
    private String nonnasality_abn;
    private String heart_lung;
    private String heart_lung_abn;
    private String abdomen;
    private String abdomen_abn;
    private String limbs;
    private String limbs_abn;
    private String neck;
    private String neck_abn;
    private String skin;
    private String skin_abn;
    private String anus;
    private String anus_abn;
    private String genitalia;
    private String genitalia_abn;
    private String backbone;
    private String backbone_abn;
    private String umbilical;
    private String umbilical_abn;
    private String transfert;
    private String transfert_cause;
    private String transfert_org_name;
    private String guidance_memo;
    private String guidance_mark;
    private String next_visit_date;
    private String next_visit_site;
    private String education_prescribe;

    private String current_agem;
    private String auto_agem;
    private String head_girth;
    private String weight;
    private String weight_grade;
    private String height;
    private String height_grade;
    private String neck_masses;
    private String fontanel_close;
    private String ear_result;
    private String ear_result_abn;
    private String tooth_start_num;
    private String rachitis_symptom;
    private String rachitis_symptom_mark;
    private String rachitis_sign;
    private String rachitis_sign_mark;
    private String rachitis_other;
    private String anus_genitalia;
    private String anus_genitalia_abn;
    private String outdoor_activities;
    private String blood_hb;
    private String vitamin_amount;
    private String growth;
    private String growth_con;
    private String between_disease;
    private String between_disease_con;
    private String guidance_con;
    private String guidance_other_exp;

    private String tooth_decayed;
    private String gait;
    private String gait_abn;

    private String visit_doctor;
    private String physique;
    private String physique_mark;
    private String ue_myopia_left;
    private String ue_myopia_right;
    private String tooth;
    private String complexion_abn;
    private String liver_spleen;
    private String liver_spleen_sbn;
    private String growth_action;
    private String growth_action_abn;
    private String growth_gam;
    private String growth_gam_abn;

    private String irritability;
    private String irritability_con;

    private String pneumonia_memo;
    private String pneumonia_mark;
    private String is_pneumonia_sum;
    private String is_diarrheaih_sum;
    private String is_traumaih_sum;
    private String is_other_con;
    private String other;
    private String current_agey;

    public String getCurrent_agey() {
        return current_agey;
    }

    public void setCurrent_agey(String current_agey) {
        this.current_agey = current_agey;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getLiver_spleen() {
        return liver_spleen;
    }

    public void setLiver_spleen(String liver_spleen) {
        this.liver_spleen = liver_spleen;
    }

    public String getLiver_spleen_sbn() {
        return liver_spleen_sbn;
    }

    public void setLiver_spleen_sbn(String liver_spleen_sbn) {
        this.liver_spleen_sbn = liver_spleen_sbn;
    }

    public String getGrowth_action() {
        return growth_action;
    }

    public void setGrowth_action(String growth_action) {
        this.growth_action = growth_action;
    }

    public String getGrowth_action_abn() {
        return growth_action_abn;
    }

    public void setGrowth_action_abn(String growth_action_abn) {
        this.growth_action_abn = growth_action_abn;
    }

    public String getGrowth_gam() {
        return growth_gam;
    }

    public void setGrowth_gam(String growth_gam) {
        this.growth_gam = growth_gam;
    }

    public String getGrowth_gam_abn() {
        return growth_gam_abn;
    }

    public void setGrowth_gam_abn(String growth_gam_abn) {
        this.growth_gam_abn = growth_gam_abn;
    }

    public String getIrritability() {
        return irritability;
    }

    public void setIrritability(String irritability) {
        this.irritability = irritability;
    }

    public String getIrritability_con() {
        return irritability_con;
    }

    public void setIrritability_con(String irritability_con) {
        this.irritability_con = irritability_con;
    }

    public String getPneumonia_memo() {
        return pneumonia_memo;
    }

    public void setPneumonia_memo(String pneumonia_memo) {
        this.pneumonia_memo = pneumonia_memo;
    }

    public String getPneumonia_mark() {
        return pneumonia_mark;
    }

    public void setPneumonia_mark(String pneumonia_mark) {
        this.pneumonia_mark = pneumonia_mark;
    }

    public String getIs_pneumonia_sum() {
        return is_pneumonia_sum;
    }

    public void setIs_pneumonia_sum(String is_pneumonia_sum) {
        this.is_pneumonia_sum = is_pneumonia_sum;
    }

    public String getIs_diarrheaih_sum() {
        return is_diarrheaih_sum;
    }

    public void setIs_diarrheaih_sum(String is_diarrheaih_sum) {
        this.is_diarrheaih_sum = is_diarrheaih_sum;
    }

    public String getIs_traumaih_sum() {
        return is_traumaih_sum;
    }

    public void setIs_traumaih_sum(String is_traumaih_sum) {
        this.is_traumaih_sum = is_traumaih_sum;
    }

    public String getIs_other_con() {
        return is_other_con;
    }

    public void setIs_other_con(String is_other_con) {
        this.is_other_con = is_other_con;
    }

    public String getComplexion_abn() {
        return complexion_abn;
    }

    public void setComplexion_abn(String complexion_abn) {
        this.complexion_abn = complexion_abn;
    }

    public String getPhysique() {
        return physique;
    }

    public void setPhysique(String physique) {
        this.physique = physique;
    }

    public String getPhysique_mark() {
        return physique_mark;
    }

    public void setPhysique_mark(String physique_mark) {
        this.physique_mark = physique_mark;
    }

    public String getUe_myopia_left() {
        return ue_myopia_left;
    }

    public void setUe_myopia_left(String ue_myopia_left) {
        this.ue_myopia_left = ue_myopia_left;
    }

    public String getUe_myopia_right() {
        return ue_myopia_right;
    }

    public void setUe_myopia_right(String ue_myopia_right) {
        this.ue_myopia_right = ue_myopia_right;
    }

    public String getTooth() {
        return tooth;
    }

    public void setTooth(String tooth) {
        this.tooth = tooth;
    }

    public String getVisit_doctor() {
        return visit_doctor;
    }

    public void setVisit_doctor(String visit_doctor) {
        this.visit_doctor = visit_doctor;
    }

    public String getTooth_decayed() {
        return tooth_decayed;
    }

    public void setTooth_decayed(String tooth_decayed) {
        this.tooth_decayed = tooth_decayed;
    }

    public String getGait() {
        return gait;
    }

    public void setGait(String gait) {
        this.gait = gait;
    }

    public String getGait_abn() {
        return gait_abn;
    }

    public void setGait_abn(String gait_abn) {
        this.gait_abn = gait_abn;
    }

    public String getGuidance_other_exp() {
        return guidance_other_exp;
    }

    public void setGuidance_other_exp(String guidance_other_exp) {
        this.guidance_other_exp = guidance_other_exp;
    }

    public String getGuidance_con() {
        return guidance_con;
    }

    public void setGuidance_con(String guidance_con) {
        this.guidance_con = guidance_con;
    }

    public String getGrowth_con() {
        return growth_con;
    }

    public void setGrowth_con(String growth_con) {
        this.growth_con = growth_con;
    }

    public String getBetween_disease() {
        return between_disease;
    }

    public void setBetween_disease(String between_disease) {
        this.between_disease = between_disease;
    }

    public String getBetween_disease_con() {
        return between_disease_con;
    }

    public void setBetween_disease_con(String between_disease_con) {
        this.between_disease_con = between_disease_con;
    }

    public String getBlood_hb() {
        return blood_hb;
    }

    public void setBlood_hb(String blood_hb) {
        this.blood_hb = blood_hb;
    }

    public String getVitamin_amount() {
        return vitamin_amount;
    }

    public void setVitamin_amount(String vitamin_amount) {
        this.vitamin_amount = vitamin_amount;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public String getRachitis_symptom() {
        return rachitis_symptom;
    }

    public void setRachitis_symptom(String rachitis_symptom) {
        this.rachitis_symptom = rachitis_symptom;
    }

    public String getRachitis_symptom_mark() {
        return rachitis_symptom_mark;
    }

    public void setRachitis_symptom_mark(String rachitis_symptom_mark) {
        this.rachitis_symptom_mark = rachitis_symptom_mark;
    }

    public String getRachitis_sign() {
        return rachitis_sign;
    }

    public void setRachitis_sign(String rachitis_sign) {
        this.rachitis_sign = rachitis_sign;
    }

    public String getRachitis_sign_mark() {
        return rachitis_sign_mark;
    }

    public void setRachitis_sign_mark(String rachitis_sign_mark) {
        this.rachitis_sign_mark = rachitis_sign_mark;
    }

    public String getRachitis_other() {
        return rachitis_other;
    }

    public void setRachitis_other(String rachitis_other) {
        this.rachitis_other = rachitis_other;
    }

    public String getAnus_genitalia() {
        return anus_genitalia;
    }

    public void setAnus_genitalia(String anus_genitalia) {
        this.anus_genitalia = anus_genitalia;
    }

    public String getAnus_genitalia_abn() {
        return anus_genitalia_abn;
    }

    public void setAnus_genitalia_abn(String anus_genitalia_abn) {
        this.anus_genitalia_abn = anus_genitalia_abn;
    }

    public String getOutdoor_activities() {
        return outdoor_activities;
    }

    public void setOutdoor_activities(String outdoor_activities) {
        this.outdoor_activities = outdoor_activities;
    }

    public String getEar_result() {
        return ear_result;
    }

    public void setEar_result(String ear_result) {
        this.ear_result = ear_result;
    }

    public String getEar_result_abn() {
        return ear_result_abn;
    }

    public void setEar_result_abn(String ear_result_abn) {
        this.ear_result_abn = ear_result_abn;
    }

    public String getTooth_start_num() {
        return tooth_start_num;
    }

    public void setTooth_start_num(String tooth_start_num) {
        this.tooth_start_num = tooth_start_num;
    }

    public String getFontanel_close() {
        return fontanel_close;
    }

    public void setFontanel_close(String fontanel_close) {
        this.fontanel_close = fontanel_close;
    }

    public String getNeck_masses() {
        return neck_masses;
    }

    public void setNeck_masses(String neck_masses) {
        this.neck_masses = neck_masses;
    }

    public String getHead_girth() {
        return head_girth;
    }

    public void setHead_girth(String head_girth) {
        this.head_girth = head_girth;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight_grade() {
        return weight_grade;
    }

    public void setWeight_grade(String weight_grade) {
        this.weight_grade = weight_grade;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHeight_grade() {
        return height_grade;
    }

    public void setHeight_grade(String height_grade) {
        this.height_grade = height_grade;
    }

    public String getCurrent_agem() {
        return current_agem;
    }

    public void setCurrent_agem(String current_agem) {
        this.current_agem = current_agem;
    }

    public String getAuto_agem() {
        return auto_agem;
    }

    public void setAuto_agem(String auto_agem) {
        this.auto_agem = auto_agem;
    }

    public String getEhr_id() {
        return ehr_id;
    }

    public void setEhr_id(String ehr_id) {
        this.ehr_id = ehr_id;
    }

    public String getDb_id() {
        return db_id;
    }

    public void setDb_id(String db_id) {
        this.db_id = db_id;
    }

    public String getCr_time() {
        return cr_time;
    }

    public void setCr_time(String cr_time) {
        this.cr_time = cr_time;
    }

    public String getCr_operator() {
        return cr_operator;
    }

    public void setCr_operator(String cr_operator) {
        this.cr_operator = cr_operator;
    }

    public String getCr_org_code() {
        return cr_org_code;
    }

    public void setCr_org_code(String cr_org_code) {
        this.cr_org_code = cr_org_code;
    }

    public String getCr_org_name() {
        return cr_org_name;
    }

    public void setCr_org_name(String cr_org_name) {
        this.cr_org_name = cr_org_name;
    }

    public String getCurrent_doctor() {
        return current_doctor;
    }

    public void setCurrent_doctor(String current_doctor) {
        this.current_doctor = current_doctor;
    }

    public String getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(String visit_date) {
        this.visit_date = visit_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getGdisease() {
        return gdisease;
    }

    public void setGdisease(String gdisease) {
        this.gdisease = gdisease;
    }

    public String getGdisease_other() {
        return gdisease_other;
    }

    public void setGdisease_other(String gdisease_other) {
        this.gdisease_other = gdisease_other;
    }

    public String getAsphyxia() {
        return asphyxia;
    }

    public void setAsphyxia(String asphyxia) {
        this.asphyxia = asphyxia;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDeformity() {
        return deformity;
    }

    public void setDeformity(String deformity) {
        this.deformity = deformity;
    }

    public String getDeformity_comments() {
        return deformity_comments;
    }

    public void setDeformity_comments(String deformity_comments) {
        this.deformity_comments = deformity_comments;
    }

    public String getLeft_hearing_screen() {
        return left_hearing_screen;
    }

    public void setLeft_hearing_screen(String left_hearing_screen) {
        this.left_hearing_screen = left_hearing_screen;
    }

    public String getRight_hearing_screen() {
        return right_hearing_screen;
    }

    public void setRight_hearing_screen(String right_hearing_screen) {
        this.right_hearing_screen = right_hearing_screen;
    }

    public String getIllness_screen() {
        return illness_screen;
    }

    public void setIllness_screen(String illness_screen) {
        this.illness_screen = illness_screen;
    }

    public String getIllness_other() {
        return illness_other;
    }

    public void setIllness_other(String illness_other) {
        this.illness_other = illness_other;
    }

    public String getCurrently_weight() {
        return currently_weight;
    }

    public void setCurrently_weight(String currently_weight) {
        this.currently_weight = currently_weight;
    }

    public String getFeed_manner_code() {
        return feed_manner_code;
    }

    public void setFeed_manner_code(String feed_manner_code) {
        this.feed_manner_code = feed_manner_code;
    }

    public String getSuckle_quantity() {
        return suckle_quantity;
    }

    public void setSuckle_quantity(String suckle_quantity) {
        this.suckle_quantity = suckle_quantity;
    }

    public String getSuckle_time() {
        return suckle_time;
    }

    public void setSuckle_time(String suckle_time) {
        this.suckle_time = suckle_time;
    }

    public String getPuke() {
        return puke;
    }

    public void setPuke(String puke) {
        this.puke = puke;
    }

    public String getDefecate() {
        return defecate;
    }

    public void setDefecate(String defecate) {
        this.defecate = defecate;
    }

    public String getDefecate_time() {
        return defecate_time;
    }

    public void setDefecate_time(String defecate_time) {
        this.defecate_time = defecate_time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPulse_rate() {
        return pulse_rate;
    }

    public void setPulse_rate(String pulse_rate) {
        this.pulse_rate = pulse_rate;
    }

    public String getBreathing_fn() {
        return breathing_fn;
    }

    public void setBreathing_fn(String breathing_fn) {
        this.breathing_fn = breathing_fn;
    }

    public String getJaundice_part() {
        return jaundice_part;
    }

    public void setJaundice_part(String jaundice_part) {
        this.jaundice_part = jaundice_part;
    }

    public String getComplexion() {
        return complexion;
    }

    public void setComplexion(String complexion) {
        this.complexion = complexion;
    }

    public String getComplexion_other() {
        return complexion_other;
    }

    public void setComplexion_other(String complexion_other) {
        this.complexion_other = complexion_other;
    }

    public String getFontanel_x() {
        return fontanel_x;
    }

    public void setFontanel_x(String fontanel_x) {
        this.fontanel_x = fontanel_x;
    }

    public String getFontanel_y() {
        return fontanel_y;
    }

    public void setFontanel_y(String fontanel_y) {
        this.fontanel_y = fontanel_y;
    }

    public String getFontanel_status() {
        return fontanel_status;
    }

    public void setFontanel_status(String fontanel_status) {
        this.fontanel_status = fontanel_status;
    }

    public String getFontanel_other() {
        return fontanel_other;
    }

    public void setFontanel_other(String fontanel_other) {
        this.fontanel_other = fontanel_other;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getEye_abn() {
        return eye_abn;
    }

    public void setEye_abn(String eye_abn) {
        this.eye_abn = eye_abn;
    }

    public String getEar() {
        return ear;
    }

    public void setEar(String ear) {
        this.ear = ear;
    }

    public String getEar_abn() {
        return ear_abn;
    }

    public void setEar_abn(String ear_abn) {
        this.ear_abn = ear_abn;
    }

    public String getNose() {
        return nose;
    }

    public void setNose(String nose) {
        this.nose = nose;
    }

    public String getNose_abn() {
        return nose_abn;
    }

    public void setNose_abn(String nose_abn) {
        this.nose_abn = nose_abn;
    }

    public String getNonnasality() {
        return nonnasality;
    }

    public void setNonnasality(String nonnasality) {
        this.nonnasality = nonnasality;
    }

    public String getNonnasality_abn() {
        return nonnasality_abn;
    }

    public void setNonnasality_abn(String nonnasality_abn) {
        this.nonnasality_abn = nonnasality_abn;
    }

    public String getHeart_lung() {
        return heart_lung;
    }

    public void setHeart_lung(String heart_lung) {
        this.heart_lung = heart_lung;
    }

    public String getHeart_lung_abn() {
        return heart_lung_abn;
    }

    public void setHeart_lung_abn(String heart_lung_abn) {
        this.heart_lung_abn = heart_lung_abn;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getAbdomen_abn() {
        return abdomen_abn;
    }

    public void setAbdomen_abn(String abdomen_abn) {
        this.abdomen_abn = abdomen_abn;
    }

    public String getLimbs() {
        return limbs;
    }

    public void setLimbs(String limbs) {
        this.limbs = limbs;
    }

    public String getLimbs_abn() {
        return limbs_abn;
    }

    public void setLimbs_abn(String limbs_abn) {
        this.limbs_abn = limbs_abn;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getNeck_abn() {
        return neck_abn;
    }

    public void setNeck_abn(String neck_abn) {
        this.neck_abn = neck_abn;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getSkin_abn() {
        return skin_abn;
    }

    public void setSkin_abn(String skin_abn) {
        this.skin_abn = skin_abn;
    }

    public String getAnus() {
        return anus;
    }

    public void setAnus(String anus) {
        this.anus = anus;
    }

    public String getAnus_abn() {
        return anus_abn;
    }

    public void setAnus_abn(String anus_abn) {
        this.anus_abn = anus_abn;
    }

    public String getGenitalia() {
        return genitalia;
    }

    public void setGenitalia(String genitalia) {
        this.genitalia = genitalia;
    }

    public String getGenitalia_abn() {
        return genitalia_abn;
    }

    public void setGenitalia_abn(String genitalia_abn) {
        this.genitalia_abn = genitalia_abn;
    }

    public String getBackbone() {
        return backbone;
    }

    public void setBackbone(String backbone) {
        this.backbone = backbone;
    }

    public String getBackbone_abn() {
        return backbone_abn;
    }

    public void setBackbone_abn(String backbone_abn) {
        this.backbone_abn = backbone_abn;
    }

    public String getUmbilical() {
        return umbilical;
    }

    public void setUmbilical(String umbilical) {
        this.umbilical = umbilical;
    }

    public String getUmbilical_abn() {
        return umbilical_abn;
    }

    public void setUmbilical_abn(String umbilical_abn) {
        this.umbilical_abn = umbilical_abn;
    }

    public String getTransfert() {
        return transfert;
    }

    public void setTransfert(String transfert) {
        this.transfert = transfert;
    }

    public String getTransfert_cause() {
        return transfert_cause;
    }

    public void setTransfert_cause(String transfert_cause) {
        this.transfert_cause = transfert_cause;
    }

    public String getTransfert_org_name() {
        return transfert_org_name;
    }

    public void setTransfert_org_name(String transfert_org_name) {
        this.transfert_org_name = transfert_org_name;
    }

    public String getGuidance_memo() {
        return guidance_memo;
    }

    public void setGuidance_memo(String guidance_memo) {
        this.guidance_memo = guidance_memo;
    }

    public String getGuidance_mark() {
        return guidance_mark;
    }

    public void setGuidance_mark(String guidance_mark) {
        this.guidance_mark = guidance_mark;
    }

    public String getNext_visit_date() {
        return next_visit_date;
    }

    public void setNext_visit_date(String next_visit_date) {
        this.next_visit_date = next_visit_date;
    }

    public String getNext_visit_site() {
        return next_visit_site;
    }

    public void setNext_visit_site(String next_visit_site) {
        this.next_visit_site = next_visit_site;
    }

    public String getEducation_prescribe() {
        return education_prescribe;
    }

    public void setEducation_prescribe(String education_prescribe) {
        this.education_prescribe = education_prescribe;
    }
}
