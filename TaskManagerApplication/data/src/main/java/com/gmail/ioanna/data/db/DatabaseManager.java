package com.gmail.ioanna.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gmail.ioanna.data.dbEntity.Task;

import java.text.ParseException;
import java.util.ArrayList;
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

        //INSERT INTO tasks('id', 'name', 'percentOfCompletion', 'state', 'estimatedTime', 'startDate', 'dueDate') VALUES (1, 'name', 100, 'New', 90, 2017-08-12, 2017-10-20)
        sql.append("INSERT INTO newTasks ('id', 'name', 'percentOfCompletion', 'state', 'estimatedTime', " +
                "'startDate', 'dueDate') ");
        sql.append("VALUES (");
        sql.append("'");
        sql.append(task.getId());
        sql.append("', '");
        sql.append(task.getName());
        sql.append("', ");
        sql.append(task.getPercentOfCompletion());
        sql.append(", '");
        sql.append(task.getState());
        sql.append("', ");
        sql.append(task.getEstimatedTime());
        sql.append(", '");
        sql.append(task.getStartDate());
        sql.append("', '");
        sql.append(task.getDueDate());
        sql.append("')");

        Log.e("DataBaseManager", "insertUser() sql = " + sql.toString());
        database.execSQL(sql.toString());

    }

    public void updateTask(Task task){

    }

    public List<Task> getTasks(){

        List<Task> listTasks = new ArrayList<>();

        // Зададим условие для выборки - список столбцов
        String[] projection = {"id", "name", "percentOfCompletion", "state", "estimatedTime",
                "startDate", "dueDate"};

        // Делаем запрос
        Cursor cursor = database.query(
                "newTasks",   // таблица
                projection,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // порядок сортировки

        try {
            Log.e("getTasks()","Таблица содержит " + cursor.getCount() + " гостей.\n\n");

            // Узнаем индекс каждого столбца
            int idColumnIndex = cursor.getColumnIndex("id");
            int nameColumnIndex = cursor.getColumnIndex("name");
            int percentOfCompletionColumnIndex = cursor.getColumnIndex("percentOfCompletion");
            int stateColumnIndex = cursor.getColumnIndex("state");
            int estimatedTimeColumnIndex = cursor.getColumnIndex("estimatedTime");
            int startDateColumnIndex = cursor.getColumnIndex("startDate");
            int dueDateColumnIndex = cursor.getColumnIndex("dueDate");

            // Проходим через все ряды
            while (cursor.moveToNext()) {
                // Используем индекс для получения строки или числа
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentState = cursor.getString(stateColumnIndex);
                int currentPercentOfCompletion = cursor.getInt(percentOfCompletionColumnIndex);
                int currentEstimatedTime = cursor.getInt(estimatedTimeColumnIndex);
                String currentStartDate = cursor.getString(startDateColumnIndex);
                String currentDueDate = cursor.getString(dueDateColumnIndex);
                Log.e("getTasks", currentName + " " + currentState + " " + currentPercentOfCompletion
                + " " + currentEstimatedTime + " " + currentStartDate + " " + currentDueDate);
                listTasks.add(new Task(currentId, currentName, currentPercentOfCompletion,
                        currentState, currentEstimatedTime, currentStartDate,currentDueDate));
            }
        } finally {
            // Всегда закрываем курсор после чтения
            cursor.close();
        }
        return listTasks;
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
