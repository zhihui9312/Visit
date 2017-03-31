package com.guc.visit.listener;


import android.support.v4.app.Fragment;

public interface Replace {
    void replace(String tag, Fragment fragment, boolean isaddToBackStack);
    void replace(Fragment fragment, boolean isaddToBackStack);
}
