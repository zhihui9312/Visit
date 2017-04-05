package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.ArchivesQueryInDTO;


public class ArchivesDetailFragment extends BaseFragment {
    private TextView tv_name;
    private TextView tv_census_register;
    private TextView tv_ehr_id;
    private TextView tv_crid_code;
    private TextView tv_work_unit;
    private TextView tv_address;
    private TextView tv_telephone;
    private TextView tv_cr_org_code;
    private LinearLayout ll_hypertension;
    private LinearLayout ll_diabetes;
    private LinearLayout ll_mental_illness;
    private LinearLayout ll_maternal;
    private LinearLayout ll_children;
    private int searchType;
    private String content;
    private TextView tvMedicalEntry;
    private String ehr_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_archives_detail);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        ArchivesQueryInDTO inDTO = (ArchivesQueryInDTO) bundle.getSerializable("data");
        searchType = bundle.getInt("searchType");
        content = bundle.getString("content");
        if(inDTO!=null){
            tv_name.setText(inDTO.getName());
            tv_census_register.setText(inDTO.getCensus_register());
            tv_ehr_id.setText(inDTO.getEhr_id());
            tv_crid_code.setText(inDTO.getCrid_code());
            tv_work_unit.setText(inDTO.getWork_unit());
            tv_address.setText(inDTO.getAddress_str());
            tv_telephone.setText(inDTO.getTelephone());
            tv_cr_org_code.setText(inDTO.getCr_org_code());
            ehr_id=inDTO.getEhr_id();
            setType(inDTO);
            controlBar(inDTO.getName(), R.string.back, true, false);
        }
    }

    private void setType(ArchivesQueryInDTO inDTO) {
        int disableResId = R.drawable.selector_button_disable;
        if ("1".equals(inDTO.getHypertension())) {
            ll_hypertension.setOnClickListener(this);
        } else {
            ll_hypertension.setBackgroundResource(disableResId);
        }
        if ("1".equals(inDTO.getDiabetes())) {

            ll_diabetes.setOnClickListener(this);
        } else {
            ll_diabetes.setBackgroundResource(disableResId);
        }
        if ("1".equals(inDTO.getMental_illness())) {

            ll_mental_illness.setOnClickListener(this);
        } else {

            ll_mental_illness.setBackgroundResource(disableResId);
        }
        if ("1".equals(inDTO.getMaternal())) {

            ll_maternal.setOnClickListener(this);
        } else {

            ll_maternal.setBackgroundResource(disableResId);
        }
        if ("1".equals(inDTO.getChildren())) {
            ll_children.setOnClickListener(this);
        } else {

            ll_children.setBackgroundResource(disableResId);
        }
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        tvMedicalEntry.setOnClickListener(this);
    }


    @Override
    protected void initWidget(View view) {
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_census_register = (TextView) view.findViewById(R.id.tv_census_register);
        tv_ehr_id = (TextView) view.findViewById(R.id.tv_ehr_id);
        tv_crid_code = (TextView) view.findViewById(R.id.tv_crid_code);
        tv_work_unit = (TextView) view.findViewById(R.id.tv_work_unit);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_telephone = (TextView) view.findViewById(R.id.tv_telephone);
        tv_cr_org_code = (TextView) view.findViewById(R.id.tv_cr_org_code);

        ll_hypertension = (LinearLayout) view.findViewById(R.id.ll_hypertension);
        ll_diabetes = (LinearLayout) view.findViewById(R.id.ll_diabetes);
        ll_mental_illness = (LinearLayout) view.findViewById(R.id.ll_mental_illness);
        ll_maternal = (LinearLayout) view.findViewById(R.id.ll_maternal);
        ll_children = (LinearLayout) view.findViewById(R.id.ll_children);
        tvMedicalEntry = (TextView) view.findViewById(R.id.tvMedicalEntry);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.ll_hypertension:
                mActivity.replace("hypertensionFragment", QueryResultFragment.newInstance(1, searchType, content), true);
                break;
            case R.id.ll_diabetes:
                mActivity.replace("hypertensionFragment", QueryResultFragment.newInstance(2, searchType, content), true);
                break;
            case R.id.ll_mental_illness:
                mActivity.replace("hypertensionFragment", QueryResultFragment.newInstance(3, searchType, content), true);
                break;
            case R.id.ll_maternal:
                mActivity.replace("hypertensionFragment", QueryResultFragment.newInstance(4, searchType, content), true);
                break;
            case R.id.ll_children:
                mActivity.replace("hypertensionFragment", QueryResultFragment.newInstance(5, searchType, content), true);
                break;
            case R.id.tvMedicalEntry:
                mActivity.replace("medicalEntryFragment", MedicalEntryFragment.newInstance(ehr_id), true);
                break;
            default:
                break;
        }
    }

    public static ArchivesDetailFragment newInstance(int type, ArchivesQueryInDTO data, int searchType, String content) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putInt("type", type);
        args.putInt("searchType", searchType);
        args.putString("content", content);
        ArchivesDetailFragment fragment = new ArchivesDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

}