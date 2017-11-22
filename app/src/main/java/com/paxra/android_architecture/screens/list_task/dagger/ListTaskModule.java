package com.paxra.android_architecture.screens.list_task.dagger;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;

import com.paxra.android_architecture.data.api.MainApi;
import com.paxra.android_architecture.data.database.AppDatabase;
import com.paxra.android_architecture.data.database.TaskService;
import com.paxra.android_architecture.data.database.viewmodel.TaskViewModel;
import com.paxra.android_architecture.screens.list_task.ListTaskActivity;
import com.paxra.android_architecture.screens.list_task.ListTaskInteractor;
import com.paxra.android_architecture.screens.list_task.ListTaskPresenter;
import com.paxra.android_architecture.screens.list_task.ListTaskRouter;
import com.paxra.android_architecture.screens.list_task.ListTaskView;
import com.paxra.android_architecture.screens.list_task.list.TaskAdapter;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ListTaskModule {

    private ListTaskActivity activity;

    public ListTaskModule(ListTaskActivity activity) {this.activity = activity;}

    @Provides
    @ListTaskScope
    public ListTaskView listTaskView() {
        return new ListTaskView(activity, new TaskAdapter());
    }

    @Provides
    @ListTaskScope
    public ListTaskPresenter listTaskPresenter(ListTaskView view, ListTaskInteractor interactor, ListTaskRouter router,
                                               RxSchedulers rxSchedulers, TaskViewModel viewModel) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        return new ListTaskPresenter(view, interactor, router, rxSchedulers, compositeDisposable, viewModel);
    }

    @Provides
    @ListTaskScope
    public ListTaskRouter listTaskRouter(MainApi api, TaskService taskService) {
        return new ListTaskRouter(activity);
    }

    @Provides
    @ListTaskScope
    public ListTaskInteractor listTaskInteractor(MainApi api, TaskService taskService) {
        return new ListTaskInteractor(activity, api, taskService);
    }

    @Provides
    @ListTaskScope
    public TaskViewModel viewModel(AppDatabase appDatabase) {
        TaskViewModel taskViewModel = ViewModelProviders.of(activity).get(TaskViewModel.class);
        taskViewModel.setDatabase(appDatabase);
        return taskViewModel;
    }

}
