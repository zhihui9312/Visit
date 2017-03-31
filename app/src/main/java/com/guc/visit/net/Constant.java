package com.guc.visit.net;

import android.support.v7.widget.AppCompatEditText;

public class Constant {
    public static final String PROTOCOL = "http://";
    public static final String HOSTNAME = "wap.guc258.com";
    // public static final String HOSTNAME = "192.168.0.109";
    public static final String PORT = ":8088";
    public static final String URL_ROOT = PROTOCOL + HOSTNAME + PORT;
    public static final String APP_ID = "03";
    public static final String DBID = "/publicservice/publicservice/getDBID/" + APP_ID + "/";
    public static final String CHECKLIMIT = "/publicservice/publicservice/checkLimit/" + APP_ID + "/";
    public static final String DOCTORINFO = "/restful/publichealth/Service/getDoctorInfo";
    public static final String ORGANIZATION = "/publicservice/publicservice/getOrganization/" + APP_ID;
    public static final String REGISTER = "/restful/doctor/Service/Register/";
    public static final String LOGINLOG = "/publicservice/publicservice/saveLog/" + APP_ID + "/";
    public static final String ARCHIVESQUERY = "/restful/publichealth/Service/getPersonBaseInfo/";
    public static final String HYPERTENSION = "/restful/publichealth/Service/getHypertensionInfo/";
    public static final String ADDHYPERTENSION = "/restful/publichealth/Service/UploadHypertensionVisit";
    public static final String HYPERTENSIONLAST = "/restful/publichealth/Service/getLastHypertensionRecord/";
    public static final String HYPERTENSIONHISTORY = "/restful/publichealth/Service/getHypertensionRecord/";
    public static final String DIABEQUERY = "/restful/publichealth/Service/getDiabetesInfo/";
    public static final String DIABETESLAST = "/restful/publichealth/Service/getLastDiabetesRecord/";
    public static final String DIABETESADD = "/restful/publichealth/Service/UploadDiabetesVisit";
    public static final String DIABETESRECORD = "/restful/publichealth/Service/getDiabetesRecord/";
    public static final String MENTALBASEINFO = "/restful/publichealth/Service/getMentalInfo/";
    public static final String MENTALADD = "/restful/publichealth/Service/UploadMentalVisit";
    public static final String LAST_MENTAL_RECORD = "/restful/publichealth/Service/getLastMentalRecord/";
    public static final String MENTALINFO = "/restful/publichealth/Service/getMentalRecord/";
    public static final String PREGNANTQUERY = "/restful/publichealth/Service/getMaternalInfo/";
    public static final String ADDMAUNAL = "/restful/publichealth/Service/UploadMaternalRegister";
    public static final String MATERNALBEFOREONE = "/restful/publichealth/Service/UploadMaternalBeforeOne/";
    public static final String MATERNALBEFOREFIVE = "/restful/publichealth/Service/UploadMaternalBeforeFive";
    public static final String MATERNALAFTER = "/restful/publichealth/Service/UploadMaternalAfter";
    public static final String MATERNALAFTER42 = "/restful/publichealth/Service/UploadMaternalAfter42Day";
    public static final String CHILDINFO = "/restful/publichealth/Service/getNewBronInfo/";
    public static final String CHILDNEWBRON = "/restful/publichealth/Service/UploadNewBron";
    public static final String CHILDONEYEAR = "/restful/publichealth/Service/UploadNewBronOneYear";
    public static final String CHILDONETWOYEAR = "/restful/publichealth/Service/UploadNewBronOneTwoYear";
    public static final String CHILDTHREEYEAR = "/restful/publichealth/Service/UploadNewBronThreeYear";
    public static final String WORKLOADSTATISTICS = "/restful/publichealth/Service/getWorkmbbz/";

    public static final String GETPREGNANTONERECORD = "/restful/publichealth/Service/getMaternalBeforeOneRecord/";
    public static final String GETPREGNANTFIVE = "/restful/publichealth/Service/getMaternalBeforeFiveRecord/";
    // 儿童-新生儿家庭访视
    public static final String GETCHILDNEWBRON = "/restful/publichealth/Service/getNewBronRecord/";

    public static final String GETCHILDONEYEAR = "/restful/publichealth/Service/getNewBronOneYearRecord/";
    public static final String GETCHILDONETWO = "/restful/publichealth/Service/getNewBronOneTwoYearRecord/";
    public static final String GETCHILDTHREE = "/restful/publichealth/Service/getNewBronThreeYearRecord/";

    public static final  String ARCHIVESADD= "/Restful/publichealth/Service/hrPersonBaseRegister";

    public static final String ARCHIVESORGANIZATION="/Restful/publichealth/Service/getVillageList/";

    public static final String GETVERSION="/publicservice/publicservice/getVersion/"+APP_ID+"";

    public static final String  GETAPPURL="/publicservice/publicservice/getAppURL/"+APP_ID+"/";
    public static final String ADDMEDICALENTRY="/Restful/publichealth/Service/UploadMsPhysicalExamination";


}