package com.slymapp.diverlog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * ダイバーログの一覧表示Fragment
 */
public class LogListFragment extends Fragment {

    private LogListAdapter adapter;

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
        adapter = new LogListAdapter(getFragmentManager());
        listView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        listView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
