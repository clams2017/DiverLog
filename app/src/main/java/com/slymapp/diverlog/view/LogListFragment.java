package com.slymapp.diverlog.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.slymapp.diverlog.R;
import com.slymapp.diverlog.domain.DiverLogJsonBackupManager;

import java.io.IOException;

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
        adapter = new LogListAdapter(getContext());
        listView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(listView.getContext(),
                new LinearLayoutManager(getActivity()).getOrientation());
        listView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.updateLogs();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_log_list, menu);
        menu.findItem(R.id.action_import).setVisible(true);
        menu.findItem(R.id.action_export).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_import:
                importLogs();
                return true;
            case R.id.action_export:
                exportLogs();
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void importLogs() {
        try {
            new DiverLogJsonBackupManager().importLogs(getContext());
            Toast.makeText(getContext(), "ログを読み込みました。", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "ログの読み込みに失敗した。", Toast.LENGTH_SHORT).show();
        }
        if (adapter != null) {
            adapter.updateLogs();
            adapter.notifyDataSetChanged();
        }
    }

    private void exportLogs() {
        try {
            new DiverLogJsonBackupManager().exportAllLog(getContext());
            Toast.makeText(getContext(), "ログを保存しました。", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "ログの保存に失敗しました。。", Toast.LENGTH_SHORT).show();
        }
    }

}
