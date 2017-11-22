package com.paxra.android_architecture.screens.list_task;

import com.paxra.android_architecture.data.api.MainApi;
import com.paxra.android_architecture.data.database.TaskService;
import com.paxra.android_architecture.data.domain.Task;
import com.paxra.android_architecture.screens.change_task.ChangeTaskActivity;

public class ListTaskRouter {
    private final ListTaskActivity activity;

    public ListTaskRouter(ListTaskActivity activity) {
        this.activity = activity;

    }

    public void startAddTask() {
        ChangeTaskActivity.start(activity, false);
    }

    public void startEditTask(Task task) {
        ChangeTaskActivity.start(activity, true);
    }
}
