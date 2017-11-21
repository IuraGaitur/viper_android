package com.paxra.android_architecture.screens.list_task;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.paxra.android_architecture.application.ArchitectureApp;
import com.paxra.android_architecture.application.builder.AppComponent;
import com.paxra.android_architecture.screens.list_task.dagger.DaggerListTaskComponent;
import com.paxra.android_architecture.screens.list_task.dagger.ListTaskModule;

import javax.inject.Inject;

public class ListTaskActivity extends AppCompatActivity {

    @Inject
    ListTaskPresenter presenter;

    @Inject
    ListTaskView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerListTaskComponent.builder().appComponent(ArchitectureApp.appComponent(this))
                .listTaskModule(new ListTaskModule(this)).build().inject(this);
        setContentView(view.getView());
        presenter.onCreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean value = view.addOptionsMenu(menu);
        presenter.onCreateOptions();
        return value;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
