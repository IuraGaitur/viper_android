package com.paxra.android_architecture.application;

import android.app.Application;
import android.content.Context;

import com.paxra.android_architecture.application.builder.AppComponent;
import com.paxra.android_architecture.application.builder.AppModule;
import com.paxra.android_architecture.application.builder.DaggerAppComponent;
import com.paxra.android_architecture.data.database.AppDatabase;
import com.paxra.android_architecture.data.database.RoomDB;


public class ArchitectureApp extends Application{
    private static ArchitectureApp instance;
    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initComponent();
    }

    public static synchronized ArchitectureApp getInstance() {
        return instance;
    }

    public static AppComponent appComponent(Context context) {
        return ((ArchitectureApp) context.getApplicationContext()).getAppComponent();
    }

    protected AppComponent getAppComponent() {
        return appComponent;
    }

    protected void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
