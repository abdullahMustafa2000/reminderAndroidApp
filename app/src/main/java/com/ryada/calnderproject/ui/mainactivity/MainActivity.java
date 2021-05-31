package com.ryada.calnderproject.ui.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.ryada.calnderproject.R;
import com.ryada.calnderproject.adapters.RemindersAdapter;
import com.ryada.calnderproject.modules.ReminderModule;
import com.ryada.calnderproject.ui.picknewalarm.PickNewAlarmActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        getList();
        ImageButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v-> startActivity(new Intent(this, PickNewAlarmActivity.class)));
    }

    private void initViewModel() {
        MainActivityVMFactory factory = new MainActivityVMFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(MainActivityViewModel.class);
    }

    private void getList() {
        viewModel.getRemindersList().observe(this, reminderModules -> {
            RemindersAdapter adapter = new RemindersAdapter(reminderModules, MainActivity.this);
            RecyclerView recyclerView = findViewById(R.id.rv_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        });
    }
}