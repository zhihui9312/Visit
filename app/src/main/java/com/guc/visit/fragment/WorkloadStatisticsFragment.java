package com.guc.visit.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.adapter.WorkloadStatisticsAdapter;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.WorkloadStatistics;
import com.guc.visit.net.GucNetEngineClient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class WorkloadStatisticsFragment extends BaseFragment implements View.OnTouchListener {
    private ListView listView;
    private TextView tvQuery;
    private EditText etStartDate;
    private EditText etendDate;
    private String currentDateStr;


    private WorkloadStatisticsAdapter adapter;
    private ArrayList<WorkloadStatistics> data = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                adapter.notifyDataSetChanged();
            }
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_workload_statistics);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        currentDateStr = DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
        getNetworkData(currentDateStr, currentDateStr);
        super.onCreate(savedInstanceState);
    }

    private void getNetworkData(String startDate, String endDate) {
        data.clear();
        materialDialog = showIndeterminateProgressDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getWorkloadStatistics(startDate, endDate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getWorkmbbzResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray jsonArray = objResult.getJSONArray("workmbbzList");
                    if (jsonArray != null && jsonArray.size() > 0) {
                        List<WorkloadStatistics> lists = JSON.parseArray(jsonArray.toJSONString(), WorkloadStatistics.class);
                        data.addAll(lists);
                    }
                    handler.sendEmptyMessage(1);
                } else {
                    showToast(errInfo);
                }
            }
        }, null, materialDialog);
    }

    @Override
    protected void initData() {
        controlBar(R.string.workload_statistics, R.string.back, true, false);
        adapter = new WorkloadStatisticsAdapter(data, R.layout.layout_item_workload_statistics);
        listView.setAdapter(adapter);
        etStartDate.setText(currentDateStr);
        etendDate.setText(currentDateStr);
    }

    @Override
    protected void setListeners() {
        ll_back.setOnClickListener(this);
        etStartDate.setOnTouchListener(this);
        etendDate.setOnTouchListener(this);
        tvQuery.setOnClickListener(this);
    }

    @Override
    protected void initWidget(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        etStartDate = (EditText) view.findViewById(R.id.etStartDate);
        etendDate = (EditText) view.findViewById(R.id.etendDate);
        tvQuery = (TextView) view.findViewById(R.id.tvQuery);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            switch (v.getId()) {
                case R.id.etendDate:
                    showDatePicker(etendDate);
                    break;
                case R.id.etStartDate:
                    showDatePicker(etStartDate);
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            case R.id.tvQuery:
                getNetworkData(getToTrim(etStartDate), getToTrim(etendDate));
                break;
            default:
                break;
        }
    }

    public static WorkloadStatisticsFragment newInstance() {

        Bundle args = new Bundle();

        WorkloadStatisticsFragment fragment = new WorkloadStatisticsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
