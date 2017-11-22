package com.paxra.android_architecture.screens.list_task;

import com.paxra.android_architecture.data.api.MainApi;
import com.paxra.android_architecture.data.database.TaskService;
import com.paxra.android_architecture.data.domain.Task;

public class ListTaskInteractor {
    private final ListTaskActivity activity;
    private final MainApi api;
    private final TaskService taskService;

    public ListTaskInteractor(ListTaskActivity activity, MainApi api, TaskService taskService) {
        this.activity = activity;
        this.api = api;
        this.taskService = taskService;
    }

    public void removeTask(Task task) {
        taskService.removeTask(task);
    }
}
