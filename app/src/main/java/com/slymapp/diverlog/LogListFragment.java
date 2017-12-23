package com.slymapp.diverlog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogListFragment extends Fragment {

    private RecyclerView listView;

    private LogListAdapter adapter;

    public LogListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_list, container, false);
        adapter = new LogListAdapter();
        listView = view.findViewById(R.id.log_list_view);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
