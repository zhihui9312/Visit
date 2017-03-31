package com.guc.visit.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guc.visit.base.BaseDTO;

public  class  QueryResult<T> extends BaseDTO {
    private T baseInfo;
    private JSONArray jsonArray;

    public T getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(T baseInfo) {
        this.baseInfo = baseInfo;
    }

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }
}
