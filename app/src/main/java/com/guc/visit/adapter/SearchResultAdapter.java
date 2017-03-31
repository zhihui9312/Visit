package com.guc.visit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.GucBaseAdapter;
import com.guc.visit.domain.ArchivesQueryInDTO;
import com.guc.visit.domain.ArchivesQueryOutDTO;

import java.util.ArrayList;

public class SearchResultAdapter extends GucBaseAdapter {
    private ArrayList<ArchivesQueryOutDTO<ArchivesQueryInDTO>> data;
    private int layout;

    public SearchResultAdapter(ArrayList<ArchivesQueryOutDTO<ArchivesQueryInDTO>> data, int layout) {
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
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ArchivesQueryOutDTO<ArchivesQueryInDTO> dto = data.get(position);
        viewHolder.tv_name.setText(dto.getPersonBaseInfoEntity().getName());
        //viewHolder.tv_address.setText(dto.getPersonBaseInfoEntity().getAddress());
        return convertView;
    }

    class ViewHolder {
        private TextView tv_name;
        private TextView tv_address;
    }
}
