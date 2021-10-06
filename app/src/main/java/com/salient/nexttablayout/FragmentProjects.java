package com.salient.nexttablayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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

public class FragmentProjects extends Fragment {
    View v;
    private MyDatabaseHelper myDB;
    private RecyclerViewAdapterProj myAdapter;
    private RecyclerView myRecyclerView;
    FloatingActionButton add_proj_btn;
    private ArrayList proj_ids_arr, proj_titles_arr;

    public FragmentProjects(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myDB = new MyDatabaseHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.projects_fragment, container, false);
        myRecyclerView = v.findViewById(R.id.proj_recyclerview);
        add_proj_btn = v.findViewById(R.id.add_proj_button);
        add_proj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddProjActivity.class);
                startActivity(intent);
            }
        });


        proj_ids_arr = new ArrayList<Integer>();
        proj_titles_arr = new ArrayList<String>();

       storeDataInArrays();

       myAdapter = new RecyclerViewAdapterProj(getActivity(), getContext(), proj_ids_arr, proj_titles_arr);
        //myAdapter.notifyDataSetChanged();
       myRecyclerView.setAdapter(myAdapter);
       myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       return v;
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllProjData();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No project data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                proj_ids_arr.add(cursor.getString(0));
                proj_titles_arr.add(cursor.getString(1));
            }
        }
    }
}
