package com.paxra.android_architecture.application.builder;

import android.content.Context;

import com.paxra.android_architecture.data.api.MainApi;
import com.paxra.android_architecture.data.database.AppDatabase;
import com.paxra.android_architecture.data.database.TaskService;
import com.paxra.android_architecture.utils.rx.RxBus;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import dagger.Component;

@AppScope
@Component(modules = {
        AppModule.class, RxModule.class, DatabaseServiceModule.class,
        RestServiceModule.class, GsonModule.class, NetworkModule.class
})
public interface AppComponent {

    Context context();

    RxSchedulers rxSchedulers();

    AppDatabase appDatabase();

    TaskService taskService();

    RxBus rxBus();

    MainApi mainApi();
}
