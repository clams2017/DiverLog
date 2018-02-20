package com.slymapp.diverlog.view;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
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
        View view = inflater.inflate(R.layout.fragment_log_add, container, false);
        Intent intent = getActivity().getIntent();

        // 破棄された時用の復元処理
        if (savedInstanceState != null) {
            diverLog = (DiverLog) savedInstanceState.getSerializable(KEY_DIVER_LOG);
        }
        else if (intent != null){
            diverLog = (DiverLog) intent.getSerializableExtra(KEY_DIVER_LOG);
        }
        if (diverLog == null) {
            diverLog = new DiverLog();
            diverLog.setDivingNumber(new DiverLogRepositoryImpl().publishDivingNumber());
            diverLog.setDate(new Date());
        }

        // DataBindingはViewHolderとしてのみ利用する
        final FragmentLogAddBinding binding = DataBindingUtil.bind(view);
        binding.logAddDivingNumberValue.setText(String.valueOf(diverLog.getDivingNumber()));
        binding.logAddDateValue.setText(DateUtils.toDateString(diverLog.getDate()));
        binding.logAddPlaceValue.setText(diverLog.getPlace());
        binding.logAddDateImageButton.setOnClickListener(new View.OnClickListener() {
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
        binding.inTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date = DateUtils.createFromTime(i, i1, 0);
                        diverLog.setStartTime(date);
                        binding.inTimeValue.setText(DateUtils.toTimeString(diverLog.getStartTime()));
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true)
                        .show();
            }
        });
        binding.outTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        Date date = DateUtils.createFromTime(i, i1, 0);
                        diverLog.setStartTime(date);
                        binding.outTimeValue.setText(DateUtils.toTimeString(diverLog.getStartTime()));
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true)
                        .show();
            }
        });
        binding.logAddAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "ログの登録完了!(メッセージのみ)", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
