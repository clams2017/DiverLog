package com.slymapp.diverlog;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.slymapp.diverlog.databinding.FragmentLogAddBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * ログ新規作成画面
 */
public class LogAddFragment extends Fragment {

    public LogAddFragment() {
        // Required empty public constructor
    }

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
        final FragmentLogAddBinding binding = DataBindingUtil.bind(view);
        binding.logAddDateValue.setText(toDateString(new Date()));
        binding.logAddDateValue.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        binding.logAddDateValue.setText(toDateString(i, i1, i2));
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
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

    // TODO 日付処理をUtil化する
    @TargetApi(Build.VERSION_CODES.N)
    private String toDateString(Date date) {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        return dateFormat.format(date);
    }

    private String toDateString(int year, int month, int day) {
        // TODO 日付の暫定対応を修正する
        return toDateString(new GregorianCalendar(year, month, day + 1).getTime());
    }
}
