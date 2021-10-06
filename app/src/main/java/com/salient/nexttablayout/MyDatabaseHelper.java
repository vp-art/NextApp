package com.salient.nexttablayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "NextApp.db";
    private static final int DATABASE_VERSION = '3';

    private static final String TABLE_NAME = "task_table"; //In list table
    private static final String PROJ_TABLE_NAME = "proj_table"; // Project table
    private static final String EVENT_TABLE_NAME = "event_table"; // Event table
    private static final String TASK_TABLE_NAME = "next_table"; // Next table
    private static final String COLUMN_ID = "_id"; // In task id
    private static final String PROJ_COLUMN_ID = "proj_id"; // Project id
    private static final String FOR_PROJ_COLUMN_ID = "for_proj_id"; //Project id foreign key
    private static final String TASK_COLUMN_ID = "next_id"; // Next task id
    private static final String FOR_COLUMN_ID = "for_next_id"; // Next id foreign key
    private static final String EVENT_COLUMN_ID = "event_id"; // Event id
    private static final String COLUMN_TITLE = "task_title"; // In task name
    private static final String PROJ_COLUMN_TITLE = "proj_title"; // Project task name
    private static final String TASK_COLUMN_TITLE = "next_title"; // Next task name
    private static final String EVENT_COLUMN_TITLE = "event_title"; // Event name
    private static final String EVENT_COLUMN_DATE = "event_date"; // Event date

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE +
                        " TEXT, " + FOR_PROJ_COLUMN_ID + " INTEGER, FOREIGN KEY("
                        + FOR_PROJ_COLUMN_ID + ") REFERENCES "+
                        PROJ_TABLE_NAME +"(" + PROJ_COLUMN_ID + ") );";
        String query1 =
                "CREATE TABLE " + TASK_TABLE_NAME +
                        " (" + TASK_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TASK_COLUMN_TITLE + " TEXT, " + FOR_COLUMN_ID + " INTEGER, FOREIGN KEY("
                        + FOR_COLUMN_ID + ") REFERENCES " + TABLE_NAME +"(" + COLUMN_ID + ") );";

        String query2 =
                "CREATE TABLE " + PROJ_TABLE_NAME +
                        " (" + PROJ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        PROJ_COLUMN_TITLE + " TEXT);";

        /*String query4 =
                "CREATE TABLE " + SUB_PROJ_TABLE_NAME +
                        " (" + SUB_PROJ_COLUMN_ID + " INTEGER, " + SUB_PROJ_COLUMN_TITLE +
                        " TEXT,"+ PROJ_FOR_COLUMN_ID +" INTEGER, CONSTRAINTS fk_sub_proj, " +
                        "FOREIGN KEY(" + SUB_PROJ_COLUMN_ID + ") REFERENCES "+ PROJ_TABLE_NAME +"("
                        + PROJ_COLUMN_ID + ") );";*/

        String query3 =
                "CREATE TABLE " + EVENT_TABLE_NAME +
                        " (" + EVENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        EVENT_COLUMN_TITLE + " TEXT, " + EVENT_COLUMN_DATE + " TEXT);";

        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        //db.execSQL(query4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +TASK_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +PROJ_TABLE_NAME);
//      db.execSQL("DROP TABLE IF EXISTS " +SUB_PROJ_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +EVENT_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    //Add in In List
    void addTask(String title, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(FOR_PROJ_COLUMN_ID, id);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    //Add in Projects List
    void addProj(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PROJ_COLUMN_TITLE,title);

        long result = db.insert(PROJ_TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    //Add in Events List
    void addEvent(String title, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EVENT_COLUMN_TITLE,title);
        cv.put(EVENT_COLUMN_DATE,date);

        long result = db.insert(EVENT_TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    //Read In List Data
    Cursor readInListData(){
        String query = "SELECT * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    //Update In List Data
    void updateInListData(String row_id, String title){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TITLE, title);

            long result = db.update(TABLE_NAME, cv, "_id = ?", new String[]{row_id});
            if (result == -1) {
                Toast.makeText(context, "Failed to Update.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Update Successful!" + result, Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
            System.out.println("Error " + e.getMessage());
        }
    }
    //Delete In List data
    void deleteOneInListTask(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Delete Successful.", Toast.LENGTH_SHORT).show();
        }
    }
    //Delete Next List data
    void deleteOneNextListTask(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TASK_TABLE_NAME, "next_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Delete Successful.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteProject(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(PROJ_TABLE_NAME, "proj_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to delete.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Delete Successful.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteInListData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    //Move data from in list to next list
    void moveDataInToNext(String id, String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TASK_COLUMN_TITLE, title);
        cv.put(FOR_COLUMN_ID, id);

        long result = db.insert(TASK_TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Moved Successfully!", Toast.LENGTH_SHORT).show();
        }
        deleteOneInListTask(id);
    }
    //Read Next List
    Cursor readAllNextData(){
        String query1 = "SELECT * FROM " + TASK_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query1, null);
        }
        return cursor;
    }
    //Read Project List
    Cursor readAllProjData(){
        String query1 = "SELECT * FROM " + PROJ_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query1, null);
            /*while (cursor.moveToNext()){
                Log.d("debug",cursor.getString(1));
            }*/
        }
        return cursor;
    }
    //Read Event List
    Cursor readAllEventData(){
        String query1 = "SELECT * FROM " + EVENT_TABLE_NAME + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query1, null);
        }
        return cursor;
    }
    //Add task to a project
    /*public void addProjTask(String proj_for_id, String sub_task_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SUB_PROJ_COLUMN_TITLE, sub_task_name);
        cv.put(PROJ_FOR_COLUMN_ID, proj_for_id);

        long result = db.insert(SUB_PROJ_TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }*/

    Cursor readAllProjectTaskData(){
        String query1 = "SELECT " + FOR_PROJ_COLUMN_ID + ", " + COLUMN_TITLE + " FROM " + TABLE_NAME + " INNER JOIN "
                + PROJ_TABLE_NAME + " ON " + TABLE_NAME + "." + FOR_PROJ_COLUMN_ID +
                " = " + PROJ_TABLE_NAME + "." + PROJ_COLUMN_ID + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query1, null);
        }
        return cursor;
    }
}
