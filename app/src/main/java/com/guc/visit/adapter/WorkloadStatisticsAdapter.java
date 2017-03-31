package com.guc.visit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.GucBaseAdapter;
import com.guc.visit.domain.WorkloadStatistics;

import java.util.ArrayList;


public class WorkloadStatisticsAdapter extends GucBaseAdapter {

    private ArrayList<WorkloadStatistics> data;
    private int layout;

    public WorkloadStatisticsAdapter(ArrayList<WorkloadStatistics> data,int layout) {
        this.data = data;
        this.layout=layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            viewHolder.mbbz=(TextView) convertView.findViewById(R.id.mbbz);
            viewHolder.rs=(TextView) convertView.findViewById(R.id.rs);
            viewHolder.rc=(TextView) convertView.findViewById(R.id.rc);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        WorkloadStatistics workloadStatistics=data.get(position);
        viewHolder.mbbz.setText(workloadStatistics.getMbbz());
        viewHolder.rs.setText(workloadStatistics.getRs());
        viewHolder.rc.setText(workloadStatistics.getRc());
        return convertView;
    }

    class ViewHolder {
        private TextView mbbz;
        private TextView rs;
        private TextView rc;
    }

}
