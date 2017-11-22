package com.paxra.android_architecture.screens.change_task;

import com.paxra.android_architecture.data.api.MainApi;
import com.paxra.android_architecture.data.database.TaskService;
import com.paxra.android_architecture.data.domain.Task;

public class ChangeTaskInteractor {
    private final ChangeTaskActivity activity;
    private final MainApi api;
    private final TaskService taskService;

    public ChangeTaskInteractor(ChangeTaskActivity activity, MainApi api, TaskService taskService) {
        this.activity = activity;
        this.api = api;
        this.taskService = taskService;
    }

    public void editTask(int id, String taskText) {
        Task task = new Task(id, taskText);
        taskService.editTask(task);
    }

    public void saveTask(String taskText) {
        Task task = new Task();
        task.setName(taskText);
        taskService.saveTask(task);
    }
}
