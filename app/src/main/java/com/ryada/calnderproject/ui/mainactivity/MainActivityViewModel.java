package com.ryada.calnderproject.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ryada.calnderproject.modules.ReminderModule;
import com.ryada.calnderproject.roomdb.RoomDbManager;
import com.ryada.calnderproject.roomdb.daos.RemindersDao;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    RemindersDao dao;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        dao = RoomDbManager.getInstance(application).getReminderDao();
    }

    public LiveData<List<ReminderModule>> getRemindersList() {
        return dao.getAllReminders();
    }
}
