package com.salient.nexttablayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity{
    EditText title_input;
    Button update_button, delete_button, move_button;
    String id, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        move_button = findViewById(R.id.move_button);
        getAndSetIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title=title_input.getText().toString().trim();
                myDB.updateInListData(id, title);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                title=title_input.getText().toString().trim();
                myDB.moveDataInToNext(id, title);
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");

            title_input.setText(title);
        }
        else{
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(UpdateActivity.this, "Confirm Dialog", Toast.LENGTH_SHORT).show();
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneInListTask(id);
                FragmentIn fragmentInstance= new FragmentIn();
                getSupportFragmentManager().beginTransaction().remove(fragmentInstance).commit();            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }
}
