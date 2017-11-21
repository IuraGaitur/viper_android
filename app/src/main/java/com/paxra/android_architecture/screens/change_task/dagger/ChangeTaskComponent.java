package com.paxra.android_architecture.screens.change_task.dagger;

import com.paxra.android_architecture.application.builder.AppComponent;
import com.paxra.android_architecture.screens.change_task.ChangeTaskActivity;
import com.paxra.android_architecture.screens.list_task.ListTaskActivity;

import dagger.Component;

@ChangeTaskScope
@Component(modules = ChangeTaskModule.class, dependencies = AppComponent.class)
public interface ChangeTaskComponent {
    void inject(ChangeTaskActivity activity);
}
