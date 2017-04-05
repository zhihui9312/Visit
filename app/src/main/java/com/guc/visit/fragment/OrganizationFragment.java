package com.guc.visit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.adapter.RegisterOrganizationItemAdapter;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.Organization;
import com.guc.visit.domain.OrganizationDTO;
import com.guc.visit.net.DefaultErrorListener;
import com.guc.visit.net.GucNetEngineClient;
import com.guc.visit.utils.PingYinUtil;
import com.guc.visit.utils.ToastUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrganizationFragment extends BaseFragment {
    private ListView organization_listView;
    private List<OrganizationDTO> list = new ArrayList<>();
    private List<Organization> list2 = new ArrayList<>();

    private RegisterOrganizationItemAdapter adapter;
    private String[] ids;
    private String[] orgnames;
    private String[] pingyins;
    private EditText input_search;
    private TextView tv_null;
    private ImageView iv_clear;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                setData();
            }
            super.handleMessage(msg);
        }
    };
    private int operation;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        operation = getArguments().getInt("operation");
        if (operation == 0) {
            getOrganization();
        } else if (operation == 1) {
            getArchivesOrganization();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_organization);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initData() {

    }

    private void setData() {
        adapter = new RegisterOrganizationItemAdapter(mActivity, ids, orgnames, pingyins);
        organization_listView.setAdapter(adapter);
    }

    private void getOrganization() {
        materialDialog= showIndeterminateProgressDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getOrganization(new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getOrganizationResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray jsonArray = objResult.getJSONArray("organizationList");
                    if (jsonArray != null && jsonArray.size() > 0) {
                        list.addAll(JSON.parseArray(jsonArray.toJSONString(), OrganizationDTO.class));
                        initIds();
                        handler.sendEmptyMessage(1);
                    }
                } else {
                    showToast(errInfo);
                }
            }
        }, null,materialDialog);
    }

    private void getArchivesOrganization() {
        materialDialog= showIndeterminateProgressDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getArchivesOrganization(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getVillageListResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray array = objResult.getJSONArray("villageData");
                    if (array != null && array.size() > 0) {
                        List<Organization> organizations = JSON.parseArray(array.toJSONString(), Organization.class);
                        list2.addAll(organizations);
                        initIds2();
                        handler.sendEmptyMessage(1);
                    }
                } else {
                    showToast(errInfo);
                }
            }
        },null,materialDialog);
    }

    private void initIds() {
        ids = new String[list.size()];
        orgnames = new String[list.size()];
        pingyins = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ids[i] = list.get(i).getId();
            orgnames[i] = list.get(i).getOrgname();
            pingyins[i] = PingYinUtil.getPinYinHeadChar(list.get(i).getOrgname());
        }
    }

    private void initIds2() {
        int size = list2.size();
        ids = new String[size];
        orgnames = new String[size];
        pingyins = new String[size];
        for (int i = 0; i < size; i++) {
            ids[i] = list2.get(i).getCode();
            orgnames[i] = list2.get(i).getName();
            pingyins[i] = PingYinUtil.getPinYinHeadChar(list2.get(i).getName());
        }
    }

    @Override
    protected void setListeners() {
        input_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.searchTextChanged(input_search.getText().toString(), tv_null, iv_clear);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        iv_clear.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                iv_clear.setVisibility(View.GONE);
                input_search.setText("");
            }
        });
        organization_listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mActivity.popBackStack(1);
                String organizationId = "";
                String orgName = "";
                if (operation == 0) {
                    organizationId = list.get(position).getId();
                    orgName = list.get(position).getOrgname();
                } else if (operation == 1) {
                    organizationId = list2.get(position).getCode();
                    orgName = list2.get(position).getName();
                }

                Fragment targetFragment = getTargetFragment();
                if (targetFragment == null) {
                    return;
                } else {
                    Intent intent = new Intent();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("organizationId", organizationId);
                    map.put("orgName", orgName);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("map", map);
                    intent.putExtras(bundle);
                    targetFragment.onActivityResult(1000, 1001, intent);
                }

            }
        });
    }

    @Override
    protected void initWidget(View view) {
        organization_listView = (ListView) view.findViewById(R.id.organization_listView);
        input_search = (EditText) view.findViewById(R.id.input_search);
        tv_null = (TextView) view.findViewById(R.id.tv_null);
        iv_clear = (ImageView) view.findViewById(R.id.iv_clear);
    }

    /**
     * @param targetFragment
     * @param operation      0  注册，1  档案
     * @return
     */
    public static OrganizationFragment newInstance(Fragment targetFragment, int operation) {
        OrganizationFragment fragment = new OrganizationFragment();
        fragment.setTargetFragment(targetFragment, 1000);
        Bundle args = new Bundle();
        args.putInt("operation", operation);
        fragment.setArguments(args);
        return fragment;
    }
}
