package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guc.visit.R;
import com.guc.visit.adapter.PregnantHistoryAdapter;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.PregnantBaseDTO;
import com.guc.visit.domain.PregnantInDTO;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 孕妇随访历史记录
 */
public class PregnantHistoryFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<PregnantBaseDTO<JSONObject>> data;
    private ArrayList<PregnantInDTO> pregnant_detail_data = new ArrayList<>();
    private HashMap<String,String> map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_hypertension_history);
    }

    @Override
    protected void initData() {
        Bundle bundle=getArguments();
        map=(HashMap<String,String>)bundle.getSerializable("map");
        data = (ArrayList<PregnantBaseDTO<JSONObject>>) bundle.getSerializable("data");
        PregnantHistoryAdapter adapter = new PregnantHistoryAdapter(data, R.layout.layout_item_pregnant_history);
        listView.setAdapter(adapter);
    }

    @Override
    protected void setListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject jsonObject = (JSONObject) data.get(position).getVisitList();
                JSONArray jsonArray = jsonObject.getJSONArray("visitList");
                List<PregnantInDTO> temp = JSON.parseArray(jsonArray.toJSONString(), PregnantInDTO.class);
                pregnant_detail_data.clear();
                pregnant_detail_data.addAll(temp);
                mActivity.replace("pregnanHistoryDetailFragment", PregnantHistoryDetailFragment.newInstance(pregnant_detail_data,map), true);
            }
        });
    }

    @Override
    protected void initWidget(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    public void onClick(View v) {

    }

    public static PregnantHistoryFragment newInstance(ArrayList<PregnantBaseDTO<JSONObject>> data, HashMap<String,String> map) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putSerializable("map",map);
        PregnantHistoryFragment fragment = new PregnantHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
