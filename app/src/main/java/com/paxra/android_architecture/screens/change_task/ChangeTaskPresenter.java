package com.paxra.android_architecture.screens.change_task;

import com.paxra.android_architecture.data.domain.Task;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ChangeTaskPresenter {
    private final ChangeTaskView view;
    private final ChangeTaskInteractor interactor;
    private final ChangeTaskRouter router;
    private final RxSchedulers rxSchedulers;
    private final CompositeDisposable compositeDisposable;
    private final boolean isEdit;

    public ChangeTaskPresenter(ChangeTaskView view, ChangeTaskInteractor interactor, ChangeTaskRouter router, RxSchedulers rxSchedulers, CompositeDisposable compositeDisposable, boolean isEdit) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
        this.rxSchedulers = rxSchedulers;
        this.compositeDisposable = compositeDisposable;
        this.isEdit = isEdit;
    }

    public void onCreate() {
        compositeDisposable.add(saveTaskSubscription());
    }

    public void onDestroy() {

    }

    private Disposable saveTaskSubscription() {
        return view.getSaveClickObservable().flatMap(aVoid -> {
            if (isEdit) {
                interactor.editTask(1, view.getTaskText());
            } else {
                interactor.saveTask(view.getTaskText());
            }
            return null;
        }).subscribeOn(rxSchedulers.background())
                .observeOn(rxSchedulers.androidUI())
                .subscribe(aVoid -> router.goBack());

    }
}
