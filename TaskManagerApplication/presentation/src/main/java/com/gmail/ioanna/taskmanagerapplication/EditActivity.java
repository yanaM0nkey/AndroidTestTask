package com.gmail.ioanna.taskmanagerapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Spinner;

import com.gmail.ioanna.taskmanagerapplication.base.BaseActivity;
import com.gmail.ioanna.taskmanagerapplication.base.BaseAppCompatActivity;
import com.gmail.ioanna.taskmanagerapplication.databinding.ActivityEditBinding;

public class EditActivity extends BaseAppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int id = getIntent().getExtras().getInt("id");

        EditViewModel viewModel = new EditViewModel(this, id);
        this.viewModel = viewModel;
        ActivityEditBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_edit);
        binding.setModel(viewModel);

        super.onCreate(savedInstanceState);
    }
}
