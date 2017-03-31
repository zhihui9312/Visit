package com.guc.visit.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.GucBaseAdapter;
import com.guc.visit.domain.HypertensionHistoryDTO;
import com.guc.visit.utils.StrUtil;

import java.util.ArrayList;

public class HypertensionHistoryAdapter extends GucBaseAdapter {
    private ArrayList<HypertensionHistoryDTO> data;
    private int layout;

    public HypertensionHistoryAdapter(ArrayList<HypertensionHistoryDTO> data, int layout) {
        this.data = data;
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            viewHolder.tv_visit_date = (TextView) convertView.findViewById(R.id.tv_visit_date);
            viewHolder.tv_doctor = (TextView) convertView.findViewById(R.id.tv_doctor);
            viewHolder.tv_drug = (TextView) convertView.findViewById(R.id.tv_drug);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HypertensionHistoryDTO dto = data.get(position);
        viewHolder.tv_visit_date.setText(StrUtil.getFormatDateStr(dto.getVisit_date()));
        viewHolder.tv_doctor.setText(dto.getDoctor());
        viewHolder.tv_drug.setText(dto.getDrug());
        return convertView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    class ViewHolder {
        private TextView tv_visit_date;
        private TextView tv_doctor;
        private TextView tv_drug;
    }
}
