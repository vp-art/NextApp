package com.salient.nexttablayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context mContext;
    Activity mActivity;
    ArrayList task_id, task_title;
    //TextView tv_task;
    int position;

    public RecyclerViewAdapter() {
    }

    public RecyclerViewAdapter(Activity mActivity, Context mContext, ArrayList task_id, ArrayList task_title) {
        this.mActivity = mActivity;
        this.mContext = mContext;
        this.task_id = task_id;
        this.task_title = task_title;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.item_in, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;
        holder.tv_task.setText(String.valueOf(task_title.get(position)));
        holder.item_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateActivity.class);
               // adapter.notifyItemChanged(position);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("title", String.valueOf(task_title.get(position)));
                mActivity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(task_id == null)
            return 0;
        else
            return task_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_in;
        private TextView tv_task, tv_type;

        public MyViewHolder(View itemView){
            super(itemView);
            item_in = (LinearLayout) itemView.findViewById(R.id.in_item_id);
            tv_task = (TextView) itemView.findViewById(R.id.task_name);
        }
    }
}