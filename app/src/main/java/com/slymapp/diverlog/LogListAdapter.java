package com.slymapp.diverlog;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
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

    private class Book{
        String id;
        String place;
        String date;

        Book(String id, String place, String date){
            this.id = id;
            this.place = place;
            this.date = date;
        }
    }
    private List<Book> list = new ArrayList<>();
    private FragmentManager fragmentManager;

    LogListAdapter(FragmentManager fragmentManager){
        for(int i = 0;  i< 30; i++){
            list.add(new Book("id" + i, "place" + i, "date" + i));
        }
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_log_list_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = new MainActivityFragment();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack(null); // 戻るボタンでreplace前に戻る
                fragmentTransaction.commit();
            }
        });
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.book_id.setText(list.get(position).id);
        itemViewHolder.place.setText(list.get(position).place);
        itemViewHolder.date.setText(list.get(position).date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView book_id;
        TextView place;
        TextView date;

        ItemViewHolder(View itemView) {
            super(itemView);
            book_id = itemView.findViewById(R.id.book_id);
            place = itemView.findViewById(R.id.place);
            date = itemView.findViewById(R.id.date);
        }
    }
}
