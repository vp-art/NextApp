package com.salient.nexttablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddInActivity extends AppCompatActivity {
    EditText title_input;
    Button add_button;
    String id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_in);

        title_input = findViewById(R.id.title_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddInActivity.this);
                myDB.addTask(title_input.getText().toString().trim(), id);
            }
        });
    }

}