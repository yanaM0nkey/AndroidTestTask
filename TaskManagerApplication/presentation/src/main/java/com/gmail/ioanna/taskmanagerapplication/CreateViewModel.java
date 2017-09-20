package com.gmail.ioanna.taskmanagerapplication;


import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.Gravity;
import android.widget.Toast;

import com.gmail.ioanna.data.db.DatabaseManager;
import com.gmail.ioanna.data.dbEntity.Task;
import com.gmail.ioanna.taskmanagerapplication.base.BaseViewModel;

public class CreateViewModel implements BaseViewModel {

    private Activity activity;
    private DatabaseManager manager;
    private int count;

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> state = new ObservableField<>("");
    public ObservableField<String> percentOfCompletion = new ObservableField<>("");
    public ObservableField<String> estimatedTime = new ObservableField<>("");
    public ObservableField<String> startDate = new ObservableField<>("");
    public ObservableField<String> dueDate = new ObservableField<>("");

    public CreateViewModel(Activity activity, int count) {
        this.activity = activity;
        this.count = count;
        manager = manager.getInstance(activity);
    }

    @Override
    public void init() {}

    @Override
    public void release() {}

    @Override
    public void resume() {}

    @Override
    public void pause() {}

    public void onButtonClick(){
        Task task = new Task();

        try {
            task.setId(count++);
            task.setName(name.get());
            task.setPercentOfCompletion(Integer.valueOf(percentOfCompletion.get()));
            task.setState(state.get());
            task.setEstimatedTime(Integer.valueOf(estimatedTime.get()));
            task.setStartDate(startDate.get());
            task.setDueDate(dueDate.get());
            manager.open(true);
            manager.insertTask(task);
            manager.close();
        } catch (Exception e){
            showError(activity);
        }

        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public static void showError(Activity activity){
        Toast toast = Toast.makeText(activity.getApplicationContext(),
                "Error, check data!", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
