package com.salient.nexttablayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentIn extends Fragment {
    View v;
    private MyDatabaseHelper myDB;
    private RecyclerView myrecyclerview;
    private RecyclerViewAdapter recyclerAdapter;
    FloatingActionButton add_button;
    TextView task_name_tv;
    private ArrayList task_id, task_title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public FragmentIn(){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
            getFragmentManager()
                    .beginTransaction()
                    .detach(FragmentIn.this)
                    .attach(FragmentIn.this)
                    .commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.in_fragment, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.in_recyclerview);

        add_button = v.findViewById(R.id.add_button);
        add_button.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddInActivity.class);
            startActivity(intent);
        });
        task_id = new ArrayList();
        task_title = new ArrayList();

        storeDataInArrays();

        recyclerAdapter = new RecyclerViewAdapter(getActivity(), getContext(),task_id, task_title);
        task_name_tv = (TextView) v.findViewById(R.id.task_name);
        recyclerAdapter.notifyDataSetChanged();
        myrecyclerview.setAdapter(recyclerAdapter);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }


    void storeDataInArrays(){
        Cursor cursor = myDB.readInListData();
        if(cursor.getCount() == 0){
            //Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task_title.add(cursor.getString(1));
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myDB = new MyDatabaseHelper(getActivity());
    }
}
