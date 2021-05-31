package com.ryada.calnderproject.ui.picknewalarm;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ryada.calnderproject.R;
import com.ryada.calnderproject.modules.ReminderModule;

import java.util.Calendar;

public class PickNewAlarmActivity extends AppCompatActivity {

    TextView btnSave, btnCancel;
    TextView tvAlarmName;
    TimePicker tpReminder;
    PickReminderViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);
        initView();
        initViewModel();
        btnSave.setOnClickListener(v -> onSaveBtnClicked());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void initViewModel() {
        PickReminderVMFactory factory = new PickReminderVMFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(PickReminderViewModel.class);
    }

    private void onSaveBtnClicked() {
        Calendar calendar = Calendar.getInstance();

        if (isInputOk(calendar)) {
            ReminderModule reminderModule = new ReminderModule();
            reminderModule.setHour(tpReminder.getCurrentHour());
            reminderModule.setMinute(tpReminder.getCurrentMinute());
            reminderModule.setName(tvAlarmName!=null?tvAlarmName.getText().toString():"no name");
            reminderModule.setPm_am(tpReminder.getCurrentHour() >= 12? 1: 0);
            viewModel.addReminder(reminderModule);
            finish();
        }
    }

    private void initView() {
        btnSave = findViewById(R.id.save_btn);
        btnCancel = findViewById(R.id.cancel_btn);
        tvAlarmName = findViewById(R.id.alarm_name_tv);

        tpReminder = findViewById(R.id.time_picker);
        tpReminder.setIs24HourView(false);
        tpReminder.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        tpReminder.setCurrentMinute(Calendar.getInstance().get(Calendar.MINUTE));
    }

    private boolean isInputOk(Calendar calendar) {
        int hour = tpReminder.getCurrentHour();
        int minute = tpReminder.getCurrentMinute();
        boolean value = false;
        // check if the hour and minute are in the future
        if (calendar.get(Calendar.HOUR_OF_DAY) >= hour && calendar.get(Calendar.MINUTE) < minute ) {
            value = true;
        }
        return value;
    }
}
