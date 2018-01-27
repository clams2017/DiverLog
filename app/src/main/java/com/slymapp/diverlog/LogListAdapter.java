package com.slymapp.diverlog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slymapp.diverlog.domain.DiverLog;
import com.slymapp.diverlog.infrastructure.realm.DiverLogRepositoryImpl;
import com.slymapp.diverlog.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ログリストのアダプタークラス
 */
public class LogListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DiverLog> list = new ArrayList<>();
    private FragmentManager fragmentManager;

    LogListAdapter(FragmentManager fragmentManager) {
        list = new DiverLogRepositoryImpl().fetchAll(); // TODO 非同期で取得するよう変更する
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_log_list_item, parent, false);
        final ItemViewHolder viewHolder = new ItemViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int divingNo = Integer.parseInt((String) viewHolder.divingNumber.getText());
                Fragment fragment = new LogDetailFragment();
                Bundle args = new Bundle();
                args.putInt("divingNumber", divingNo);
                fragment.setArguments(args);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, fragment)
                        .addToBackStack(null) // 戻るボタンでreplace前に戻る
                        .commit();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView divingNumber;
        private TextView place;
        private TextView date;

        ItemViewHolder(View itemView) {
            super(itemView);
            divingNumber = itemView.findViewById(R.id.diving_number);
            place = itemView.findViewById(R.id.place);
            date = itemView.findViewById(R.id.date);
        }

        void bind(DiverLog diverLog) {
            divingNumber.setText(String.valueOf(diverLog.getDivingNumber()));
            place.setText(diverLog.getPlace());
//            date.setText(diverLog.getDate().toString());
            date.setText(DateUtils.toDateString(diverLog.getDate()));
            // ここでサムネ画像を表示する
        }

    }
}
