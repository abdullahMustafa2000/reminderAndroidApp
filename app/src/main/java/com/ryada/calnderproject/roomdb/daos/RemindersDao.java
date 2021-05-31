package com.ryada.calnderproject.roomdb.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ryada.calnderproject.modules.ReminderModule;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface RemindersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Void addReminder(ReminderModule Reminder);

    @Query("delete from reminders_table where id=:id")
    void cancelReminder(int id);

    @Query("select * from reminders_table")
    LiveData<List<ReminderModule>> getAllReminders();
}
