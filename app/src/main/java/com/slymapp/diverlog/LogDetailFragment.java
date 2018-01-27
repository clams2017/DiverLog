package com.slymapp.diverlog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slymapp.diverlog.domain.DiverLog;
import com.slymapp.diverlog.infrastructure.realm.DiverLogRepositoryImpl;


/**
 * ダイバーログの詳細表示Fragment
 */
public class LogDetailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private int divingNo;

    public static LogDetailFragment newInstance() {
        LogDetailFragment fragment = new LogDetailFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            divingNo = getArguments().getInt("divingNumber");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_detail, container, false);

        // ログデータをフェッチ
        DiverLogRepositoryImpl rep = new DiverLogRepositoryImpl();
        DiverLog diverLog = rep.fetch(divingNo);

        TextView divingNumber  = view.findViewById(R.id.diving_number);
        TextView date          = view.findViewById(R.id.date);
        TextView weather       = view.findViewById(R.id.weather);
        TextView place         = view.findViewById(R.id.place);
        TextView entryMethod   = view.findViewById(R.id.entry_method);
        TextView transparent   = view.findViewById(R.id.transparent);
        TextView startTime     = view.findViewById(R.id.start_time);
        TextView endTime       = view.findViewById(R.id.end_time);
        TextView startPressure = view.findViewById(R.id.start_pressure);
        TextView endPressure   = view.findViewById(R.id.end_pressure);
        TextView suits         = view.findViewById(R.id.suits);
        TextView weight        = view.findViewById(R.id.weight);
        TextView averageDepth  = view.findViewById(R.id.average_depth);
        TextView maxDepth      = view.findViewById(R.id.max_depth);
        TextView temperature   = view.findViewById(R.id.temperature);

        divingNumber.setText(String.valueOf(diverLog.getDivingNumber()));
        date.setText(diverLog.getDate().toString());
        weather.setText(diverLog.getWeather());
        place.setText(diverLog.getPlace());
        entryMethod.setText(diverLog.getEntryMethod());
        transparent.setText(String.valueOf(diverLog.getTransparent()));
        startTime.setText(diverLog.getStartTime().toString());
        endTime.setText(diverLog.getEndTime().toString());
        startPressure.setText(String.valueOf(diverLog.getStartPressure()));
        endPressure.setText(String.valueOf(diverLog.getEndPressure()));
        suits.setText(diverLog.getSuits());
        weight.setText(String.valueOf(diverLog.getWeight()));
        averageDepth.setText(String.valueOf(diverLog.getAverageDepth()));
        maxDepth.setText(String.valueOf(diverLog.getMaxDepth()));
        temperature.setText(String.valueOf(diverLog.getTemperature()));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
