package com.guc.visit.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guc.visit.R;
import com.guc.visit.adapter.PregnantHistoryDetailAdapter;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.PregnantInDTO;

import java.util.ArrayList;
import java.util.HashMap;


public class PregnantHistoryDetailFragment extends BaseFragment {
    private ListView listView;
    private ArrayList<PregnantInDTO> data;
    private HashMap<String, String> map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_pregnant_history_detail);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        map = (HashMap<String, String>) bundle.getSerializable("map");
        data = (ArrayList<PregnantInDTO>) bundle.getSerializable("data");
        PregnantHistoryDetailAdapter adapter = new PregnantHistoryDetailAdapter(data, R.layout.layout_item_pregnant_detail);
        listView.setAdapter(adapter);
        controlBar("随访记录", R.string.back, true, true);
    }


    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        right_layout.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PregnantInDTO pregnantInDTO = data.get(position);
                String tbl_name = pregnantInDTO.getTbl_name();
                map.put("operation", "1");
                map.put("record_code", pregnantInDTO.getRecord_code());
                if (tbl_name.equals("ms_maternal_visit_before_one")) {// 产前第一次随访
                    mActivity.replace("pregnantAddFragment", PregnantAddFragment.newInstance(map), true);
                    // intent.setClass(mContext, MaternalBeforeOne.class);
                } else if (tbl_name.equals("ms_maternal_visit_before_five")) {// 产后2-5次随访
                    mActivity.replace("pregnantAdd25Fragment", PregnantAdd25Fragment.newInstance(map), true);
                    //  intent.setClass(mContext, MaternalBeforeFiveRecord.class);
                } else if (tbl_name.equals("ms_maternal_visit_after")) {// 产后访视
                    //  intent.setClass(mContext, MaternalAfterRecord.class);
                } else if (tbl_name.equals("ms_maternal_visit_after_42day")) {// 产后42天随访

                    //  intent.setClass(mContext, MaternalAfter42DayRecord.class);
                }
                // mActivity.replace("pregnanHistoryDetailFragment", PregnantAddFragment.newInstance(pregnantInDTO), true);
            }
        });
    }

    @Override
    protected void initWidget(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.right_layout:
                map.put("operation", "0");
                new MaterialDialog.Builder(mActivity).items(R.array.array_pregnant).itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        switch (position) {
                            case 0:
                                mActivity.replace("pregnantAddFragment", PregnantAddFragment.newInstance(map), true);
                                break;
                            case 1:
                                mActivity.replace("pregnant25AddFragment", PregnantAdd25Fragment.newInstance(map), true);
                                break;
                            case 2:
                                mActivity.replace("pregnantAddAfterFragment", PregnantAddAfterFragment.newInstance(map), true);
                                break;
                            case 3:
                                mActivity.replace("pregnantAddAfter42Fragment", PregnantAddAfter42Fragment.newInstance(map), true);
                                break;
                            default:
                                break;
                        }
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    public static PregnantHistoryDetailFragment newInstance(ArrayList<PregnantInDTO> data, HashMap<String, String> map) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putSerializable("map", map);
        PregnantHistoryDetailFragment fragment = new PregnantHistoryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
