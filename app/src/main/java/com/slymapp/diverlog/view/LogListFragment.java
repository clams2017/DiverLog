package com.slymapp.diverlog.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.slymapp.diverlog.R;
import com.slymapp.diverlog.domain.DiverLog;
import com.slymapp.diverlog.infrastructure.realm.DiverLogRepositoryImpl;

import java.util.ArrayList;
import java.util.List;


/**
 * ダイバーログの一覧表示Fragment
 */
public class LogListFragment extends Fragment {

    private LogListAdapter adapter;
    private List<DiverLog> list = new ArrayList<>();

    public static LogListFragment newInstance() {
        Bundle args = new Bundle();
        LogListFragment fragment = new LogListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // xmlを元にViewを生成
        View view = inflater.inflate(R.layout.fragment_log_list, container, false);

        // リスト部分のビュー生成
        RecyclerView listView = view.findViewById(R.id.log_list_view);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new DiverLogRepositoryImpl().fetchAll();
        adapter = new LogListAdapter(getContext(), list);
        listView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        listView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list = new DiverLogRepositoryImpl().fetchAll();
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }
}
