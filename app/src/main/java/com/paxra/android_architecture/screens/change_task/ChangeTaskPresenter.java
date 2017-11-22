package com.paxra.android_architecture.screens.change_task;

import android.util.Log;

import com.paxra.android_architecture.data.database.viewmodel.ListViewModel;
import com.paxra.android_architecture.utils.NumUtils;
import com.paxra.android_architecture.utils.rx.RxSchedulers;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ChangeTaskPresenter {
    private final ChangeTaskView view;
    private final ChangeTaskInteractor interactor;
    private final ChangeTaskRouter router;
    private final RxSchedulers rxSchedulers;
    private final CompositeDisposable compositeDisposable;
    private final boolean isEdit;
    private final ListViewModel viewModel;

    public ChangeTaskPresenter(ChangeTaskView view, ChangeTaskInteractor interactor, ChangeTaskRouter router,
                               RxSchedulers rxSchedulers, CompositeDisposable compositeDisposable, boolean isEdit,
                               ListViewModel viewModel) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
        this.rxSchedulers = rxSchedulers;
        this.compositeDisposable = compositeDisposable;
        this.isEdit = isEdit;
        this.viewModel = viewModel;
    }

    public void onCreate() {
        addProgressStateObservable();
        compositeDisposable.add(saveTaskSubscription());
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }

    private void addProgressStateObservable() {
        viewModel.getProgressState().observe(view.getActivity(), aBoolean -> view.showProgress(aBoolean));
    }

    private Disposable saveTaskSubscription() {
        return view.getSaveClickObservable()
                .throttleFirst(NumUtils.TIMEOUT_WAIT, TimeUnit.SECONDS, rxSchedulers.androidUI())
                .doOnNext(aVoid -> viewModel.showProgress(true))
                .delay(NumUtils.TIMEOUT_WAIT, TimeUnit.SECONDS)
                .doOnNext(aVoid -> {
                    if (isEdit) {
                        interactor.editTask(1, view.getTaskText());
                    } else {
                        interactor.saveTask(view.getTaskText());
                    }
                }).subscribe(aVoid -> {
                    viewModel.showProgress(false);
                    router.goBack();
                }, err -> {
                    Log.d("App", err.getMessage());
                });
    }
}
