package com.paxra.android_architecture.application.builder;


import android.content.Context;

import com.paxra.android_architecture.data.database.AppDatabase;
import com.paxra.android_architecture.data.database.RoomDB;
import com.paxra.android_architecture.data.database.TaskService;
import com.paxra.android_architecture.utils.AppExecutors;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseServiceModule {

    @AppScope
    @Provides
    public AppDatabase appDatabase(Context context) {return new RoomDB(context).getAppDatabase();}

    @AppScope
    @Provides
    public TaskService taskService(AppDatabase appDatabase) {return new TaskService(appDatabase); }

}
