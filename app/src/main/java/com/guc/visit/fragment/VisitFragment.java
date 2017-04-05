package com.guc.visit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.guc.visit.R;
import com.guc.visit.base.BaseFragment;
import com.guc.visit.domain.ChildBaseInfo;
import com.guc.visit.domain.DiabetesDTO;
import com.guc.visit.domain.HypertensionBaseDTO;
import com.guc.visit.domain.HypertensionHistoryDTO;
import com.guc.visit.domain.MentalBaseDTO;
import com.guc.visit.domain.PregnantBaseDTO;
import com.guc.visit.domain.QueryResult;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VisitFragment extends BaseFragment {


    private TabLayout tabLayout;

    private FragmentManager fragmentManager;

    private HistoryFragment historyFragment;


    private HypertensionBaseInfoFragment hypertensionBaseInfoFragment;
    private DiabetesBaseFragment diabetesBaseFragment;
    private MentalBaseInfoFragment mentalBaseInfoFragment;
    private PregnantBaseInfoFragment pregnantBaseInfoFragment;
    private ChildBaseInfoFragment childBaseInfoFragment;

    private MentalBaseDTO mentalBaseDTO;

    private int type;
    private ArrayList<HypertensionHistoryDTO> historyList = new ArrayList<>();

    private ChildBaseInfo childBaseInfo;

    private QueryResult<HypertensionBaseDTO> dataHypertension;
    private HypertensionBaseDTO hypertensionBaseDTO;

    private QueryResult<DiabetesDTO> dataDiabetes;
    private DiabetesDTO diabetesDTO;

    private QueryResult<MentalBaseDTO> dataMental;
    private QueryResult<PregnantBaseDTO> dataPregnant;

    private PregnantBaseDTO pregnantBaseDTO;
    private QueryResult<ChildBaseInfo> dataChild;

    private Fragment pregnantHistoryFragment;

    private ArrayList<PregnantBaseDTO<JSONObject>> dataHistoryPregnant;

    private int postion = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, R.layout.fragment_hypertension);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getSendData();
        super.onCreate(savedInstanceState);
    }

    private void getSendData() {
        Bundle bundle = getArguments();
        type = bundle.getInt("type");
        switch (type) {
            case 0:
                break;
            case 1:
                dataHypertension = (QueryResult<HypertensionBaseDTO>) bundle.getSerializable("data");
                processJson(dataHypertension.getJsonArray());
                break;
            case 2:
                dataDiabetes = (QueryResult<DiabetesDTO>) bundle.getSerializable("data");
                processJson(dataDiabetes.getJsonArray());
                break;
            case 3:
                dataMental = (QueryResult<MentalBaseDTO>) bundle.getSerializable("data");
                processJson(dataMental.getJsonArray());
                break;
            case 4:
                dataPregnant = (QueryResult<PregnantBaseDTO>) bundle.getSerializable("data");
                processPregnantJson(dataPregnant.getJsonArray());
            case 5:
                dataChild = (QueryResult<ChildBaseInfo>) bundle.getSerializable("data");
                processJson(dataChild.getJsonArray());
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        updateUI();
        if(postion!=0){
            //switchToHistoryTab();
            //tabLayout
        }else{
            updateUI();
        }
    }

    private void updateUI() {
        switch (type) {
            case 0:
                break;
            case 1:
                hypertensionBaseDTO = dataHypertension.getBaseInfo();
                hypertensionBaseInfoFragment = HypertensionBaseInfoFragment.newInstance(hypertensionBaseDTO);
                replace("hypertensionBaseInfoFragment", hypertensionBaseInfoFragment);
                controlBar(hypertensionBaseDTO.getName(), R.string.back, true, true);
                break;
            case 2:
                diabetesDTO = dataDiabetes.getBaseInfo();
                diabetesBaseFragment = DiabetesBaseFragment.newInstance(diabetesDTO);
                replace("diabetesBaseFragment", diabetesBaseFragment);
                controlBar(diabetesDTO.getName(), R.string.back, true, true);
                break;
            case 3:
                mentalBaseDTO = dataMental.getBaseInfo();
                mentalBaseInfoFragment = MentalBaseInfoFragment.newInstance(mentalBaseDTO);
                replace("mentalBaseInfoFragment", mentalBaseInfoFragment);
                controlBar(mentalBaseDTO.getName(), R.string.back, true, true);
                break;
            case 4:
                pregnantBaseDTO = dataPregnant.getBaseInfo();
                pregnantBaseInfoFragment = PregnantBaseInfoFragment.newInstance(pregnantBaseDTO);
                replace("pregnantBaseInfoFragment", pregnantBaseInfoFragment);
                controlBar(pregnantBaseDTO.getName(), R.string.back, true, true);
                break;
            case 5:
                childBaseInfo = dataChild.getBaseInfo();
                childBaseInfoFragment = ChildBaseInfoFragment.newInstance(childBaseInfo);
                replace("childBaseInfoFragment", childBaseInfoFragment);
                controlBar(childBaseInfo.getName(), R.string.back, true, true);
                break;
            default:
                break;
        }
    }


    private void processJson(JSONArray jsonArray) {
        List<HypertensionHistoryDTO> tempData = JSON.parseArray(jsonArray.toJSONString(), HypertensionHistoryDTO.class);
        historyList.addAll(tempData);
    }

    private void processPregnantJson(JSONArray jsonArray) {
        dataHistoryPregnant = JSON.parseObject(jsonArray.toJSONString(), new TypeReference<ArrayList<PregnantBaseDTO<JSONObject>>>() {
        });
    }

    @Override
    protected void setListeners() {
        right_layout.setOnClickListener(this);
        ll_back.setOnClickListener(this);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    postion=0;
                    switch (type) {
                        case 0:
                            break;
                        case 1:
                            replace("hypertensionBaseInfoFragment", hypertensionBaseInfoFragment);
//                            if (historyFragment != null && hypertensionBaseInfoFragment.isHidden()) {
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.hide(historyFragment);
//                                fragmentTransaction.show(hypertensionBaseInfoFragment);
//                                fragmentTransaction.commitAllowingStateLoss();
//                            }
                            break;
                        case 2:
                            replace("diabetesBaseFragment", diabetesBaseFragment);
//                            if (historyFragment != null && diabetesBaseFragment.isHidden()) {
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.show(diabetesBaseFragment);
//                                fragmentTransaction.hide(historyFragment);
//                                fragmentTransaction.commitAllowingStateLoss();
//                            }
                            break;
                        case 3:
                            replace("mentalBaseInfoFragment", mentalBaseInfoFragment);
//                            if (historyFragment != null && mentalBaseInfoFragment.isHidden()) {
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.show(mentalBaseInfoFragment);
//                                fragmentTransaction.hide(historyFragment);
//                                fragmentTransaction.commitAllowingStateLoss();
//                            }

                            break;
                        case 4:
                            replace("pregnantBaseInfoFragment", pregnantBaseInfoFragment);
//                            if (pregnantHistoryFragment != null && pregnantBaseInfoFragment.isHidden()) {
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.show(pregnantBaseInfoFragment);
//                                fragmentTransaction.hide(pregnantHistoryFragment);
//                                fragmentTransaction.commitAllowingStateLoss();
//                            }
                            break;
                        case 5:
                            replace("childBaseInfoFragment", childBaseInfoFragment);
//                            if (historyFragment != null && childBaseInfoFragment.isHidden()) {
//                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                                fragmentTransaction.show(childBaseInfoFragment);
//                                fragmentTransaction.hide(historyFragment);
//                                fragmentTransaction.commitAllowingStateLoss();
//                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    postion = 1;
                    switchToHistoryTab();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void switchToHistoryTab() {
        switch (type) {
            case 0:
                break;
            case 1:
                if (historyFragment == null) {
                    historyFragment = HistoryFragment.newInstance(historyList, hypertensionBaseDTO.getName(), type);
                }
                replace("historyFragment", historyFragment);
                break;
            case 2:
                if (historyFragment == null) {
                    historyFragment = HistoryFragment.newInstance(historyList, diabetesDTO.getName(), type);
                }
                replace("historyFragment", historyFragment);
                break;
            case 3:
                if (historyFragment == null) {
                    historyFragment = HistoryFragment.newInstance(historyList, mentalBaseDTO.getName(), type);
                }
                replace("historyFragment", historyFragment);
                break;
            case 5:
                if (historyFragment == null) {
                    historyFragment = HistoryFragment.newInstance(historyList, childBaseInfo.getName(), type);
                }
                replace("historyFragment", historyFragment);
                break;
            case 4:
                if (pregnantHistoryFragment == null) {

                    HashMap<String, String> map = new HashMap<>();

                    map.put("ehr_id", pregnantBaseDTO.getEhr_id());
                    map.put("name", pregnantBaseDTO.getName());
                    pregnantHistoryFragment = PregnantHistoryFragment.newInstance(dataHistoryPregnant, map);
                }
                replace("pregnantHistoryFragment", pregnantHistoryFragment);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initWidget(View view) {
        fragmentManager = getChildFragmentManager();
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("基本信息"));
        tabLayout.addTab(tabLayout.newTab().setText("随访历史"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_layout:
                switch (type) {
                    case 1:
                        mActivity.replace("HypertensionAddFragment", HypertensionAddFragment.newInstance(hypertensionBaseDTO, 0), true);
                        break;
                    case 2:
                        mActivity.replace("DiabetesAddFragment", DiabetesAddFragment.newInstance(diabetesDTO, 0), true);
                        break;
                    case 3:
                        mActivity.replace("MentalAddFragment", MentalAddFragment.newInstance(mentalBaseDTO, 0), true);
                        break;
                    case 4:
                        /**添加手册*/
                        String ehrId = pregnantBaseDTO.getEhr_id();
                        mActivity.replace("PregnantAddManualFragment", PregnantAddManualFragment.newInstance(ehrId), true);
                        break;
                    case 5:
                        new MaterialDialog.Builder(mActivity)
                                .items(R.array.array_child_visit)
                                .itemsCallback(new MaterialDialog.ListCallback() {
                                    @Override
                                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                        HashMap<String, String> map = new HashMap<>();
                                        map.put("ehr_id", childBaseInfo.getEhr_id());
                                        map.put("name", childBaseInfo.getName());
                                        map.put("birth_date", childBaseInfo.getBirth_date());
                                        map.put("operation", "0");
                                        switch (position) {
                                            case 0:
                                                map.put("sex", childBaseInfo.getSex());
                                                mActivity.replace("childNewBronFragment", ChildAddNewBronFragment.newInstance(map), true);
                                                break;
                                            case 1:
                                                mActivity.replace("childAdd1Fragment", ChildAdd1Fragment.newInstance(map), true);
                                                break;
                                            case 2:
                                                mActivity.replace("childAdd12Fragment", ChildAdd12Fragment.newInstance(map), true);
                                                break;
                                            case 3:
                                                mActivity.replace("childAdd36Fragment", ChildAdd36Fragment.newInstance(map), true);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                })
                                .show();
                        break;

                    default:
                        break;
                }
                break;
            case R.id.ll_back:
                mActivity.popBackStack(1);
                break;
            default:
                break;
        }
    }


    public void replace(String tag, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment, tag).commitAllowingStateLoss();
    }

    public void add(String tag, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.framelayout, fragment, tag).commitAllowingStateLoss();
    }

    public void add(String tag, Fragment fragment, Fragment hideFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (hideFragment != null) {
            fragmentTransaction.hide(hideFragment);
        }
        fragmentTransaction.add(R.id.framelayout, fragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }


//    /**
//     * 糖尿病
//     *
//     * @param searchType
//     * @param content
//     */
//    private void getDiabetes(int searchType, String content) {
//        GucNetEngineClient.getDiabetes(searchType, content, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONObject jsonObject = JSON.parseObject(response);
//                JSONObject getDiabetesInfoResult = jsonObject.getJSONObject("getDiabetesInfoResult");
//                String errInfo = getDiabetesInfoResult.getString("errInfo");
//                if (errInfo != null) {
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
//            }
//        }, new DefaultErrorListener());
//    }
//
//    /**
//     * 精神病
//     *
//     * @param searchType
//     * @param content
//     */
//    private void getMentalBaseInfo(int searchType, String content) {
//        GucNetEngineClient.getMentalBase(searchType, content, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                JSONObject jsonObject = JSON.parseObject(response);
//                JSONObject obj_res = jsonObject.getJSONObject("getMentalInfoResult");
//                String errInfo = obj_res.getString("errInfo");
//                if (errInfo == null) {
//                    JSONArray jsonArray = obj_res.getJSONArray("personList");
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
//                } else {
//                    ToastUtils.showLong(mActivity, errInfo);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                ToastUtils.showLong(mActivity, error.toString());
//            }
//        });
//    }
//
//    /**
//     * 孕妇
//     *
//     * @param searchType
//     * @param content
//     */
//    private void getPregnantInfo(int searchType, String content) {
//        GucNetEngineClient.getPregnantInfo(searchType, content, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        JSONObject jsonObject = JSON.parseObject(response);
//                        JSONObject obj_res = jsonObject.getJSONObject("getMaternalInfoResult");
//                        String errInfo = obj_res.getString("errInfo");
//                        if (StringUtils.isBlank(errInfo)) {
//                            JSONArray jsonArray = obj_res.getJSONArray("personList");
//                            if (jsonArray != null && jsonArray.size() >= 0) {
//                                JSONObject object = jsonArray.getJSONObject(0);
//                                JSONObject obj_info = object.getJSONObject("maternalBase");
//                                pregnantBaseDTO = JSON.parseObject(obj_info.toJSONString(), PregnantBaseDTO.class);
//
//                                pregnantBaseInfoFragment = PregnantBaseInfoFragment.newInstance(pregnantBaseDTO);
//                                replace("pregnantBaseInfoFragment", pregnantBaseInfoFragment, false);
//
//                                JSONArray history_list = object.getJSONArray("registerList");
//                                List<PregnantBaseDTO<JSONObject>> temp = JSON.parseObject(history_list.toJSONString(), new TypeReference<List<PregnantBaseDTO<JSONObject>>>() {
//                                });
//                                pregnant_data.addAll(temp);
//                                return;
//                            }
//                        }
//                        ToastUtils.showLong(mActivity, "未找到相关人员");
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        ToastUtils.showLong(mActivity, error.toString());
//                    }
//                }
//        );
//    }
//
//    private void getChildInfo(int searchType, String content) {
//        showDialog(R.string.is_loading_please_waite);
//        GucNetEngineClient.getChildInfo(searchType, content, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                JSONObject jsonObject = JSON.parseObject(response);
//                JSONObject objResult = jsonObject.getJSONObject("getNewBronInfoResult");
//                String errInfo = objResult.getString("errInfo");
//                if (StringUtils.isBlank(errInfo)) {
//                    JSONArray jsonArray = objResult.getJSONArray("personList");
//                    if (jsonArray != null && jsonArray.size() > 0) {
//
//                    } else {
//                        showToast(R.string.not_find);
//                    }
//
//                } else {
//                    showToast(errInfo);
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("TAG", error.toString());
//                dismiss();
//            }
//        });
//
//    }
    //you belong with me.
    /**
     * 高血压
     *
     * @param type
     * @param data
     * @return
     */
    public static VisitFragment newInstance(int type, QueryResult<?> data) {
        Bundle args = new Bundle();
        args.putSerializable("data", data);
        args.putInt("type", type);
        VisitFragment fragment = new VisitFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
