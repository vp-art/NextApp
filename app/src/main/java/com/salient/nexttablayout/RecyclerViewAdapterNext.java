package com.salient.nexttablayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterNext extends RecyclerView.Adapter<RecyclerViewAdapterNext.MyViewHolder> {
    Context context;
    Activity activity;
    int position;
    ArrayList next_task, next_task_id;

    public RecyclerViewAdapterNext(Activity activity, Context context, ArrayList task_id, ArrayList task_title) {
        this.activity = activity;
        this.context = context;
        this.next_task_id = task_id;
        this.next_task = task_title;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterNext.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_next, parent, false);
        final RecyclerViewAdapterNext.MyViewHolder vHolder = new RecyclerViewAdapterNext.MyViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterNext.MyViewHolder holder, int position) {
        this.position = position;
        holder.tv_next_id.setText(String.valueOf(next_task_id.get(position)));
        holder.tv_next_task.setText(String.valueOf(next_task.get(position)));
        holder.item_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DeleteNextActivity.class);
                // adapter.notifyItemChanged(position);
                intent.putExtra("id", String.valueOf(next_task_id.get(position)));
                intent.putExtra("title", String.valueOf(next_task.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(next_task_id == null){
            return 0;
        }
        else
            return next_task_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_next;
        private TextView tv_next_task, tv_next_id;

        public MyViewHolder(View itemView){
            super(itemView);
            item_next = itemView.findViewById(R.id.next_item_id);
            tv_next_id = itemView.findViewById(R.id.task_id_next);
            tv_next_task = itemView.findViewById(R.id.task_name_next);
        }
    }
}
