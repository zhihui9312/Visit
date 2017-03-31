package com.guc.visit.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.guc.visit.listener.PopBackStack;
import com.guc.visit.listener.Replace;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener, PopBackStack, Replace {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initWidget();
        setListeners();
    }

    protected abstract void setListeners();

    protected abstract void initWidget();

    @Override
    public void onClick(View v) {

    }
}
