package com.paxra.android_architecture.screens.list_task;

import com.paxra.android_architecture.data.database.viewmodel.TaskViewModel;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import io.reactivex.Observable;
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
                             RxSchedulers rxSchedulers, CompositeDisposable compositeDisposable, TaskViewModel viewModel) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
        this.rxScheduler = rxSchedulers;
        this.compositeDisposables = compositeDisposable;
        this.taskViewModel = viewModel;
    }

    public void onCreate() {
        initActionBar();
        observeTasks();
        compositeDisposables.add(refreshItemsDisposable());
        compositeDisposables.add(editItemDisposable());
        compositeDisposables.add(removeItemDisposable());
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

    private Disposable refreshItemsDisposable() {
        return view.swipeToRefreshObservable()
                .flatMap(aVoid -> Observable.just(taskViewModel.getTasksViewModel().getValue()))
                .doOnNext(data -> view.showRefresh(false))
                .subscribe(data -> {
                    view.swapData(data);
                });
    }

    private Disposable removeItemDisposable() {
        return view.removeItemClickObservable().flatMap(task -> {
            interactor.removeTask(task);
            return Observable.just(true);
        }).subscribe(data -> {});
    }

    private Disposable editItemDisposable() {
        return view.listItemClick().subscribe(task -> router.startEditTask(task));
    }

    public void onDestroy() {
        compositeDisposables.clear();
    }

    public void onCreateOptions() {
        compositeDisposables.add(addTaskDisposable());
    }
}
