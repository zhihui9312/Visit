package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.HypertensionBaseDTO;

public class HypertensionBaseInfoFragment extends BaseFragment {
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_birth_date;
    private TextView tv_disease_status;
    private TextView tv_diagnose_date;
    private TextView tv_source_flag;
    private TextView tv_height;
    private TextView tv_weight;
    private TextView tv_systolic_pressure;
    private TextView tv_diastolic_pressure;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_hypertension_base);
    }

    @Override
    protected void initData() {
        HypertensionBaseDTO dto = (HypertensionBaseDTO) getArguments().getSerializable("data");
        if (dto != null) {
            tv_name.setText(dto.getName());
            tv_sex.setText(dto.getSex());
            tv_birth_date.setText(getFormatDateStr(dto.getBirth_date()));
            tv_disease_status.setText(dto.getDisease_status());
            tv_diagnose_date.setText(getFormatDateStr(dto.getDiagnose_date()));
            tv_source_flag.setText(dto.getSource_flag());
            tv_height.setText(dto.getHeight());
            tv_weight.setText(dto.getWeight());
            tv_systolic_pressure.setText(dto.getSystolic_pressure());
            tv_diastolic_pressure.setText(dto.getDiastolic_pressure());
        }
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initWidget(View view) {
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_sex = (TextView) view.findViewById(R.id.tv_sex);
        tv_birth_date = (TextView) view.findViewById(R.id.tv_birth_date);
        tv_disease_status = (TextView) view.findViewById(R.id.tv_disease_status);
        tv_diagnose_date = (TextView) view.findViewById(R.id.tv_diagnose_date);
        tv_source_flag = (TextView) view.findViewById(R.id.tv_source_flag);
        tv_height = (TextView) view.findViewById(R.id.tv_height);
        tv_weight = (TextView) view.findViewById(R.id.tv_weight);
        tv_systolic_pressure = (TextView) view.findViewById(R.id.tv_systolic_pressure);
        tv_diastolic_pressure = (TextView) view.findViewById(R.id.tv_diastolic_pressure);
    }

    @Override
    public void onClick(View v) {

    }

    public static HypertensionBaseInfoFragment newInstance(HypertensionBaseDTO dto) {
        Bundle args = new Bundle();
        args.putSerializable("data", dto);
        HypertensionBaseInfoFragment fragment = new HypertensionBaseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}