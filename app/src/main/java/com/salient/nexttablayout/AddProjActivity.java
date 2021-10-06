package com.salient.nexttablayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddProjActivity extends AppCompatActivity {
    EditText proj_name;
    Button add_proj_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proj);

        proj_name = findViewById(R.id.proj_name_input);
        add_proj_btn = findViewById(R.id.proj_add_button);

        add_proj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddProjActivity.this);
                myDB.addProj(proj_name.getText().toString().trim());
            }
        });
    }
}
