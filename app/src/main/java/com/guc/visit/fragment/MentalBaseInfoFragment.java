package com.guc.visit.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.MentalBaseDTO;

public class MentalBaseInfoFragment extends BaseFragment {
    private TextView name;
    private TextView sex;
    private TextView guardian_name;
    private TextView guardian_telephone;
    private TextView village_info;
    private TextView spirit_state;
    private TextView diagnose_name;
    private TextView diagnose_hospital;
    private TextView diagnose_date;
    private TextView affray;
    private TextView cause_trouble;
    private TextView disaster;
    private TextView autolesion;
    private TextView attempted_suicide;
    private TextView close_lock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_mental_base_info);
    }

    @Override
    protected void initData() {
        MentalBaseDTO dto=(MentalBaseDTO)getArguments().getSerializable("data");
        name.setText(dto.getName());
        sex.setText(dto.getSex());
        guardian_name.setText(dto.getGuardian_name());
        guardian_telephone.setText(dto.getGuardian_telephone());
        village_info.setText(dto.getVillage_info());
        spirit_state.setText(dto.getSpirit_state());
        diagnose_name.setText(dto.getDiagnose_name());
        diagnose_hospital.setText(dto.getDiagnose_hospital());
        diagnose_date.setText(dto.getDiagnose_date());
        affray.setText(dto.getAffray());
        cause_trouble.setText(dto.getCause_trouble());
        disaster.setText(dto.getDisaster());
        autolesion.setText(dto.getAutolesion());
        attempted_suicide.setText(dto.getAttempted_suicide());
        close_lock.setText(dto.getClose_lock());
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initWidget(View view) {
        name = (TextView) view.findViewById(R.id.name);
        sex = (TextView) view.findViewById(R.id.sex);
        guardian_name = (TextView) view.findViewById(R.id.guardian_name);
        guardian_telephone = (TextView) view.findViewById(R.id.guardian_telephone);
        village_info = (TextView) view.findViewById(R.id.village_info);
        spirit_state = (TextView) view.findViewById(R.id.spirit_state);
        diagnose_name = (TextView) view.findViewById(R.id.diagnose_name);
        diagnose_hospital = (TextView) view.findViewById(R.id.diagnose_hospital);
        diagnose_date = (TextView) view.findViewById(R.id.diagnose_date);
        affray = (TextView) view.findViewById(R.id.affray);
        cause_trouble = (TextView) view.findViewById(R.id.cause_trouble);
        disaster = (TextView) view.findViewById(R.id.disaster);
        autolesion = (TextView) view.findViewById(R.id.autolesion);
        attempted_suicide = (TextView) view.findViewById(R.id.attempted_suicide);
        close_lock = (TextView) view.findViewById(R.id.close_lock);
    }

    @Override
    public void onClick(View v) {

    }

    public static MentalBaseInfoFragment newInstance(MentalBaseDTO dto) {

        Bundle args = new Bundle();
        args.putSerializable("data", dto);
        MentalBaseInfoFragment fragment = new MentalBaseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
