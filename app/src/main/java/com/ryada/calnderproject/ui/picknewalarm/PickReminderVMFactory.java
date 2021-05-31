package com.ryada.calnderproject.ui.picknewalarm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PickReminderVMFactory extends ViewModelProvider.AndroidViewModelFactory {
    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public PickReminderVMFactory(@NonNull Application application) {
        super(application);
        new PickReminderViewModel(application);
    }
}
