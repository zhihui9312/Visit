package com.guc.visit.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.datepicker.picker.DatePicker;
import com.guc.visit.R;
import com.guc.visit.listener.ChangeTitle;
import com.guc.visit.utils.ToastUtils;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseFragment extends Fragment implements OnClickListener, ChangeTitle {
    protected BaseActivity mActivity;
    protected LinearLayout right_layout;
    protected TextView tv_title;
    protected LinearLayout ll_back;
    protected TextView tv_back;
    protected MaterialDialog materialDialog;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }

    protected View initView(LayoutInflater inflater, ViewGroup container, int layout) {
        View view = inflater.inflate(layout, container, false);
        initBar(view);
        initWidget(view);
        setListeners();
        initData();
        return view;
    }

    private void initBar(View view) {
        ll_back = (LinearLayout) view.findViewById(R.id.ll_back);
        right_layout = (LinearLayout) view.findViewById(R.id.right_layout);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_back = (TextView) view.findViewById(R.id.tv_back);
        if (tv_title != null) {
            tv_title.requestFocus();
        }
    }

    protected abstract void initData();

    protected abstract void setListeners();

    protected abstract void initWidget(View view);
//    protected abstract void updateUI();

    @Override
    public void controlBar(int resId, int backId, boolean isLeft, boolean isRight) {
        tv_title.setText(getResources().getString(resId));
        tv_back.setText(getResources().getString(backId));
        ll_back.setVisibility(isLeft ? View.VISIBLE : View.GONE);
        right_layout.setVisibility(isRight ? View.VISIBLE : View.GONE);
    }

    @Override
    public void controlBar(int resId, String backId, boolean isLeft, boolean isRight) {
        tv_title.setText(getResources().getString(resId));
        tv_back.setText(backId);
        ll_back.setVisibility(isLeft ? View.VISIBLE : View.GONE);
        right_layout.setVisibility(isRight ? View.VISIBLE : View.GONE);
    }

    @Override
    public void controlBar(String resId, int backId, boolean isLeft, boolean isRight) {
        tv_title.setText(resId);
        tv_back.setText(getResources().getString(backId));
        ll_back.setVisibility(isLeft ? View.VISIBLE : View.GONE);
        right_layout.setVisibility(isRight ? View.VISIBLE : View.GONE);
    }

    @Override
    public void controlBar(String resId, String backId, boolean isLeft, boolean isRight) {
        tv_title.setText(resId);
        tv_back.setText(backId);
        ll_back.setVisibility(isLeft ? View.VISIBLE : View.GONE);
        right_layout.setVisibility(isRight ? View.VISIBLE : View.GONE);
    }

    public void showDatePicker(final TextView textView) {
        final DatePicker picker = new DatePicker(getActivity());
        picker.setTopPadding(10);
        picker.setTextSize(18);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        picker.setRangeStart(year - 200, 01, 01);
        picker.setRangeEnd(year + 200, 01, 01);
        String currentText = textView.getText().toString().trim();
        if (StringUtils.isNoneBlank(currentText)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = simpleDateFormat.parse(currentText);
                calendar.setTime(date);
                picker.setSelectedItem(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                textView.setText(year + "-" + month + "-" + day);
            }
        });
        picker.show();
    }

    public void showToast(String message) {
        ToastUtils.show(getActivity(), message, Toast.LENGTH_LONG);
    }

    public void showToast(int resId) {
        String message = mActivity.getResources().getString(resId);
        ToastUtils.show(getActivity(), message, Toast.LENGTH_LONG);
    }

    public void hideInput() {
        View mv = getActivity().getWindow().peekDecorView();
        if (mv != null) {
            InputMethodManager inputManger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(mv.getWindowToken(), 0);
        }
    }

    public String getSpinnerValue(Spinner spinner) {
        int position = spinner.getSelectedItemPosition();
        return position == 0 ? null : (position + 1) + "";
    }

    public String getSpinnerValue0(Spinner spinner) {
        int position = spinner.getSelectedItemPosition();
        return position + "";
    }

    public String getSpinnerValue1(Spinner spinner) {
        int position = spinner.getSelectedItemPosition();
        return (position + 1) + "";
    }

    public String getSpinnerValue01(Spinner spinner) {
        int position = spinner.getSelectedItemPosition();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumIntegerDigits(2);
        nf.setMinimumIntegerDigits(2);
        return nf.format(position) + "";
    }

    public String getToTrim(TextView textView) {
        String str = textView.getText().toString().trim();
        if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            return str;
        }
    }

    public int convertToInteger(String string) {
        int value = 0;
        if (StringUtils.isNoneBlank(string)) {
            try {
                value = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

//    public void multiChoiceDialog2(int arrayId, final TextView textView, final StringBuilder code) {
//        final String[] stringArray = mActivity.getResources().getStringArray(arrayId);
//        final int lengthArray = stringArray.length;
//
//        MaterialDialog.Builder builder = new MaterialDialog.Builder(mActivity);
//        builder.items(arrayId);
//        builder.itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
//            @Override
//            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
//                int length = which.length;
//                if (length > 1 && which[length - 1] == lengthArray - 1) {
//                    Integer[] array = new Integer[1];
//                    array[0] = lengthArray - 1;
//                    dialog.setSelectedIndices(array);
//                } else if (length > 1 && which[0] == lengthArray - 1) {
//                    Integer[] array = new Integer[1];
//                    array[0] = which[1];
//                    dialog.setSelectedIndices(array);
//                }
//                return true;
//            }
//        });
//        builder.onNeutral(new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                dialog.clearSelectedIndices();
//            }
//        });
//        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                Integer selectedArray[] = dialog.getSelectedIndices();
//
//                if (selectedArray != null && selectedArray.length > 0) {
//                    int length = selectedArray.length;
//                    StringBuilder tempStr = new StringBuilder("");
//                    StringBuilder strCode = new StringBuilder("");
//                    for (int i = 0; i < length; i++) {
//                        tempStr.append(stringArray[selectedArray[i]] + ",");
//                        strCode.append((selectedArray[i] + 1) + ",");
//                    }
//                    tempStr.deleteCharAt(tempStr.length() - 1);
//                    strCode.deleteCharAt(strCode.length() - 1);
//                    textView.setText(tempStr.toString());
//                    code.append(strCode.toString());
//                }
//
//                dialog.dismiss();
//            }
//        });
//        builder.alwaysCallMultiChoiceCallback();
//        builder.positiveText("确认");
//        builder.autoDismiss(false);
//        builder.neutralText("清除");
//        builder.show();
//
//    }

//    public void multiChoiceDialog3(int arrayId, final TextView textView, final StringBuilder code) {
//        final String[] stringArray = mActivity.getResources().getStringArray(arrayId);
//        final int lengthArray = stringArray.length;
//
//        MaterialDialog.Builder builder = new MaterialDialog.Builder(mActivity);
//        builder.items(arrayId);
//        builder.itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
//            @Override
//            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
//                int length = which.length;
//                if (length > 1 && which[length - 1] == lengthArray - 1) {
//                    Integer[] array = new Integer[1];
//                    array[0] = lengthArray - 1;
//                    dialog.setSelectedIndices(array);
//                } else if (length > 1 && which[0] == lengthArray - 1) {
//                    Integer[] array = new Integer[1];
//                    array[0] = which[1];
//                    dialog.setSelectedIndices(array);
//                }
//                return true;
//            }
//        });
//        builder.onNeutral(new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                dialog.clearSelectedIndices();
//            }
//        });
//        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                Integer selectedArray[] = dialog.getSelectedIndices();
//
//                if (selectedArray != null && selectedArray.length > 0) {
//                    int length = selectedArray.length;
//                    StringBuilder tempStr = new StringBuilder("");
//                    StringBuilder strCode = new StringBuilder("");
//                    for (int i = 0; i < length; i++) {
//                        tempStr.append(stringArray[selectedArray[i]] + ",");
//                        strCode.append((selectedArray[i] + 1) + ",");
//                    }
//                    tempStr.deleteCharAt(tempStr.length() - 1);
//                    strCode.deleteCharAt(strCode.length() - 1);
//                    textView.setText(tempStr.toString());
//                    code.append(strCode.toString());
//                }
//
//                dialog.dismiss();
//            }
//        });
//        builder.alwaysCallMultiChoiceCallback();
//        builder.positiveText("确认");
//        builder.autoDismiss(false);
//        builder.neutralText("清除");
//        builder.show();
//
//    }

    public String getFormatDateStr(String dateStr) {
        return dateStr.split("T")[0];
    }

    public Integer[] getIntArray(StringBuilder stringBuilder) {
        Integer[] temp = null;
        if (stringBuilder.length() > 0) {
            String[] integers = stringBuilder.toString().split(",");
            int length = integers.length;
            temp = new Integer[length];
            for (int i = 0; i < length; i++) {
                temp[i] = Integer.parseInt(integers[i]) - 1;
            }
        }
        return temp;
    }

//    public void multiChoiceDialog(int arrayId, final TextView textView, final StringBuilder code) {
//        final String[] stringArray = mActivity.getResources().getStringArray(arrayId);
//
//        MaterialDialog.Builder builder = new MaterialDialog.Builder(mActivity);
//        builder.items(arrayId);
//        builder.itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
//            @Override
//            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
//                return true;
//            }
//        });
//        builder.onNeutral(new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                dialog.clearSelectedIndices();
//            }
//        });
//        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                Integer selectedArray[] = dialog.getSelectedIndices();
//                if (selectedArray != null && selectedArray.length > 0) {
//                    int length = selectedArray.length;
//                    StringBuilder tempStr = new StringBuilder("");
//                    StringBuilder strCode = new StringBuilder("");
//
//                    for (int i = 0; i < length; i++) {
//                        tempStr.append(stringArray[selectedArray[i]] + ",");
//                        strCode.append((selectedArray[i] + 1) + ",");
//                    }
//                    tempStr.deleteCharAt(tempStr.length() - 1);
//                    strCode.deleteCharAt(strCode.length() - 1);
//                    textView.setText(tempStr.toString());
//                    code.append(strCode.toString());
//                }
//                dialog.dismiss();
//            }
//        });
//        builder.alwaysCallMultiChoiceCallback();
//        builder.positiveText("确认");
//        builder.autoDismiss(false);
//        builder.neutralText("清除");
//        builder.show();
//    }


    public void multiChoiceDialog(@Nullable Integer[] selectedIndices, int arrayId, final TextView textView, final StringBuilder code) {
        final String[] stringArray = mActivity.getResources().getStringArray(arrayId);
        code.delete(0, code.length());
        MaterialDialog.Builder builder = new MaterialDialog.Builder(mActivity);
        builder.items(arrayId);
        builder.itemsCallbackMultiChoice(selectedIndices, new MaterialDialog.ListCallbackMultiChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                return true;
            }
        });
        builder.onNeutral(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.clearSelectedIndices();
            }
        });
        builder.onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                Integer selectedArray[] = dialog.getSelectedIndices();
                if (selectedArray != null && selectedArray.length > 0) {
                    int length = selectedArray.length;
                    StringBuilder tempStr = new StringBuilder("");
                    StringBuilder strCode = new StringBuilder("");
                    for (int i = 0; i < length; i++) {
                        tempStr.append(stringArray[selectedArray[i]] + ",");
                        strCode.append((selectedArray[i] + 1) + ",");
                    }
                    tempStr.deleteCharAt(tempStr.length() - 1);
                    strCode.deleteCharAt(strCode.length() - 1);
                    textView.setText(tempStr.toString());
                    code.append(strCode.toString());
                }else{
                    textView.setText("");
                }
                dialog.dismiss();
            }
        });
        builder.alwaysCallMultiChoiceCallback();
        builder.positiveText("确认");
        builder.autoDismiss(false);
        builder.neutralText("清除");
        builder.show();
    }

    public MaterialDialog showIndeterminateProgressDialog(@StringRes int contentRes) {
        MaterialDialog materialDialog = new MaterialDialog.Builder(mActivity)
                .content(contentRes)
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .show();
        return materialDialog;
    }

}