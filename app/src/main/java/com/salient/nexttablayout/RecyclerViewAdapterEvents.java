package com.salient.nexttablayout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterEvents extends RecyclerView.Adapter<RecyclerViewAdapterEvents.MyViewHolder> {
    Context mContext;
    Activity activity;
    ArrayList event_id, event_name, event_date;
    int position;

    public RecyclerViewAdapterEvents(Activity activity, Context mContext, ArrayList event_id, ArrayList event_name, ArrayList event_date) {
        this.activity = activity;
        this.mContext = mContext;
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_date = event_date;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterEvents.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.item_events, parent, false);
        final RecyclerViewAdapterEvents.MyViewHolder vHolder = new RecyclerViewAdapterEvents.MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterEvents.MyViewHolder holder, int position) {
        this.position = position;
        holder.tv_event.setText(String.valueOf(event_name.get(position)));
        holder.tv_date.setText(String.valueOf(event_date.get(position)));
    }

    @Override
    public int getItemCount() {
        if(event_id == null){
            return 0;
        }
        else {
            return event_id.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_events;
        private TextView tv_event;
        private TextView tv_date;

        public MyViewHolder(View itemView){
            super(itemView);
            item_events = (LinearLayout) itemView.findViewById(R.id.event_item_id);
            tv_event = (TextView) itemView.findViewById(R.id.event_name);
            tv_date = (TextView) itemView.findViewById(R.id.event_date);
        }
    }
}
