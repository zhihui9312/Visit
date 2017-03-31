package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.ChildBaseInfo;


public class ChildBaseInfoFragment extends BaseFragment {
    private EditText name;
    private EditText sex;
    private EditText birth_date;
    private EditText birth_week;
    private EditText birth_week_days;
    private EditText bodylong;
    private EditText born_weight;
    private EditText father_name;
    private EditText father_telephone;
    private EditText mother_name;
    private EditText mother_telephone;
    private EditText address_str;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_child_baseinfo);
    }

    @Override
    protected void initData() {
        ChildBaseInfo childBaseInfo = (ChildBaseInfo) getArguments().getSerializable("childBaseInfo");
        updateUI(childBaseInfo);
    }

    private void updateUI(ChildBaseInfo childBaseInfo) {
        name.setText(childBaseInfo.getName());
        sex.setText(childBaseInfo.getSex());
        birth_date.setText(childBaseInfo.getBirth_date().split("T")[0]);
        birth_week.setText(childBaseInfo.getBirth_week());
        birth_week_days.setText(childBaseInfo.getBirth_week_days());
        bodylong.setText(childBaseInfo.getBodylong());
        born_weight.setText(childBaseInfo.getBorn_weight());
        father_name.setText(childBaseInfo.getFather_name());
        father_telephone.setText(childBaseInfo.getFather_telephone());
        mother_name.setText(childBaseInfo.getMother_name());
        mother_telephone.setText(childBaseInfo.getMother_telephone());
        address_str.setText(childBaseInfo.getAddress_str());
    }


    @Override
    protected void setListeners() {

    }


    @Override
    protected void initWidget(View view) {
        name = (EditText) view.findViewById(R.id.name);
        sex = (EditText) view.findViewById(R.id.sex);
        birth_date = (EditText) view.findViewById(R.id.birth_date);
        birth_week = (EditText) view.findViewById(R.id.birth_week);
        birth_week_days = (EditText) view.findViewById(R.id.birth_week_days);
        bodylong = (EditText) view.findViewById(R.id.bodylong);
        born_weight = (EditText) view.findViewById(R.id.born_weight);
        father_name = (EditText) view.findViewById(R.id.father_name);
        father_telephone = (EditText) view.findViewById(R.id.father_telephone);
        mother_name = (EditText) view.findViewById(R.id.mother_name);
        mother_telephone = (EditText) view.findViewById(R.id.mother_telephone);
        address_str = (EditText) view.findViewById(R.id.address_str);
    }

    @Override
    public void onClick(View v) {

    }

    public static ChildBaseInfoFragment newInstance(ChildBaseInfo childBaseInfo) {
        Bundle args = new Bundle();
        args.putSerializable("childBaseInfo", childBaseInfo);
        ChildBaseInfoFragment fragment = new ChildBaseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
