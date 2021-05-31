package com.ryada.calnderproject.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ryada.calnderproject.modules.ReminderModule;
import com.ryada.calnderproject.roomdb.daos.RemindersDao;

@Database(entities = {ReminderModule.class}, version = 1, exportSchema = false)
public abstract class RoomDbManager extends RoomDatabase {

    public abstract RemindersDao getReminderDao();

    public static RoomDbManager ROOM_INSTANCE;
    public static RoomDbManager getInstance(Context context) {
        if (ROOM_INSTANCE == null) {
            ROOM_INSTANCE = Room.databaseBuilder(context, RoomDbManager.class, "reminder_app.db")
                    .enableMultiInstanceInvalidation()
                    .build();
        }
        return ROOM_INSTANCE;
    }
}
