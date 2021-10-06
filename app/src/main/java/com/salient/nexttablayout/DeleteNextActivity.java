package com.salient.nexttablayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteNextActivity extends AppCompatActivity {
    EditText next_title_input;
    Button delete_button;
    String id, title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_next);

        next_title_input = findViewById(R.id.title_input_next);
        delete_button = findViewById(R.id.delete_button_next);
        getAndSetIntentData();
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = next_title_input.getText().toString().trim();
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");

            next_title_input.setText(title);
        }
        else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DeleteNextActivity.this, "Confirm Dialog", Toast.LENGTH_SHORT).show();
                MyDatabaseHelper myDB = new MyDatabaseHelper(DeleteNextActivity.this);
                myDB.deleteOneNextListTask(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}
