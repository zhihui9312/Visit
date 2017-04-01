package com.guc.visit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.GucBaseAdapter;
import com.guc.visit.domain.PregnantInDTO;
import com.guc.visit.utils.StrUtil;

import java.util.ArrayList;


public class PregnantHistoryDetailAdapter extends GucBaseAdapter {
    private ArrayList<PregnantInDTO> data;
    private int layout;

    public PregnantHistoryDetailAdapter(ArrayList<PregnantInDTO> data, int layout) {
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            viewHolder.visit_date = (TextView) convertView.findViewById(R.id.visit_date);
            viewHolder.doctor = (TextView) convertView.findViewById(R.id.doctor);
            viewHolder.drug = (TextView) convertView.findViewById(R.id.drug);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PregnantInDTO dto = data.get(position);
        viewHolder.visit_date.setText(StrUtil.getFormatDateStr(dto.getVisit_date()));
        viewHolder.doctor.setText(dto.getDoctor());
        viewHolder.drug.setText(dto.getDrug());
        return convertView;
    }

    class ViewHolder {
        private TextView visit_date;
        private TextView doctor;
        private TextView drug;
    }
}
