package com.guc.visit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.guc.visit.R;
import com.guc.visit.base.GucBaseAdapter;
import com.guc.visit.domain.PregnantBaseDTO;
import com.guc.visit.domain.PregnantInDTO;
import com.guc.visit.domain.PregnantOutDTO;
import com.guc.visit.utils.StrUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PregnantHistoryAdapter extends GucBaseAdapter {
    private ArrayList<PregnantBaseDTO<JSONObject>> data;
    private int layout;

    public PregnantHistoryAdapter(ArrayList<PregnantBaseDTO<JSONObject>> data, int layout) {
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
            viewHolder.create_week = (TextView) convertView.findViewById(R.id.create_week);
            viewHolder.create_week_days = (TextView) convertView.findViewById(R.id.create_week_days);
            viewHolder.high_score = (TextView) convertView.findViewById(R.id.high_score);
            viewHolder.is_high_risk = (TextView) convertView.findViewById(R.id.is_high_risk);
            viewHolder.create_week = (TextView) convertView.findViewById(R.id.create_week);
            viewHolder.record_code = (TextView) convertView.findViewById(R.id.record_code);
            viewHolder.due_birth_date = (TextView) convertView.findViewById(R.id.due_birth_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PregnantBaseDTO dto = data.get(position);
        viewHolder.create_week.setText(dto.getCreate_week());
        viewHolder.create_week_days.setText(dto.getCreate_week_days());
        viewHolder.high_score.setText(dto.getHigh_score());
        viewHolder.is_high_risk.setText(dto.getIs_high_risk());
        viewHolder.due_birth_date.setText(StrUtil.getFormatDateStr(dto.getDue_birth_date()));
        viewHolder.record_code.setText(dto.getRecord_code());
        return convertView;
    }

    class ViewHolder {
        private TextView create_week;
        private TextView create_week_days;
        private TextView high_score;
        private TextView is_high_risk;
        private TextView due_birth_date;
        private TextView record_code;
    }
}
