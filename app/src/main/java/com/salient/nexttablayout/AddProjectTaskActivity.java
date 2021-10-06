package com.salient.nexttablayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddProjectTaskActivity extends AppCompatActivity {
    EditText proj_task;
    Button add_proj_btn1;
    Button delete_proj_btn;
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_task);

//        RecyclerViewAdapterProj obj = new RecyclerViewAdapterProj();
        proj_task = findViewById(R.id.proj_task_input);
        add_proj_btn1 = findViewById(R.id.proj_add_button1);
        delete_proj_btn = findViewById(R.id.proj_delete_button);
        getAndSetIntentData();
        this.setTitle(" ");

        add_proj_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddProjectTaskActivity.this);
                myDB.addTask(proj_task.getText().toString().trim(), id);
            }
        });
        delete_proj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddProjectTaskActivity.this);
                myDB.deleteProject(id);
//                myDB.deleteOneInListTask(id);
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
        else{
            Toast.makeText(this, "No Intent Data", Toast.LENGTH_SHORT).show();
        }
    }
}
