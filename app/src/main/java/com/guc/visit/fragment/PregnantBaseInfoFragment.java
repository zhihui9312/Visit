package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.PregnantBaseDTO;

/**
 * 孕妇随访基本信息
 */
public class PregnantBaseInfoFragment extends BaseFragment {
    private TextView name;
    private TextView sex;
    private TextView menses_over_date;
    private TextView is_high_risk;
    private TextView high_score;
    private TextView create_week_days;
    private TextView create_week;
    private TextView blood_rh;
    private TextView birth_date;
    private TextView address_str;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_pregnant_base);
    }

    @Override
    protected void initData() {
        PregnantBaseDTO dto = (PregnantBaseDTO) getArguments().getSerializable("data");
        updateUI(dto);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initWidget(View view) {
        name = (TextView) view.findViewById(R.id.name);
        sex = (TextView) view.findViewById(R.id.sex);
        menses_over_date = (TextView) view.findViewById(R.id.menses_over_date);
        is_high_risk = (TextView) view.findViewById(R.id.is_high_risk);
        high_score = (TextView) view.findViewById(R.id.high_score);
        create_week_days = (TextView) view.findViewById(R.id.create_week_days);
        create_week = (TextView) view.findViewById(R.id.create_week);
        blood_rh = (TextView) view.findViewById(R.id.blood_rh);
        birth_date = (TextView) view.findViewById(R.id.birth_date);
        address_str = (TextView) view.findViewById(R.id.address_str);
    }

    @Override
    public void onClick(View v) {

    }

    private void updateUI(PregnantBaseDTO dto) {
        name.setText(dto.getName());
        sex.setText(dto.getSex());
        menses_over_date.setText(dto.getMenses_over_date().split(" ")[0]);
        is_high_risk.setText(dto.getIs_high_risk());
        high_score.setText(dto.getHigh_score());
        create_week_days.setText(dto.getCreate_week_days());
        create_week.setText(dto.getCreate_week());
        blood_rh.setText(dto.getBlood_rh());
        birth_date.setText(dto.getBirth_date().split(" ")[0]);
        address_str.setText(dto.getAddress_str());
    }

    public static PregnantBaseInfoFragment newInstance(PregnantBaseDTO dto) {
        Bundle args = new Bundle();
        args.putSerializable("data", dto);
        PregnantBaseInfoFragment fragment = new PregnantBaseInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
