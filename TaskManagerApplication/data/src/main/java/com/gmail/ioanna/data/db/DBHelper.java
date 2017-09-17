package com.gmail.ioanna.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tasks";
    private static final int VERSION = 0;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREAT TABLE tasks ('name' TEXT, 'percentOfCompletion' INTEGER, " +
                "'state' TEXT, 'estimatedTime' INTEGER, 'startDate' DATETIME DEFAULT CURRENT_DATE," +
                " 'dueDate' DATETIME DEFAULT CURRENT_DATE)");
        Log.e("DBHelper", "onCreate()");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.e("DBHelper", "onUpgrade()");

    }
}
