package com.salient.nexttablayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity {
    EditText event_name, event_date;
    Button add_event_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        event_name = findViewById(R.id.event_input);
        event_date = findViewById(R.id.event_date_input);
        add_event_btn = findViewById(R.id.event_add_button);

        add_event_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddEventActivity.this);
                myDB.addEvent(event_name.getText().toString().trim(), event_date.getText().toString().trim());
            }
        });
    }
}
