package com.guc.visit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.GucBaseAdapter;
import com.guc.visit.domain.HomeItemDTO;

import java.util.ArrayList;

public class MainAdapter extends GucBaseAdapter {

    private ArrayList<HomeItemDTO> data;
    private int layout;

    public MainAdapter(ArrayList<HomeItemDTO> data, int layout) {
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HomeItemDTO dto = data.get(position);
        viewHolder.imageView.setImageResource(dto.getSourceId());
        viewHolder.textView.setText(dto.getLable());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
