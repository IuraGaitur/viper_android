package com.paxra.android_architecture.data.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.paxra.android_architecture.data.domain.Task;

import java.util.List;

import javax.inject.Inject;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> loaddAlTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Task> tasks);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Update()
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM Task WHERE id = :taskID")
    Task getByID(long taskID);


}
