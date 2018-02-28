package com.slymapp.diverlog.view;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.slymapp.diverlog.R;
import com.slymapp.diverlog.databinding.FragmentLogAddBinding;
import com.slymapp.diverlog.domain.DiverLog;
import com.slymapp.diverlog.infrastructure.realm.DiverLogRepositoryImpl;
import com.slymapp.diverlog.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * ログ新規作成画面
 */
public class LogAddFragment extends Fragment {

    private static final String KEY_DIVER_LOG = LogAddActivity.KEY_DIVER_LOG;

    private DiverLog diverLog;


    /**
     * ログ新規作成Fragmentを作成する。
     *
     * @return {@link LogAddFragment}
     */
    public static LogAddFragment newInstance() {
        LogAddFragment fragment = new LogAddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_log_add, container, false);
        Intent intent = getActivity().getIntent();

        // 破棄された時用の復元処理
        if (savedInstanceState != null) {
            diverLog = (DiverLog) savedInstanceState.getSerializable(KEY_DIVER_LOG);
        }
        // updateするログを受け取る
        else if (intent != null){
            diverLog = (DiverLog) intent.getSerializableExtra(KEY_DIVER_LOG);
        }
        // insert時はデフォルト値を入力済みにする
        if (diverLog == null) {
            // TODO Builderクラスに移行する
            diverLog = new DiverLog();
            diverLog.setDivingNumber(new DiverLogRepositoryImpl().publishDivingNumber());
            diverLog.setDate(new Date());
            diverLog.setStartTime(new Date());
            diverLog.setEndTime(new Date());
        }

        // DataBindingはViewHolderとしてのみ利用する
        final FragmentLogAddBinding binding = DataBindingUtil.bind(mainView);
        bindDefaultValues(diverLog, binding);

        binding.logAddDateValue.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Date date = DateUtils.createFromDate(i, i1 + 1, i2);
                        diverLog.setDate(date);
                        binding.logAddDateValue.setText(DateUtils.toDateString(diverLog.getDate()));
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });
        binding.logAddInTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date = DateUtils.createFromTime(i, i1, 0);
                        diverLog.setStartTime(date);
                        binding.logAddInTimeValue.setText(DateUtils.toTimeString(diverLog.getStartTime()));
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true)
                        .show();
            }
        });
        binding.logAddOutTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date = DateUtils.createFromTime(i, i1, 0);
                        diverLog.setStartTime(date);
                        binding.logAddOutTimeValue.setText(DateUtils.toTimeString(diverLog.getStartTime()));
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true)
                        .show();
            }
        });
        // TODO DiverLogRepositoryImpl()でモックデータを上書き保存しているため、No.1-5はログデータ変更不可
        binding.logAddSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ログの登録完了!", Toast.LENGTH_SHORT).show();
                new DiverLogRepositoryImpl().upsert(bindInputValues(binding));
                Context context = getContext();
                context.startActivity(MainActivity.createIntent(context));
            }
        });
        // EditTextからフォーカスを外したときにキーボードを隠す
        binding.logAddMainLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert inputMethodManager != null;
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    view.requestFocus();
                }
            }
        });
        return mainView;
    }

    private void bindDefaultValues(DiverLog diverLog, FragmentLogAddBinding binding){
        binding.logAddDivingNumberValue.setText(String.valueOf(diverLog.getDivingNumber()));
        binding.logAddDateValue.setText(DateUtils.toDateString(diverLog.getDate()));
        binding.logAddWeatherValue.setText(diverLog.getWeather());
        binding.logAddPlaceValue.setText(diverLog.getPlace());
        binding.logAddStartPressureValue.setText(String.valueOf(diverLog.getStartPressure()));
        binding.logAddEndPressureValue.setText(String.valueOf(diverLog.getEndPressure()));
        binding.logAddInTimeValue.setText(DateUtils.toTimeString(diverLog.getStartTime()));
        binding.logAddOutTimeValue.setText(DateUtils.toTimeString(diverLog.getEndTime()));
        binding.logAddEntryWayValue.setText(diverLog.getEntryMethod());
        binding.logAddSuitsValue.setText(diverLog.getSuits());
        binding.logAddTransparencyValue.setText(String.valueOf(diverLog.getTransparent()));
        binding.logAddWeightValue.setText(String.valueOf(diverLog.getWeight()));
        binding.logAddMaxDepthValue.setText(String.valueOf(diverLog.getMaxDepth()));
        binding.logAddAverageDepthValue.setText(String.valueOf(diverLog.getAverageDepth()));
        binding.logAddTemperatureValue.setText(String.valueOf(diverLog.getTemperature()));
        // TODO 合計ダイブタイムをbindingする
    }

    private DiverLog bindInputValues(FragmentLogAddBinding binding) {
        diverLog = new DiverLog();

        diverLog.setDivingNumber(view2Integer(binding.logAddDivingNumberValue));
        diverLog.setDate(view2Date(binding.logAddDateValue));
        diverLog.setWeather(view2String(binding.logAddWeatherValue));
        diverLog.setPlace(view2String(binding.logAddPlaceValue));
        diverLog.setStartPressure(view2Integer(binding.logAddStartPressureValue));
        diverLog.setEndPressure(view2Integer(binding.logAddEndPressureValue));
        diverLog.setStartTime(view2Time(binding.logAddInTimeValue));
        diverLog.setEndTime(view2Time(binding.logAddOutTimeValue));
        diverLog.setTransparent(view2Integer(binding.logAddTransparencyValue));
        diverLog.setWeight(view2Integer(binding.logAddWeightValue));
        diverLog.setMaxDepth(view2Float(binding.logAddMaxDepthValue));
        diverLog.setAverageDepth(view2Float(binding.logAddAverageDepthValue));
        diverLog.setTemperature(view2Float(binding.logAddTemperatureValue));
        // TODO 合計ダイブタイムをsetする

        return diverLog;
    }

    private String view2String(TextView view){ return view.getText().toString(); }

    private Integer view2Integer(TextView view) { return Integer.parseInt(view2String(view)); }

    private Float view2Float(TextView view) { return Float.parseFloat(view2String(view)); }

    private Date view2Date(TextView view) { return DateUtils.createFromDate(view2String(view)); }

    private Date view2Time(TextView view) { return DateUtils.createFromTime(view2String(view)); }

}
