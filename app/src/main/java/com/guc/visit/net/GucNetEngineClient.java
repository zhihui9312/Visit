package com.guc.visit.net;

import android.app.ProgressDialog;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.guc.visit.R;
import com.guc.visit.application.GucApplication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class GucNetEngineClient {
    public static String DBID = "";
    public static String ORG_CODE = "";

    public static Request<String> checkLimit(String account, Listener<String> listener, ErrorListener errorListener) {
        String url = Constant.CHECKLIMIT + account;
        return get(url, null, listener, errorListener);
    }

    public static Request<String> getDbid(String account, Listener<String> listener, ErrorListener errorListener) {
        String url = Constant.DBID + account;
        return get(url, null, listener, errorListener);
    }

    public static Request<String> getDoctorInfo(String account, String password, Listener<String> listener, ErrorListener errorListener) {
        String url = Constant.DOCTORINFO;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dblist_id", DBID);
        jsonObject.put("phoneNumber", account);
        jsonObject.put("PWD", password);
        return post(url, jsonObject.toJSONString(), listener, errorListener);
    }

    public static Request<String> getOrganization(Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.ORGANIZATION;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> register(String organizationId, String phoneNumber, String password, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.REGISTER + organizationId + "/07/" + phoneNumber + "/" + password;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> loginLog(Listener<String> listener, ErrorListener errorListener) {
        String url = Constant.LOGINLOG + GucApplication.getInstance().getUserName();
        return get(url, null, listener, errorListener);
    }

    public static Request<String> getArchivesQuery(int searchType, String content, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = Constant.ARCHIVESQUERY + DBID + "/" + searchType + "/" + content + "/" + ORG_CODE;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getHypertension(int searchType, String content, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = Constant.HYPERTENSION + DBID + "/" + searchType + "/" + content + "/" + ORG_CODE;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> addHypertension(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.ADDHYPERTENSION;
        return post(url, json, listener, errorListener, materialDialog);
    }

    public static Request<String> getLastHypertension(String ehr_id, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.HYPERTENSIONLAST + DBID + "/" + ehr_id;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getHistoryHypertension(String record_code, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.HYPERTENSIONHISTORY + DBID + "/" + record_code;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getDiabetes(int searchType, String content, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = Constant.DIABEQUERY + DBID + "/" + searchType + "/" + content + "/" + ORG_CODE;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getDiabetesLast(String ehrId, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.DIABETESLAST + DBID + "/" + ehrId;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getDiabetesRecord(String recordCode, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.DIABETESRECORD + DBID + "/" + recordCode;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> addDiabetes(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.DIABETESADD;
        return post(url, json, listener, errorListener, materialDialog);
    }

    public static Request<String> getMentalBase(int searchType, String content, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = Constant.MENTALBASEINFO + DBID + "/" + searchType + "/" + content + "/" + ORG_CODE;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> addMental(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.MENTALADD;
        return post(url, json, listener, errorListener, materialDialog);
    }

    public static Request<String> getLastMental(String ehr_id, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.LAST_MENTAL_RECORD + DBID + "/" + ehr_id;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getMental(String recordCode, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.MENTALINFO + DBID + "/" + recordCode;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 添加第一次随访
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addMaternalBeforeOne(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.MATERNALBEFOREONE;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 获取第一次随访
     *
     * @param record_code
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getPregnantOne(String record_code, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.GETPREGNANTONERECORD + DBID + "/" + record_code;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 添加第2-5次随访
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addMaternalBeforeFive(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.MATERNALBEFOREFIVE;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 获取第次随访
     *
     * @param record_code
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getPregnancyFive(String record_code, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.GETPREGNANTFIVE + DBID + "/" + record_code;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 产后
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */

    public static Request<String> addMaternalAfter(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.MATERNALAFTER;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 产后42
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addMaternalAfter42(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.MATERNALAFTER42;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 添加新生儿
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addChildNew(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.CHILDNEWBRON;
        return post(url, json, listener, errorListener, materialDialog);
    }


    /**
     * 一年内
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addChildOneYear(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.CHILDONEYEAR;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 1~2年内
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addChildTwoYear(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.CHILDONETWOYEAR;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 3年
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addChildThreeYear(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.CHILDTHREEYEAR;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 获取新生儿信息
     *
     * @param record_code
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getChildNew(String record_code, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.GETCHILDNEWBRON + DBID + "/" + record_code;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 获取一年以内
     *
     * @param record_code
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getChildOneYear(String record_code, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.GETCHILDONEYEAR + DBID + "/" + record_code;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 1-2年内
     *
     * @param record_code
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getChildOneTwo(String record_code, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.GETCHILDONETWO + DBID + "/" + record_code;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 3-6年内
     *
     * @param record_code
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getChildThree(String record_code, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.GETCHILDTHREE + DBID + "/" + record_code;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getPregnantInfo(int searchType, String content, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = Constant.PREGNANTQUERY + DBID + "/" + searchType + "/" + content + "/" + ORG_CODE;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 添加手册
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> addMaunal(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.ADDMAUNAL;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 获取儿童信息
     *
     * @param searchType
     * @param content
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getChildInfo(int searchType, String content, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        try {
            content = URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = Constant.CHILDINFO + DBID + "/" + searchType + "/" + content + "/" + ORG_CODE;
        return get(url, null, listener, errorListener, materialDialog);
    }

    /**
     * 添加档案
     *
     * @param json
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> archivesAdd(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.ARCHIVESADD;
        return post(url, json, listener, errorListener, materialDialog);
    }

    /**
     * 获取添加档案机构
     *
     * @param listener
     * @param errorListener
     * @return
     */
    public static Request<String> getArchivesOrganization(Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.ARCHIVESORGANIZATION + DBID + "/" + ORG_CODE;
        return get(url, null, listener, errorListener, materialDialog);
    }

    public static Request<String> getVersion(Listener<String> listener, ErrorListener errorListener) {
        String url = Constant.GETVERSION;
        return get(url, null, listener, errorListener);
    }

    public static Request<String> getAppUrl(String version, Listener<String> listener, ErrorListener errorListener) {
        String url = Constant.GETAPPURL + version;
        return get(url, null, listener, errorListener);
    }

    public static Request<String> getWorkloadStatistics(String beginDate, String endDate, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.WORKLOADSTATISTICS + DBID + "/" + ORG_CODE + "/" + GucApplication.doctorCode + "/" + beginDate + "/" + endDate;
        return get(url, null, listener, errorListener, materialDialog);
    }


    public static Request<String> addMedicalEntry(String json, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        String url = Constant.ADDMEDICALENTRY;
        return post(url, json, listener, errorListener, materialDialog);
    }


    public static Request<String> get(String url, String requestBody, Listener<String> listener, ErrorListener errorListener) {
        if (errorListener == null) {
            errorListener = new DefaultErrorListener();
        }
        StringRequest request = new StringRequest(Method.GET, Constant.URL_ROOT + url, requestBody, listener, errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return GucApplication.mRequestQueue.add(request);
    }

    public static Request<String> get(String url, String requestBody, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        if (errorListener == null) {
            errorListener = new DefaultErrorListener();
        }
        StringRequest request = new StringRequest(Method.GET, Constant.URL_ROOT + url, requestBody, listener, errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setProgressDialog(materialDialog);
        return GucApplication.mRequestQueue.add(request);
    }

    public static Request<String> post(String url, String requestBody, Listener<String> listener, ErrorListener errorListener) {
        StringRequest request = new StringRequest(Method.POST, Constant.URL_ROOT + url, requestBody, listener, errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return GucApplication.mRequestQueue.add(request);
    }

    public static Request<String> post(String url, String requestBody, Listener<String> listener, ErrorListener errorListener, MaterialDialog materialDialog) {
        if (errorListener == null) {
            errorListener = new DefaultErrorListener();
        }
        StringRequest request = new StringRequest(Method.POST, Constant.URL_ROOT + url, requestBody, listener, errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(60 * 1000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setProgressDialog(materialDialog);
        return GucApplication.mRequestQueue.add(request);
    }
}
