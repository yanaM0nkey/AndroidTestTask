package com.gmail.ioanna.taskmanagerapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.ioanna.taskmanagerapplication.base.BaseAppCompatActivity;
import com.gmail.ioanna.taskmanagerapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = new MainViewModel(this);
        this.viewModel = viewModel;
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);

        binding.setModel(viewModel);
        setSupportActionBar(binding.toolbar);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(viewModel.adapter);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
