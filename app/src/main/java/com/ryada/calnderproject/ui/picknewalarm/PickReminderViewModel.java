package com.ryada.calnderproject.ui.picknewalarm;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import java.util.Calendar;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ryada.calnderproject.AlarmReceiver;
import com.ryada.calnderproject.modules.ReminderModule;
import com.ryada.calnderproject.roomdb.RoomDbManager;
import com.ryada.calnderproject.roomdb.daos.RemindersDao;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PickReminderViewModel extends AndroidViewModel {
    RemindersDao remindersDao;
    Application application;
    public PickReminderViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        RoomDbManager dbManager = RoomDbManager.getInstance(application);
        remindersDao = dbManager.getReminderDao();
    }

    public void addReminder(ReminderModule reminderModule) {
        Completable.fromRunnable(() -> remindersDao.addReminder(reminderModule)).subscribeOn(Schedulers.io())
                .subscribe(() -> Log.d("TAG", "addToRoom: success"), e-> Log.d("TAG", "addToRoom: "+e.getMessage()));
        addToAlarms(reminderModule);
    }

    private void addToAlarms(ReminderModule reminderModule) {
        AlarmManager alarmManager = (AlarmManager) getApplication().getSystemService(Context.ALARM_SERVICE);
        int hour = reminderModule.getHour();
        int minute = reminderModule.getMinute();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        Intent intent = new Intent(application, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(application, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

}
