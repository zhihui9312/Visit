package com.guc.visit.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ViewUtils {
    public static void setAllViewEnable(View view) {

        List<View> views = getAllChildViews(view);
        for (View v : views) {
            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }
    }


    public static List<View> getAllChildViews(View view) {

        List<View> allchildren = new ArrayList<>();

        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                allchildren.add(viewchild);
                allchildren.addAll(getAllChildViews(viewchild));
            }
        }
        return allchildren;
    }

}
