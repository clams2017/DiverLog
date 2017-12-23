package com.slymapp.diverlog;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ログリストのアダプタークラス
 */
public class LogListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> list = new ArrayList<>();

    LogListAdapter(){
        for(int i = 0;  i< 30; i++){
            list.add("テキスト" + i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_log_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        ItemViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.view_log_list_item_text);
        }
    }
}
