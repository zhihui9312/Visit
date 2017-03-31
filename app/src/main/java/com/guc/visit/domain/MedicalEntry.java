package com.guc.visit.domain;

import com.guc.visit.base.BaseDTO;


public class MedicalEntry extends BaseDTO {
    private String ehr_id;
    private String data_type;
    private String cr_time;
    private String cr_operator;
    private String record_code;
    private String mtest_date;
    private String doctor_name;
    private String symptom;
    private String symptom_str;
    private String symptom_other;
    private String temperature;
    private String pulse_rate;
    private String breathing_fn;
    private String blood_pressure_left;
    private String diastolic_pressure_left;
    private String blood_pressure_right;
    private String diastolic_pressure_right;
    private String height;
    private String weight;
    private String waistline;
    private String old_health_status;
    private String old_lifeself;
    private String old_cognizefun;
    private String old_cognizefun_grade;
    private String old_sensibility;
    private String old_blahs_grade;
    private String physical_exercise_fre;
    private String physical_exercise_minute;
    private String physical_exercise_year;
    private String exercise_way;
    private String diet_habit_abn;
    private String diet_habit;
    private String smoke_situation;
    private String smoke_qty;
    private String begin_smoke_age;
    private String stop_smoke_age;
    private String drink_frequency;
    private String drink_measure_day;
    private String if_stopdrink_flage;
    private String stop_drink_age;
    private String begin_drink_age;
    private String in_one_year_intoxication;
    private String drink_type_abn;
    private String drink_type;
    private String job_mark;
    private String job_occupation;
    private String job_year;
    private String dust;
    private String dust_protect;
    private String dust_abn;
    private String ray;
    private String ray_protect;
    private String ray_abn;

    private String pest;
    private String pest_protect;
    private String pest_abn;

    private String chemicla;
    private String chemicla_protect;
    private String chemicla_abn;

    private String toxicant_other;
    private String toxicant_other_protect;
    private String toxicant_other_abn;
    private String db_id;

    public String getDb_id() {
        return db_id;
    }

    public void setDb_id(String db_id) {
        this.db_id = db_id;
    }

    public String getEhr_id() {
        return ehr_id;
    }

    public void setEhr_id(String ehr_id) {
        this.ehr_id = ehr_id;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
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

    public String getRecord_code() {
        return record_code;
    }

    public void setRecord_code(String record_code) {
        this.record_code = record_code;
    }

    public String getMtest_date() {
        return mtest_date;
    }

    public void setMtest_date(String mtest_date) {
        this.mtest_date = mtest_date;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getSymptom_str() {
        return symptom_str;
    }

    public void setSymptom_str(String symptom_str) {
        this.symptom_str = symptom_str;
    }

    public String getSymptom_other() {
        return symptom_other;
    }

    public void setSymptom_other(String symptom_other) {
        this.symptom_other = symptom_other;
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

    public String getBlood_pressure_left() {
        return blood_pressure_left;
    }

    public void setBlood_pressure_left(String blood_pressure_left) {
        this.blood_pressure_left = blood_pressure_left;
    }

    public String getDiastolic_pressure_left() {
        return diastolic_pressure_left;
    }

    public void setDiastolic_pressure_left(String diastolic_pressure_left) {
        this.diastolic_pressure_left = diastolic_pressure_left;
    }

    public String getBlood_pressure_right() {
        return blood_pressure_right;
    }

    public void setBlood_pressure_right(String blood_pressure_right) {
        this.blood_pressure_right = blood_pressure_right;
    }

    public String getDiastolic_pressure_right() {
        return diastolic_pressure_right;
    }

    public void setDiastolic_pressure_right(String diastolic_pressure_right) {
        this.diastolic_pressure_right = diastolic_pressure_right;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWaistline() {
        return waistline;
    }

    public void setWaistline(String waistline) {
        this.waistline = waistline;
    }

    public String getOld_health_status() {
        return old_health_status;
    }

    public void setOld_health_status(String old_health_status) {
        this.old_health_status = old_health_status;
    }

    public String getOld_lifeself() {
        return old_lifeself;
    }

    public void setOld_lifeself(String old_lifeself) {
        this.old_lifeself = old_lifeself;
    }

    public String getOld_cognizefun() {
        return old_cognizefun;
    }

    public void setOld_cognizefun(String old_cognizefun) {
        this.old_cognizefun = old_cognizefun;
    }

    public String getOld_cognizefun_grade() {
        return old_cognizefun_grade;
    }

    public void setOld_cognizefun_grade(String old_cognizefun_grade) {
        this.old_cognizefun_grade = old_cognizefun_grade;
    }

    public String getOld_sensibility() {
        return old_sensibility;
    }

    public void setOld_sensibility(String old_sensibility) {
        this.old_sensibility = old_sensibility;
    }

    public String getOld_blahs_grade() {
        return old_blahs_grade;
    }

    public void setOld_blahs_grade(String old_blahs_grade) {
        this.old_blahs_grade = old_blahs_grade;
    }

    public String getPhysical_exercise_fre() {
        return physical_exercise_fre;
    }

    public void setPhysical_exercise_fre(String physical_exercise_fre) {
        this.physical_exercise_fre = physical_exercise_fre;
    }

    public String getPhysical_exercise_minute() {
        return physical_exercise_minute;
    }

    public void setPhysical_exercise_minute(String physical_exercise_minute) {
        this.physical_exercise_minute = physical_exercise_minute;
    }

    public String getPhysical_exercise_year() {
        return physical_exercise_year;
    }

    public void setPhysical_exercise_year(String physical_exercise_year) {
        this.physical_exercise_year = physical_exercise_year;
    }

    public String getExercise_way() {
        return exercise_way;
    }

    public void setExercise_way(String exercise_way) {
        this.exercise_way = exercise_way;
    }

    public String getDiet_habit_abn() {
        return diet_habit_abn;
    }

    public void setDiet_habit_abn(String diet_habit_abn) {
        this.diet_habit_abn = diet_habit_abn;
    }

    public String getDiet_habit() {
        return diet_habit;
    }

    public void setDiet_habit(String diet_habit) {
        this.diet_habit = diet_habit;
    }

    public String getSmoke_situation() {
        return smoke_situation;
    }

    public void setSmoke_situation(String smoke_situation) {
        this.smoke_situation = smoke_situation;
    }

    public String getSmoke_qty() {
        return smoke_qty;
    }

    public void setSmoke_qty(String smoke_qty) {
        this.smoke_qty = smoke_qty;
    }

    public String getBegin_smoke_age() {
        return begin_smoke_age;
    }

    public void setBegin_smoke_age(String begin_smoke_age) {
        this.begin_smoke_age = begin_smoke_age;
    }

    public String getStop_smoke_age() {
        return stop_smoke_age;
    }

    public void setStop_smoke_age(String stop_smoke_age) {
        this.stop_smoke_age = stop_smoke_age;
    }

    public String getDrink_frequency() {
        return drink_frequency;
    }

    public void setDrink_frequency(String drink_frequency) {
        this.drink_frequency = drink_frequency;
    }

    public String getDrink_measure_day() {
        return drink_measure_day;
    }

    public void setDrink_measure_day(String drink_measure_day) {
        this.drink_measure_day = drink_measure_day;
    }

    public String getIf_stopdrink_flage() {
        return if_stopdrink_flage;
    }

    public void setIf_stopdrink_flage(String if_stopdrink_flage) {
        this.if_stopdrink_flage = if_stopdrink_flage;
    }

    public String getStop_drink_age() {
        return stop_drink_age;
    }

    public void setStop_drink_age(String stop_drink_age) {
        this.stop_drink_age = stop_drink_age;
    }

    public String getBegin_drink_age() {
        return begin_drink_age;
    }

    public void setBegin_drink_age(String begin_drink_age) {
        this.begin_drink_age = begin_drink_age;
    }

    public String getIn_one_year_intoxication() {
        return in_one_year_intoxication;
    }

    public void setIn_one_year_intoxication(String in_one_year_intoxication) {
        this.in_one_year_intoxication = in_one_year_intoxication;
    }

    public String getDrink_type_abn() {
        return drink_type_abn;
    }

    public void setDrink_type_abn(String drink_type_abn) {
        this.drink_type_abn = drink_type_abn;
    }

    public String getDrink_type() {
        return drink_type;
    }

    public void setDrink_type(String drink_type) {
        this.drink_type = drink_type;
    }

    public String getJob_mark() {
        return job_mark;
    }

    public void setJob_mark(String job_mark) {
        this.job_mark = job_mark;
    }

    public String getJob_occupation() {
        return job_occupation;
    }

    public void setJob_occupation(String job_occupation) {
        this.job_occupation = job_occupation;
    }

    public String getJob_year() {
        return job_year;
    }

    public void setJob_year(String job_year) {
        this.job_year = job_year;
    }

    public String getDust() {
        return dust;
    }

    public void setDust(String dust) {
        this.dust = dust;
    }

    public String getDust_protect() {
        return dust_protect;
    }

    public void setDust_protect(String dust_protect) {
        this.dust_protect = dust_protect;
    }

    public String getDust_abn() {
        return dust_abn;
    }

    public void setDust_abn(String dust_abn) {
        this.dust_abn = dust_abn;
    }

    public String getRay() {
        return ray;
    }

    public void setRay(String ray) {
        this.ray = ray;
    }

    public String getRay_protect() {
        return ray_protect;
    }

    public void setRay_protect(String ray_protect) {
        this.ray_protect = ray_protect;
    }

    public String getRay_abn() {
        return ray_abn;
    }

    public void setRay_abn(String ray_abn) {
        this.ray_abn = ray_abn;
    }

    public String getPest() {
        return pest;
    }

    public void setPest(String pest) {
        this.pest = pest;
    }

    public String getPest_protect() {
        return pest_protect;
    }

    public void setPest_protect(String pest_protect) {
        this.pest_protect = pest_protect;
    }

    public String getPest_abn() {
        return pest_abn;
    }

    public void setPest_abn(String pest_abn) {
        this.pest_abn = pest_abn;
    }

    public String getChemicla() {
        return chemicla;
    }

    public void setChemicla(String chemicla) {
        this.chemicla = chemicla;
    }

    public String getChemicla_protect() {
        return chemicla_protect;
    }

    public void setChemicla_protect(String chemicla_protect) {
        this.chemicla_protect = chemicla_protect;
    }

    public String getChemicla_abn() {
        return chemicla_abn;
    }

    public void setChemicla_abn(String chemicla_abn) {
        this.chemicla_abn = chemicla_abn;
    }

    public String getToxicant_other() {
        return toxicant_other;
    }

    public void setToxicant_other(String toxicant_other) {
        this.toxicant_other = toxicant_other;
    }

    public String getToxicant_other_protect() {
        return toxicant_other_protect;
    }

    public void setToxicant_other_protect(String toxicant_other_protect) {
        this.toxicant_other_protect = toxicant_other_protect;
    }

    public String getToxicant_other_abn() {
        return toxicant_other_abn;
    }

    public void setToxicant_other_abn(String toxicant_other_abn) {
        this.toxicant_other_abn = toxicant_other_abn;
    }
}
