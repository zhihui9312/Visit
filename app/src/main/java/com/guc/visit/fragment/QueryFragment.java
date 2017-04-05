package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.utils.ToastUtils;

import org.apache.commons.lang3.StringUtils;


public class QueryFragment extends BaseFragment {

    private TextView tv_search;
    private ListView listView;
    private String[] strings = new String[]{"姓名", "农合编号", "社保编号", "身份证号"};
    private int search_type = 2;
    private EditText content_input;
    private int type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_archives_query);
    }

    @Override
    protected void initData() {
        listView.setAdapter(new ArrayAdapter<>(mActivity, R.layout.layout_item_check, strings));
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        type = getArguments().getInt("type");
        switch (type) {
            case 0:
                controlBar(R.string.archives_query, R.string.back, true, false);
                break;
            case 1:
                controlBar(R.string.hypertension_query, R.string.back, true, false);
                break;
            case 2:
                controlBar(R.string.diabetes_visit_query, R.string.back, true, false);
                break;
            case 3:
                controlBar(R.string.psychosis_visit, R.string.back, true, false);
                break;
            case 4:
                controlBar(R.string.pregnant_visit, R.string.back, true, false);
                break;
            case 5:
                controlBar(R.string.child_visit, R.string.back, true, false);
                break;
            default:
                break;
        }
        listView.setItemChecked(0, true);
    }

    @Override
    protected void setListeners() {
        tv_search.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search_type = position + 2;
            }
        });
        ll_back.setOnClickListener(this);
    }

    @Override
    protected void initWidget(View view) {
        tv_search = (TextView) view.findViewById(R.id.tv_search);
        listView = (ListView) view.findViewById(R.id.listView);
        content_input = (EditText) view.findViewById(R.id.content_input);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                String content = content_input.getText().toString().trim();
                content="王婷";
                if (StringUtils.isBlank(content)) {
                    ToastUtils.showLong(mActivity, R.string.input_content_not_null);
                    return;
                }
                mActivity.replace("QueryResultFragment", QueryResultFragment.newInstance(type, search_type, content), true);
                break;
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            default:
                break;
        }
        hideInput();
    }

    public static QueryFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        QueryFragment fragment = new QueryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}