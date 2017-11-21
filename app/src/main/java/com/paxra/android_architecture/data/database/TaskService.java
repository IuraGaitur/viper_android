package com.paxra.android_architecture.data.database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.paxra.android_architecture.data.domain.Task;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

public class TaskService {

    private final AppDatabase appDatabase;

    public TaskService(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void saveTask(Task task) {
        this.appDatabase.taskModel().insert(task);
    }

    public void editTask(Task task) {
        this.appDatabase.taskModel().update(task);
    }

    public void removeTask(Task task) {
        this.appDatabase.taskModel().delete(task);
    }

}
