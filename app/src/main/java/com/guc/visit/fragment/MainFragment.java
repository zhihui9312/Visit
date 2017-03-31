package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.adapter.MainAdapter;
import com.guc.visit.application.GucApplication;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.HomeItemDTO;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.widget.LineGridView;

import java.util.ArrayList;

public class MainFragment extends BaseFragment {
    private ArrayList<HomeItemDTO> data = new ArrayList<>();
    private LineGridView mGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_main);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        updateData();
        super.onCreate(savedInstanceState);
    }

    private void updateData() {
        String[] titles = new String[]{getResources().getString(R.string.archives_query),
                getResources().getString(R.string.hypertension_visit),
                getResources().getString(R.string.diabetes_visit),
                getResources().getString(R.string.psychosis_visit),
                getResources().getString(R.string.pregnant_visit),
                getResources().getString(R.string.child_visit),
                getResources().getString(R.string.task_remind), getResources().getString(R.string.workload_statistics), getResources().getString(R.string.archives_entry)};
        int[] images = new int[]{R.mipmap.ic_home_x1, R.mipmap.ic_home_x2, R.mipmap.ic_home_x3, R.mipmap.ic_home_x4, R.mipmap.ic_home_x5, R.mipmap.ic_home_x6, R.mipmap.ic_home_x7, R.mipmap.ic_home_x8,R.mipmap.ic_home_x8};
        int length=images.length;
        for (int i = 0; i < length; i++) {
            HomeItemDTO dto = new HomeItemDTO();
            dto.setLable(titles[i]);
            dto.setSourceId(images[i]);
            data.add(dto);
        }
    }

    @Override
    protected void initData() {
        controlBar(R.string.app_name, null, false, false);
        MainAdapter adapter = new MainAdapter(data, R.layout.layout_item_main);
        mGridView.setAdapter(adapter);
        loginLog();
        GucApplication.getInstance().setIslogin(true);
    }

    private void loginLog() {
        GucNetEngineClient.loginLog(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
    }

    @Override
    protected void setListeners() {

        mGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mActivity.replace("queryFragment", QueryFragment.newInstance(position), true);
                        break;
                    case 1:
                        mActivity.replace("queryFragment", QueryFragment.newInstance(position), true);
                        break;
                    case 2:
                        mActivity.replace("queryFragment", QueryFragment.newInstance(position), true);
                        break;
                    case 3:
                        mActivity.replace("queryFragment", QueryFragment.newInstance(position), true);
                        break;
                    case 4:
                        mActivity.replace("queryFragment", QueryFragment.newInstance(position), true);
                        break;
                    case 5:
                        mActivity.replace("queryFragment", QueryFragment.newInstance(position), true);
                        break;
                    case 6:
                        mActivity.replace("taskFragment", TaskFragment.newInstance(), true);
                        break;
                    case 7:
                        mActivity.replace("workloadStatisticsFragment", WorkloadStatisticsFragment.newInstance(), true);
                        break;
                    case 8:
                        mActivity.replace("archivesAddFragment", ArchivesAddFragment.newInstance(), true);
                        break;
                    case 9:
                        mActivity.replace("queryFragment", QueryFragment.newInstance(0), true);
                        break;
                    default:
                        //mActivity.replace("hospitallistfragment", HospitalListFragment.newInstance(position), true);
                        break;
                }
            }
        });
    }

    @Override
    protected void initWidget(View view) {
        mGridView = (LineGridView) view.findViewById(R.id.gridview);
    }

    public static Fragment newInstance() {
        return new MainFragment();
    }


}
