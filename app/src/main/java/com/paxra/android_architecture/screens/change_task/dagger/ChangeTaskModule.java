package com.paxra.android_architecture.screens.change_task.dagger;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;

import com.paxra.android_architecture.data.api.MainApi;
import com.paxra.android_architecture.data.database.TaskService;
import com.paxra.android_architecture.data.database.viewmodel.ListViewModel;
import com.paxra.android_architecture.data.domain.Task;
import com.paxra.android_architecture.screens.change_task.ChangeTaskActivity;
import com.paxra.android_architecture.screens.change_task.ChangeTaskInteractor;
import com.paxra.android_architecture.screens.change_task.ChangeTaskPresenter;
import com.paxra.android_architecture.screens.change_task.ChangeTaskRouter;
import com.paxra.android_architecture.screens.change_task.ChangeTaskView;
import com.paxra.android_architecture.screens.list_task.dagger.ListTaskModule;
import com.paxra.android_architecture.screens.list_task.list.TaskAdapter;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ChangeTaskModule {

    private final boolean isEdit;
    private final Task task;
    private ChangeTaskActivity activity;

    public ChangeTaskModule(ChangeTaskActivity activity, Task task, boolean isEdit) {
        this.activity = activity;
        this.isEdit = isEdit;
        this.task = task;
    }

    @Provides
    @ChangeTaskScope
    public ChangeTaskView view() {
        return new ChangeTaskView(activity);
    }

    @Provides
    @ChangeTaskScope
    public ChangeTaskPresenter presenter(ChangeTaskView view, ChangeTaskInteractor interactor, ChangeTaskRouter router,
                                                   RxSchedulers rxSchedulers, ListViewModel viewModel) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        return new ChangeTaskPresenter(view, interactor, router, rxSchedulers, compositeDisposable, task, isEdit, viewModel);
    }

    @Provides
    @ChangeTaskScope
    public ChangeTaskRouter router(TaskService taskService) {
        return new ChangeTaskRouter(activity);
    }

    @Provides
    @ChangeTaskScope
    public ChangeTaskInteractor interactor(MainApi api, TaskService taskService) {
        return new ChangeTaskInteractor(activity, api, taskService);
    }

    @Provides
    @ChangeTaskScope
    public ListViewModel viewModel() {
        return ViewModelProviders.of(activity).get(ListViewModel.class);
    }

}
