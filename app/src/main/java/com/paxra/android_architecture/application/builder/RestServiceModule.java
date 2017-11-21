package com.paxra.android_architecture.application.builder;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.paxra.android_architecture.data.api.MainApi;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RestServiceModule {

    private static final String DEV_WEEK_BASE_URL = "https://myapi.com";

    @AppScope
    @Provides
    public MainApi provideDevWeekApi(Gson gson, OkHttpClient okHttpClient, RxSchedulers rxSchedulers) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(rxSchedulers.network()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(DEV_WEEK_BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit.create(MainApi.class);
    }

}
