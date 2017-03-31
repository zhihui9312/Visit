package com.guc.visit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.GucBaseAdapter;
import com.guc.visit.domain.QueryResult;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class QueryResultAdapter<T> extends GucBaseAdapter {
    private ArrayList<QueryResult<T>> data;
    private int layout;

    public QueryResultAdapter(ArrayList<QueryResult<T>>   data, int layout) {
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QueryResultAdapter.ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new QueryResultAdapter.ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (QueryResultAdapter.ViewHolder) convertView.getTag();
        }


        T t = data.get(position).getBaseInfo();
        Class userCla = (Class) t.getClass();

        Field[] fs = userCla.getDeclaredFields ();
        for ( int i = 0 ; i < fs. length ; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            String fieldName=f.getName();
            if(fieldName.equals("name")){
                try {
                    String val = (String)f.get(t);
                    viewHolder.tv_name.setText(val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else if(fieldName.equals("address_str")){
                try {
                    String val = (String)f.get(t);
                    viewHolder.tv_address.setText(val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return convertView;
    }

    class ViewHolder {
        private TextView tv_name;
        private TextView tv_address;
    }
}
