package com.gmail.ioanna.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gmail.ioanna.data.dbEntity.Task;

import java.util.List;


public class DatabaseManager {

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public void open(boolean isWritable){
        if(isWritable){
            database = dbHelper.getWritableDatabase();
        } else{
            database = dbHelper.getReadableDatabase();
        }
    }

    public void close(){
        if(database != null){
            database.close();
        }
    }

    public void insertTask(Task task){

        StringBuilder sql = new StringBuilder();

        //INSERT INTO tasks('name', 'percentOfCompletion', 'state', 'estimatedTime', 'startDate', 'dueDate') VALUES ('name', 100, 'New', 90, 2017-08-12, 2017-10-20)
        sql.append("INSERT INTO tasks ('name', 'percentOfCompletion', 'state', 'estimatedTime', " +
                "'startDate', 'dueDate') ");
        sql.append("VALUES (");
        sql.append("'");
        sql.append(task.getName());
        sql.append("', ");
        sql.append(task.getPercentOfCompletion());
        sql.append(", '");
        sql.append(task.getState());
        sql.append("', ");
        sql.append(task.getEstimatedTime());
        sql.append(", ");
        sql.append(task.getStartDate());
        sql.append(", ");
        sql.append(task.getDueDate());
        sql.append(")");

        Log.e("DataBaseManager", "insertUser() sql = " + sql.toString());
        database.execSQL(sql.toString());

    }

    public void updateTask(Task task){

    }

    public List<Task> getTasks(){
        return null;
    }

   /* public User getUserById(String id){

        Cursor cursor = database.rawQuery("SELECT * FROM user INNER JOIN country ON " +
                        "user.countryId = country.id WHERE id = ?",
                new String[]{String.valueOf(id)});

        //Cursor cursor1 = database.rawQuery("SELECT * FROM user WHERE id = " + id, null);

        if(cursor != null){

            User user = new User();

            //вытягиваем данные из Cursor
            cursor.moveToFirst();
            int userId = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            int countryId = cursor.getInt(3);
            String countryName = cursor.getString(4);

            //заполняем объект User
            user.setId(userId);
            user.setName(name);
            user.setAge(age);

            //заполняем Country
            Country country = new Country();
            country.setId(countryId);
            country.setName(countryName);

            user.setCountry(country);

            return user;

        }else{
            Log.e("DatabaseManager","getUserById() cursor is null");
        }

        return null;
    }*/

}
