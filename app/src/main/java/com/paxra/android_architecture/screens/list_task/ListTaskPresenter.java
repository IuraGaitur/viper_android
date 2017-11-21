package com.paxra.android_architecture.screens.list_task;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.paxra.android_architecture.data.database.viewmodel.TaskViewModel;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ListTaskPresenter {
    private final ListTaskView view;
    private final ListTaskInteractor interactor;
    private final ListTaskRouter router;
    private final RxSchedulers rxScheduler;
    private final CompositeDisposable compositeDisposables;
    private TaskViewModel taskViewModel;

    public ListTaskPresenter(ListTaskView view, ListTaskInteractor interactor, ListTaskRouter router,
                             RxSchedulers rxSchedulers, CompositeDisposable compositeDisposable) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
        this.rxScheduler = rxSchedulers;
        this.compositeDisposables = compositeDisposable;
        taskViewModel = ViewModelProviders.of(view.getActivity()).get(TaskViewModel.class);
    }

    public void onCreate() {
        initActionBar();
        observeTasks();
    }

    private void initActionBar() {
        view.setTootlbar();
        view.setToolbarTitle("Task Menu");
    }

    private void observeTasks() {
        taskViewModel.getTasksViewModel().observe(view.getActivity(), tasks -> view.swapData(tasks));
    }

    private Disposable addTaskDisposable() {
        return view.addMenuItemClicked().subscribe(data -> router.startAddTask());
    }

    public void onDestroy() {
        compositeDisposables.clear();
    }

    public void onCreateOptions() {
        compositeDisposables.add(addTaskDisposable());
    }
}
