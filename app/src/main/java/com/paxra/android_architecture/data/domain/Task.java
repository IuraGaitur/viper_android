package com.paxra.android_architecture.data.domain;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Task implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;

    public Task() {}

    @Ignore
    public Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Task(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
