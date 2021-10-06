package com.salient.nexttablayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentEvents extends Fragment {
    View v;
    private RecyclerView recyclerview;
    private RecyclerViewAdapterEvents myAdapter;
    private ArrayList event_id, event_name, event_date;
    FloatingActionButton add_event_btn;
    private MyDatabaseHelper myDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public FragmentEvents(){
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myDB = new MyDatabaseHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.events_fragment, container, false);
        recyclerview = (RecyclerView) v.findViewById(R.id.events_recyclerview);
        add_event_btn = v.findViewById(R.id.add_event_button);

        add_event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddEventActivity.class);
                startActivity(intent);
            }
        });

        event_id = new ArrayList();
        event_name = new ArrayList();
        event_date = new ArrayList();

        storeEventsInArrays();

        myAdapter = new RecyclerViewAdapterEvents(getActivity(), getContext(), event_id, event_name, event_date);
        myAdapter.notifyDataSetChanged();
        recyclerview.setAdapter(myAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    void storeEventsInArrays(){
        Cursor cursor = myDB.readAllEventData();
        if(cursor.getCount() == 0){
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                event_id.add(cursor.getString(0));
                event_name.add(cursor.getString(1));
                event_date.add(cursor.getString(2));
            }
        }
    }
}
