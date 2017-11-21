package com.paxra.android_architecture.screens.list_task.dagger;

import com.paxra.android_architecture.application.builder.AppComponent;
import com.paxra.android_architecture.screens.list_task.ListTaskActivity;

import dagger.Component;

@ListTaskScope
@Component(modules = ListTaskModule.class, dependencies = AppComponent.class)
public interface ListTaskComponent {
    void inject(ListTaskActivity activity);
}
