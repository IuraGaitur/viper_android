package com.paxra.android_architecture.screens.change_task;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.paxra.android_architecture.application.ArchitectureApp;
import com.paxra.android_architecture.data.domain.Task;
import com.paxra.android_architecture.screens.change_task.dagger.ChangeTaskModule;
import com.paxra.android_architecture.screens.change_task.dagger.DaggerChangeTaskComponent;
import com.paxra.android_architecture.screens.list_task.ListTaskActivity;
import com.paxra.android_architecture.screens.list_task.ListTaskPresenter;
import com.paxra.android_architecture.screens.list_task.ListTaskView;
import com.paxra.android_architecture.screens.list_task.dagger.DaggerListTaskComponent;
import com.paxra.android_architecture.screens.list_task.dagger.ListTaskModule;

import javax.inject.Inject;

public class ChangeTaskActivity extends AppCompatActivity{

    private final static String EXTRA_EDIT = "edit_task";
    private static final String EXTRA_TASK = "task";

    @Inject
    ChangeTaskPresenter presenter;

    @Inject
    ChangeTaskView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isEdit = getIntent().getBooleanExtra(EXTRA_EDIT, false);
        Task task = (Task) getIntent().getSerializableExtra(EXTRA_TASK);
        DaggerChangeTaskComponent.builder().appComponent(ArchitectureApp.appComponent(this))
                .changeTaskModule(new ChangeTaskModule(this, task, isEdit)).build().inject(this);
        setContentView(view.getView());
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public static void start(Activity activity, boolean editTask) {
        start(activity, null, editTask);
    }

    public static void start(Activity activity, Task task, boolean editTask) {
        Intent intent = new Intent(activity, ChangeTaskActivity.class);
        intent.putExtra(EXTRA_TASK, task);
        intent.putExtra(EXTRA_EDIT, editTask);
        activity.startActivity(intent);

    }
}
