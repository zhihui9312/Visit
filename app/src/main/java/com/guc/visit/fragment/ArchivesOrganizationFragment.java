package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;


public class ArchivesOrganizationFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_archives_organization);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initWidget(View view) {

    }

    @Override
    public void onClick(View v) {

    }

    public static ArchivesOrganizationFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ArchivesOrganizationFragment fragment = new ArchivesOrganizationFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
