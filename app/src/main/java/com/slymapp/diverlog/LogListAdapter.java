package com.slymapp.diverlog;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.slymapp.diverlog.domain.DiverLog;
import com.slymapp.diverlog.infrastructure.realm.DiverLogRepositoryImpl;
import com.slymapp.diverlog.utils.DateUtils;
import com.squareup.picasso.Picasso;

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
        return new ItemViewHolder(view);
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
        private ImageView image;
        private Context context;
        private View itemView;

        ItemViewHolder(View itemView) {
            super(itemView);
            divingNumber = itemView.findViewById(R.id.diving_number);
            place = itemView.findViewById(R.id.place);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.image);
            context = itemView.getContext();
            this.itemView = itemView;
        }

        void bind(final DiverLog diverLog) {
            divingNumber.setText("ID: " + String.valueOf(diverLog.getDivingNumber()));
            place.setText(diverLog.getPlace());
            date.setText(DateUtils.toDateString(diverLog.getDate()));
            // とりあえず適当な画像を表示する
            Picasso.with(context).load("file://"+Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DCIM+"/m_9.jpg").fit().into(image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new LogDetailFragment();
                    Bundle args = new Bundle();
                    args.putInt("divingNumber", diverLog.getDivingNumber());
                    fragment.setArguments(args);
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment, fragment)
                            .addToBackStack(null) // 戻るボタンでreplace前に戻る
                            .commit();
                }
            });
        }
    }
}
