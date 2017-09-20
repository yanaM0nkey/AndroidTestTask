package com.gmail.ioanna.taskmanagerapplication;


import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;

import com.gmail.ioanna.data.db.DatabaseManager;
import com.gmail.ioanna.data.dbEntity.Task;
import com.gmail.ioanna.taskmanagerapplication.base.BaseViewModel;

public class EditViewModel implements BaseViewModel {

    private Activity activity;
    private DatabaseManager manager;

    private int id;

    public ObservableField<String> name = new ObservableField<>("");
    public ObservableField<String> state = new ObservableField<>("");
    public ObservableField<String> percentOfCompletion = new ObservableField<>("");
    public ObservableField<String> estimatedTime = new ObservableField<>("");
    public ObservableField<String> startDate = new ObservableField<>("");
    public ObservableField<String> dueDate = new ObservableField<>("");

    public EditViewModel(Activity activity, int id) {
        this.activity = activity;
        this.id = id;
        manager = manager.getInstance(activity);
    }

    @Override
    public void init() {}

    @Override
    public void release() {}

    @Override
    public void resume() {
        manager.open(false);
        Task task = manager.getTaskById(id);
        manager.close();
        name.set(task.getName());
        percentOfCompletion.set(String.valueOf(task.getPercentOfCompletion()));
        state.set(task.getState());
        estimatedTime.set(String.valueOf(task.getEstimatedTime()));
        startDate.set(task.getStartDate());
        dueDate.set(task.getDueDate());
    }

    @Override
    public void pause() {}

    public void onButtonClick(){
        Task task = new Task();

        try {
            task.setId(id);
            task.setName(name.get());
            task.setPercentOfCompletion(Integer.valueOf(percentOfCompletion.get()));
            task.setState(state.get());
            task.setEstimatedTime(Integer.valueOf(estimatedTime.get()));
            task.setStartDate(startDate.get());
            task.setDueDate(dueDate.get());
        }catch(Exception e){
            CreateViewModel.showError(activity);
        }
        manager.open(true);
        int count = manager.updateTask(task);
        if(count == 1){
            CreateViewModel.showError(activity);
        }
        Log.e("onClick edit", String.valueOf(count));
        manager.close();

        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }
}
