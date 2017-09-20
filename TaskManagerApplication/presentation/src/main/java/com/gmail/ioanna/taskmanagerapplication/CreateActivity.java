package com.gmail.ioanna.taskmanagerapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gmail.ioanna.taskmanagerapplication.base.BaseAppCompatActivity;
import com.gmail.ioanna.taskmanagerapplication.databinding.ActivityCreateBinding;


public class CreateActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int count = getIntent().getExtras().getInt("count");
        CreateViewModel viewModel = new CreateViewModel(this, count);
        this.viewModel = viewModel;

        ActivityCreateBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_create);
        binding.setModel(viewModel);

        super.onCreate(savedInstanceState);

    }
}
