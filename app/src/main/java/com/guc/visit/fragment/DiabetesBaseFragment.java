package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.DiabetesDTO;


public class DiabetesBaseFragment extends BaseFragment {
    private TextView name;
    private TextView sex;
    private TextView birth_date;
    private TextView disease_status;
    private TextView diagnose_date;
    private TextView source_flag;
    private TextView diabetes_type;
    private TextView height;
    private TextView family_diabetes;
    private TextView blood_sugar;
    private TextView address_str;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_diabetes_base);
    }

    @Override
    protected void initData() {
        DiabetesDTO dto = (DiabetesDTO) getArguments().getSerializable("data");
        updateUI(dto);
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initWidget(View view) {
        name = (TextView) view.findViewById(R.id.name);
        sex = (TextView) view.findViewById(R.id.sex);
        birth_date = (TextView) view.findViewById(R.id.birth_date);
        disease_status = (TextView) view.findViewById(R.id.disease_status);
        diagnose_date = (TextView) view.findViewById(R.id.diagnose_date);
        source_flag = (TextView) view.findViewById(R.id.source_flag);
        diabetes_type = (TextView) view.findViewById(R.id.diabetes_type);
        height = (TextView) view.findViewById(R.id.height);
        family_diabetes = (TextView) view.findViewById(R.id.family_diabetes);
        blood_sugar = (TextView) view.findViewById(R.id.blood_sugar);
        address_str = (TextView) view.findViewById(R.id.address_str);
    }

    @Override
    public void onClick(View v) {

    }

    private void updateUI(DiabetesDTO dto) {
        name.setText(dto.getName());
        sex.setText(dto.getSex());
        birth_date.setText(getFormatDateStr(dto.getBirth_date()));
        disease_status.setText(dto.getDisease_status());
        diagnose_date.setText(getFormatDateStr(dto.getDiagnose_date()));
        source_flag.setText(dto.getSource_flag());
        diabetes_type.setText(dto.getDiabetes_type());
        height.setText(dto.getHeight());
        family_diabetes.setText(dto.getFamily_diabetes());
        blood_sugar.setText(dto.getBlood_sugar());
        address_str.setText(dto.getAddress_str());
    }

    public static DiabetesBaseFragment newInstance(DiabetesDTO data) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        DiabetesBaseFragment fragment = new DiabetesBaseFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
