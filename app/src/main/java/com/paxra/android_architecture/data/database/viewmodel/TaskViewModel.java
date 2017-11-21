package com.paxra.android_architecture.data.database.viewmodel;


import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.paxra.android_architecture.data.database.AppDatabase;
import com.paxra.android_architecture.data.database.RoomDB;
import com.paxra.android_architecture.data.domain.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel{
    private LiveData<List<Task>> tasks;
    private AppDatabase mDb;

    public TaskViewModel(Application application, AppDatabase appDatabase) {
        super(application);
        mDb = appDatabase;
        subscribeToDbChanges();
    }

    private void subscribeToDbChanges() {
        tasks = mDb.taskModel().loaddAlTasks();
    }

    public LiveData<List<Task>> getTasksViewModel() {
        return tasks;
    }

}
