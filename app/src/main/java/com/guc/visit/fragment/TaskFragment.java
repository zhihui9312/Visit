package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;


public class TaskFragment extends BaseFragment {
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_task);
    }

    @Override
    protected void initData() {
        controlBar(R.string.task_remind, R.string.back, true, false);

    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
    }

    @Override
    protected void initWidget(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            default:
                break;
        }
    }

    public static TaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
