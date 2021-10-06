package com.salient.nexttablayout;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectTaskAdapter extends RecyclerView.Adapter<ProjectTaskAdapter.ViewHolder> {
    ArrayList sub_task_name;
    int arrsize;
    Context context;
    Activity activity;

    public ProjectTaskAdapter(Context context, Activity activity, int arrsize,
                              ArrayList sub_task_name) {
        this.context = context;
        this.activity = activity;
        this.arrsize = arrsize;
        this.sub_task_name = sub_task_name;
    }

    @NonNull
    @Override
    public ProjectTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ProjectTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectTaskAdapter.ViewHolder holder, int position) {
        holder.task_name_project_tv.setText(String.valueOf(sub_task_name.get(position)));
    }

    @Override
    public int getItemCount() {
            return this.arrsize;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView task_name_project_tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task_name_project_tv = itemView.findViewById(R.id.task_name_project);
        }
    }
}
