package com.paxra.android_architecture.utils.rx;

import io.reactivex.Scheduler;

public interface RxSchedulers {

    Scheduler androidUI();

    Scheduler io();

    Scheduler computation();

    Scheduler network();

    Scheduler immediate();

    Scheduler background();
}
