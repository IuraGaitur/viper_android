package com.paxra.android_architecture.application.builder;


import com.paxra.android_architecture.utils.rx.AppRxSchedulers;
import com.paxra.android_architecture.utils.rx.RxBus;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {

    @AppScope
    @Provides
    public RxSchedulers provideRxSchedulers() {return new AppRxSchedulers(); }

    @AppScope
    @Provides
    public RxBus provideRxBus() {return new RxBus(); }

}
