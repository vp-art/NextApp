package com.salient.nexttablayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterProj extends RecyclerView.Adapter<RecyclerViewAdapterProj.MyViewHolder> {
    Activity activity;
    Context context;
    ArrayList<Integer> proj_ids_arr;
    ArrayList<String> proj_titles_arr;
    ArrayList sub_task_name, sub_task_id;
    MyDatabaseHelper myDB;
    int position;

    public RecyclerViewAdapterProj(Activity activity, Context context, ArrayList<Integer> proj_ids_arr, ArrayList<String> proj_titles_arr) {
        this.activity = activity;
        this.context = context;
        this.proj_ids_arr = proj_ids_arr;
        this.proj_titles_arr = proj_titles_arr;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterProj.MyViewHolder holder, int position) {
        this.position = position;
        holder.proj_name_tv.setText(String.valueOf(proj_titles_arr.get(position)));
        myDB = new MyDatabaseHelper(activity);

        sub_task_name = new ArrayList();
        sub_task_id = new ArrayList();

        storeProjDataInArrays();

        holder.item_proj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddProjectTaskActivity.class);
                intent.putExtra("id", String.valueOf(proj_ids_arr.get(position)));
                //intent.putExtra("sub_id", String.valueOf(sub_task_id.get(position)));
                activity.startActivity(intent);
            }
        });
        ArrayList t=new ArrayList();
        for (int j = 0; j < sub_task_id.size(); j++){
            if(String.valueOf(sub_task_id.get(j)).equalsIgnoreCase(String.valueOf(proj_ids_arr.get(position)))){
                t.add(sub_task_name.get(j));
            }
        }
        for (int j=0;j<t.size();j++) {
            ProjectTaskAdapter projectTaskAdapter = new ProjectTaskAdapter(context, activity,
                    t.size(), t);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
            holder.sub_proj_rv.setLayoutManager(linearLayoutManager);
            holder.sub_proj_rv.setAdapter(projectTaskAdapter);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapterProj.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_proj, parent, false);
        final RecyclerViewAdapterProj.MyViewHolder vHolder = new RecyclerViewAdapterProj.MyViewHolder(view);
        return vHolder;
    }

    @Override
    public int getItemCount() {
        if(proj_ids_arr == null){
            return 0;
        }
        else{
            return proj_ids_arr.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView item_proj;
        TextView proj_name_tv;
        RecyclerView sub_proj_rv;

        public MyViewHolder(View itemView){
            super(itemView);
            item_proj = (CardView) itemView.findViewById(R.id.proj_item_id);
            proj_name_tv = (TextView) itemView.findViewById(R.id.proj_name);
            sub_proj_rv = (RecyclerView) itemView.findViewById(R.id.sub_proj);
        }
    }

    void storeProjDataInArrays(){
        Cursor cursor = myDB.readAllProjectTaskData();
        if(cursor.getCount() == 0){
            Toast.makeText(context, "No project data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                sub_task_id.add(cursor.getString(0));
                sub_task_name.add(cursor.getString(1));
            }
        }
    }
}
