package com.guc.visit.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.guc.visit.R;
import com.guc.visit.adapter.QueryResultAdapter;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.ArchivesQueryInDTO;
import com.guc.visit.domain.ChildBaseInfo;
import com.guc.visit.domain.DiabetesDTO;
import com.guc.visit.domain.HypertensionBaseDTO;
import com.guc.visit.domain.MentalBaseDTO;
import com.guc.visit.domain.PregnantBaseDTO;
import com.guc.visit.domain.QueryResult;
import com.guc.visit.net.GucNetEngineClient;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class QueryResultFragment extends BaseFragment {

    private ListView listView;
    private QueryResultAdapter queryResultAdapter;

    private int type;
    private int searchType;
    private String content;

    private ArrayList<QueryResult<ArchivesQueryInDTO>> dataArchives = new ArrayList<>();
    private ArrayList<QueryResult<HypertensionBaseDTO>> dataHypertension = new ArrayList<>();
    private ArrayList<QueryResult<DiabetesDTO>> dataDiabetes = new ArrayList<>();
    private ArrayList<QueryResult<MentalBaseDTO>> dataMental = new ArrayList<>();
    private ArrayList<QueryResult<PregnantBaseDTO>> dataPregnant = new ArrayList<>();
    private ArrayList<QueryResult<ChildBaseInfo>> dataChild = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                queryResultAdapter.notifyDataSetChanged();
            }
            super.handleMessage(msg);

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getNetworkData();
        super.onCreate(savedInstanceState);
    }

    private void getNetworkData() {
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        searchType = bundle.getInt("search_type");
        content = bundle.getString("content");
        switch (type) {
            case 0:
                //content="王婷";
                getArchivesQuery(searchType, content);
                break;
            case 1:
                //content="李小光";
                getHypertension(searchType, content);
                break;
            case 2:
                //content="李长生";
                getDiabetes(searchType, content);
                break;
            case 3:
                //content="杨美术";
                getMentalBaseInfo(searchType, content);
                break;
            case 4:
                //content="吴美丽";
                getPregnantInfo(searchType, content);
                break;
            case 5:
                //content="王婷";
                getChildInfo(searchType, content);
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG", "onCreateView");
        return initView(inflater, container, R.layout.fragment_archives_result);
    }

    @Override
    protected void initData() {
        controlBar(R.string.search_result, R.string.back, true, false);
        updateUI();

    }

    private void updateUI() {
        switch (type) {
            case 0:
                queryResultAdapter = new QueryResultAdapter(dataArchives, R.layout.layout_item_result);
                listView.setAdapter(queryResultAdapter);
                break;
            case 1:
                queryResultAdapter = new QueryResultAdapter(dataHypertension, R.layout.layout_item_result);
                listView.setAdapter(queryResultAdapter);
                break;
            case 2:
                queryResultAdapter = new QueryResultAdapter(dataDiabetes, R.layout.layout_item_result);
                listView.setAdapter(queryResultAdapter);
                break;
            case 3:
                queryResultAdapter = new QueryResultAdapter(dataMental, R.layout.layout_item_result);
                listView.setAdapter(queryResultAdapter);
                break;
            case 4:
                queryResultAdapter = new QueryResultAdapter(dataPregnant, R.layout.layout_item_result);
                listView.setAdapter(queryResultAdapter);
                break;
            case 5:
                queryResultAdapter = new QueryResultAdapter(dataChild, R.layout.layout_item_result);
                listView.setAdapter(queryResultAdapter);
                break;
            default:
                break;
        }
    }

    /**
     * 档案
     *
     * @param searchType
     * @param content
     */
    private void getArchivesQuery(int searchType, String content) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getArchivesQuery(searchType, content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject obj_res = jsonObject.getJSONObject("getPersonBaseInfoResult");
                String errInfo = obj_res.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray jsonArray = obj_res.getJSONArray("personList");
                    if (jsonArray != null && jsonArray.size() > 0) {
                        int size = jsonArray.size();
                        for (int i = 0; i < size; i++) {
                            JSONObject objRubbish = jsonArray.getJSONObject(i);
                            JSONObject objPersonBaseInfoEntity = objRubbish.getJSONObject("personBaseInfoEntity");
                            ArchivesQueryInDTO dto = JSON.parseObject(objPersonBaseInfoEntity.toJSONString(), ArchivesQueryInDTO.class);
                            QueryResult<ArchivesQueryInDTO> queryResult = new QueryResult<>();
                            queryResult.setBaseInfo(dto);
                            dataArchives.add(queryResult);
                        }
                        handler.sendEmptyMessage(1);
                    } else {
                        showToast(R.string.not_find);
                    }
//                    List<ArchivesQueryOutDTO<ArchivesQueryInDTO>> temp = JSON.parseObject(jsonArray.toJSONString(), new TypeReference<List<ArchivesQueryOutDTO<ArchivesQueryInDTO>>>() {
//                    });
//                    dataArchives.addAll(temp);
//                    adapter.notifyDataSetChanged();
                } else {
                    showToast(errInfo);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });
    }


    /**
     * 高血压
     *
     * @param searchType
     * @param content
     */
    private void getHypertension(int searchType, String content) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getHypertension(searchType, content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject jsonObject_base = jsonObject.getJSONObject("getHypertensionInfoResult");
                String errInfo = jsonObject_base.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray jsonArray = jsonObject_base.getJSONArray("personList");
                    if (jsonArray != null && jsonArray.size() > 0) {
                        int length = jsonArray.size();
                        for (int i = 0; i < length; i++) {
                            JSONObject objRubbish = jsonArray.getJSONObject(i);
                            JSONObject objBase2 = objRubbish.getJSONObject("hypertensionBase2");
                            JSONObject jsonBase = objBase2.getJSONObject("hypertensionBase");
                            HypertensionBaseDTO hypertensionBaseDTO = JSON.parseObject(jsonBase.toJSONString(), HypertensionBaseDTO.class);

                            JSONObject visitRecord2 = objRubbish.getJSONObject("visitRecord2");
                            JSONArray arrayHistory = visitRecord2.getJSONArray("visitList");
                            QueryResult<HypertensionBaseDTO> queryResult = new QueryResult();
                            queryResult.setJsonArray(arrayHistory);
                            queryResult.setBaseInfo(hypertensionBaseDTO);
                            dataHypertension.add(queryResult);
                        }
                        handler.sendEmptyMessage(1);
                    } else {
                        showToast(R.string.not_find);
                    }
                } else {
                    showToast(errInfo);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });
    }

    /**
     * 糖尿病
     *
     * @param searchType
     * @param content
     */
    private void getDiabetes(int searchType, String content) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getDiabetes(searchType, content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject getDiabetesInfoResult = jsonObject.getJSONObject("getDiabetesInfoResult");
                String errInfo = getDiabetesInfoResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray jsonArray = getDiabetesInfoResult.getJSONArray("personList");
                    if (jsonArray != null && jsonArray.size() > 0) {
                        int size = jsonArray.size();
                        for (int i = 0; i < size; i++) {
                            JSONObject objRubbish = jsonArray.getJSONObject(i);
                            JSONObject objDiabetesBase2 = objRubbish.getJSONObject("diabetesBase2");
                            JSONObject objDiabetesBase = objDiabetesBase2.getJSONObject("diabetesBase");
                            DiabetesDTO diabetesDTO = JSON.parseObject(objDiabetesBase.toJSONString(), DiabetesDTO.class);

                            JSONObject objVisitRecord2 = objRubbish.getJSONObject("visitRecord2");
                            JSONArray arrayHistory = objVisitRecord2.getJSONArray("visitList");
                            QueryResult<DiabetesDTO> queryResult = new QueryResult<>();
                            queryResult.setJsonArray(arrayHistory);
                            queryResult.setBaseInfo(diabetesDTO);
                            dataDiabetes.add(queryResult);
                        }
                        handler.sendEmptyMessage(1);
                    } else {
                        showToast(R.string.not_find);
                    }
                } else {
                    showToast(errInfo);
                }
//                    ToastUtils.showLong(mActivity, errInfo);
//                    return;
//                }
//                JSONArray jsonArray = getDiabetesInfoResult.getJSONArray("personList");
//                if (jsonArray.size() <= 0) {
//                    ToastUtils.showLong(mActivity, R.string.not_find);
//                    return;
//                }
//                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
//
//                JSONObject obj_history = jsonObject1.getJSONObject("visitRecord2");
//                JSONArray array_visitList = obj_history.getJSONArray("visitList");
//
//                List<HypertensionHistoryDTO> temp = JSON.parseArray(array_visitList.toJSONString(), HypertensionHistoryDTO.class);
//                data.addAll(temp);
//
//                JSONObject diabetesBase2 = jsonObject1.getJSONObject("diabetesBase2");
//                JSONObject obj_diabetesBase = diabetesBase2.getJSONObject("diabetesBase");
//                diabetesDTO = JSON.parseObject(obj_diabetesBase.toJSONString(), DiabetesDTO.class);
//                diabetesBaseFragment = DiabetesBaseFragment.newInstance(diabetesDTO);
//                replace("diabetesBaseFragment", diabetesBaseFragment, false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });
    }

    /**
     * 精神病
     *
     * @param searchType
     * @param content
     */
    private void getMentalBaseInfo(int searchType, String content) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getMentalBase(searchType, content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject obj_res = jsonObject.getJSONObject("getMentalInfoResult");
                String errInfo = obj_res.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray jsonArray = obj_res.getJSONArray("personList");
                    if (jsonArray != null && jsonArray.size() > 0) {
                        int size = jsonArray.size();
                        for (int i = 0; i < size; i++) {
                            JSONObject objRubbish = jsonArray.getJSONObject(i);
                            JSONObject objMentalBase2 = objRubbish.getJSONObject("mentalBase2");
                            JSONObject objMentalBase = objMentalBase2.getJSONObject("mentalBase");
                            MentalBaseDTO MentalBaseDTO = JSON.parseObject(objMentalBase.toJSONString(), MentalBaseDTO.class);

                            JSONObject objVisitRecord2 = objRubbish.getJSONObject("visitRecord2");
                            JSONArray arrayHistory = objVisitRecord2.getJSONArray("visitList");
                            QueryResult<MentalBaseDTO> queryResult = new QueryResult<>();
                            queryResult.setBaseInfo(MentalBaseDTO);
                            queryResult.setJsonArray(arrayHistory);
                            dataMental.add(queryResult);
                        }
                        handler.sendEmptyMessage(1);
                    } else {
                        showToast(R.string.not_find);
                    }
//                    JSONObject obj_array0 = jsonArray.getJSONObject(0);
//                    JSONObject obj_base = obj_array0.getJSONObject("mentalBase2");
//                    JSONObject obj_base_info = obj_base.getJSONObject("mentalBase");
//                    mentalBaseDTO = JSON.parseObject(obj_base_info.toJSONString(), MentalBaseDTO.class);
//
//                    mentalBaseInfoFragment = MentalBaseInfoFragment.newInstance(mentalBaseDTO);
//                    replace("mentalBaseInfoFragment", mentalBaseInfoFragment, false);
//
//                    JSONObject obj_history = obj_array0.getJSONObject("visitRecord2");
//                    JSONArray array_history = obj_history.getJSONArray("visitList");
//
//                    List<HypertensionHistoryDTO> temp = JSON.parseArray(array_history.toJSONString(), HypertensionHistoryDTO.class);
//                    data.addAll(temp);
                } else {
                    showToast(errInfo);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });
    }


    /**
     * 孕妇
     *
     * @param searchType
     * @param content
     */
    private void getPregnantInfo(int searchType, String content) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getPregnantInfo(searchType, content, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dismiss();
                        JSONObject jsonObject = JSON.parseObject(response);
                        JSONObject obj_res = jsonObject.getJSONObject("getMaternalInfoResult");
                        String errInfo = obj_res.getString("errInfo");
                        if (StringUtils.isBlank(errInfo)) {
                            JSONArray jsonArray = obj_res.getJSONArray("personList");
                            if (jsonArray != null && jsonArray.size() >= 0) {
                                int size = jsonArray.size();
                                for (int i = 0; i < size; i++) {


                                    JSONObject objRubbish = jsonArray.getJSONObject(i);
                                    JSONObject objMaternalBase = objRubbish.getJSONObject("maternalBase");
                                    PregnantBaseDTO pregnantBaseDTO = JSON.parseObject(objMaternalBase.toJSONString(), PregnantBaseDTO.class);
//
//                                pregnantBaseInfoFragment = PregnantBaseInfoFragment.newInstance(pregnantBaseDTO);
//                                replace("pregnantBaseInfoFragment", pregnantBaseInfoFragment, false);
//
                                    JSONArray arrayHistory = objRubbish.getJSONArray("registerList");
                                    QueryResult<PregnantBaseDTO> queryResult = new QueryResult<>();
                                    queryResult.setBaseInfo(pregnantBaseDTO);
                                    queryResult.setJsonArray(arrayHistory);

                                    dataPregnant.add(queryResult);
                                }
                                handler.sendEmptyMessage(1);
//                                List<PregnantBaseDTO<JSONObject>> temp = JSON.parseObject(history_list.toJSONString(), new TypeReference<List<PregnantBaseDTO<JSONObject>>>() {
//                                });
//                                pregnant_data.addAll(temp);
                            } else {
                                showToast(R.string.not_find);
                            }
                        } else {
                            showToast(errInfo);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dismiss();
                    }
                }
        );
    }

    /**
     * 儿童
     *
     * @param searchType
     * @param content
     */
    private void getChildInfo(int searchType, String content) {
        showDialog(R.string.is_loading_please_waite);
        GucNetEngineClient.getChildInfo(searchType, content, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismiss();
                JSONObject jsonObject = JSON.parseObject(response);
                JSONObject objResult = jsonObject.getJSONObject("getNewBronInfoResult");
                String errInfo = objResult.getString("errInfo");
                if (StringUtils.isBlank(errInfo)) {
                    JSONArray jsonArray = objResult.getJSONArray("personList");
                    if (jsonArray != null && jsonArray.size() > 0) {
                        int length = jsonArray.size();
                        for (int i = 0; i < length; i++) {
                            JSONObject objRubbish = jsonArray.getJSONObject(i);
                            JSONObject objNewBronBase = objRubbish.getJSONObject("newBronBase");
                            ChildBaseInfo childBaseInfo = JSON.parseObject(objNewBronBase.toJSONString(), ChildBaseInfo.class);

                            JSONObject objRubbishList = objRubbish.getJSONObject("visitList");
                            JSONArray arrayHistory = objRubbishList.getJSONArray("visitList");

                            QueryResult<ChildBaseInfo> queryResult = new QueryResult();

                            queryResult.setBaseInfo(childBaseInfo);
                            queryResult.setJsonArray(arrayHistory);
                            dataChild.add(queryResult);
                        }
                        handler.sendEmptyMessage(1);
                    } else {
                        showToast(R.string.not_find);
                    }

                } else {
                    showToast(errInfo);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismiss();
            }
        });

    }

    @Override
    protected void setListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (type) {
                    case 0:
                        mActivity.replace("archivesDetailFragment", ArchivesDetailFragment.newInstance(type, dataArchives.get(position).getBaseInfo(), searchType, content), true);
                        break;
                    case 1:
                        mActivity.replace("VisitFragment", VisitFragment.newInstance(type, dataHypertension.get(position)), true);
                        break;
                    case 2:
                        mActivity.replace("VisitFragment", VisitFragment.newInstance(type, dataDiabetes.get(position)), true);
                        break;
                    case 3:
                        mActivity.replace("VisitFragment", VisitFragment.newInstance(type, dataMental.get(position)), true);
                        break;
                    case 4:
                        mActivity.replace("VisitFragment", VisitFragment.newInstance(type, dataPregnant.get(position)), true);
                        break;
                    case 5:
                        mActivity.replace("VisitFragment", VisitFragment.newInstance(type, dataChild.get(position)), true);
                        break;
                    default:
                        break;
                }
            }
        });
        ll_back.setOnClickListener(this);
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
            default:
                break;
        }
    }

    public static QueryResultFragment newInstance(int type, int search_type, String content) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("search_type", search_type);
        args.putString("content", content);
        QueryResultFragment fragment = new QueryResultFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
