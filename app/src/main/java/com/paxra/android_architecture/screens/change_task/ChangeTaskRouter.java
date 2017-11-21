package com.paxra.android_architecture.screens.change_task;

public class ChangeTaskRouter {
    private final ChangeTaskActivity activity;

    public ChangeTaskRouter(ChangeTaskActivity activity) {
        this.activity = activity;
    }


    public void goBack() {
        activity.finish();
    }
}
