package com.salient.nexttablayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentNextactions extends Fragment {
    View v;
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    TextView next_task_tv;
    ArrayList next_id, next_task;

    public FragmentNextactions(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
            getFragmentManager()
                    .beginTransaction()
                    .detach(FragmentNextactions.this)
                    .attach(FragmentNextactions.this)
                    .commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.nextactions_fragment, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.next_actions_recyclerview);
        next_task_tv = v.findViewById(R.id.task_name_next);

        next_task = new ArrayList();
        next_id = new ArrayList();

        storeDataInArrays();

        RecyclerViewAdapterNext recyclerAdapter = new RecyclerViewAdapterNext(getActivity(), getContext(), next_id, next_task);
        //recyclerAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myDB = new MyDatabaseHelper(getActivity());
    }

    /*@Override
    public void onResume() {
        super.onResume();
    }*/

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllNextData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                next_id.add(cursor.getString(0));
                next_task.add(cursor.getString(1));
            }
        }
    }
}
