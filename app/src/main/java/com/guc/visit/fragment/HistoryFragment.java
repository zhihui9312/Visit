package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.guc.visit.R;
import com.guc.visit.adapter.HypertensionHistoryAdapter;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.HypertensionHistoryDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<HypertensionHistoryDTO> data;
    private String name;
    private int type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_hypertension_history);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        data = (ArrayList<HypertensionHistoryDTO>) bundle.getSerializable("data");
        name = bundle.getString("name");
        type = bundle.getInt("type");
        HypertensionHistoryAdapter adapter = new HypertensionHistoryAdapter(data, R.layout.layout_item_hypertension_history);
        listView.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // super.onSaveInstanceState(outState);
    }

    @Override
    protected void setListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (type) {
                    case 0:
                        break;
                    case 1:
                        mActivity.replace("HypertensionAddFragment", HypertensionAddFragment.newInstance(data.get(position).getRecord_code(), 1, name), true);
                        break;
                    case 2:
                        mActivity.replace("DiabetesAddFragment", DiabetesAddFragment.newInstance(data.get(position).getRecord_code(), 1, name), true);
                        break;
                    case 3:
                        mActivity.replace("MentalAddFragment", MentalAddFragment.newInstance(data.get(position).getRecord_code(), 1, name), true);
                        break;
                    case 4:
                        //孕妇随访，另一个fragment
                        break;
                    case 5:
                        HypertensionHistoryDTO dto = data.get(position);
                        String tbl_name = dto.getTbl_name();
                        String record_code = dto.getRecord_code();
                        HashMap<String, String> map = new HashMap<>();
                        map.put("tbl_name", tbl_name);
                        map.put("record_code", record_code);
                        map.put("name", name);
                        map.put("operation", "1");
                        if (tbl_name.equals("ms_children_visit_newbron")) {// 儿童-新生儿家庭访视
                            mActivity.replace("childAddNewBronFragment", ChildAddNewBronFragment.newInstance(map), true);
                        } else if (tbl_name.equals("ms_children_visit_oneyear")) {// 产后1年内次随访
                            mActivity.replace("childAdd1Fragment", ChildAdd1Fragment.newInstance(map), true);
                        } else if (tbl_name.equals("ms_children_visit_onetwoyear")) {// 产后访视-2
                            mActivity.replace("childAdd12Fragment", ChildAdd12Fragment.newInstance(map), true);
                        } else if (tbl_name.equals("ms_children_visit_threeyear")) {// 产后42天随访
                            mActivity.replace("childAdd36Fragment", ChildAdd36Fragment.newInstance(map), true);
                        }
                        break;
                    default:
                        break;
                }
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

    public static HistoryFragment newInstance(ArrayList<HypertensionHistoryDTO> data, String name, int type) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putString("name", name);
        args.putInt("type", type);
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
