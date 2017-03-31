package com.guc.visit.controller;


import com.guc.visit.mode.DefaultHXSDKModel;
import com.guc.visit.mode.GucSDKModel;

public class DefaultHelper extends GucSDKHelper {

    @Override
    protected GucSDKModel createModel() {
        return new DefaultHXSDKModel(appContext);
    }
}
