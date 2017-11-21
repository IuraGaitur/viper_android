package com.paxra.android_architecture.data.database;


import android.arch.persistence.room.Room;
import android.content.Context;

public class RoomDB {
    private static final String DATABASE_NAME = "user-database";
    private AppDatabase appDatabase;


    public RoomDB(Context context) {
        //Intiliaze the room database with database name
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
