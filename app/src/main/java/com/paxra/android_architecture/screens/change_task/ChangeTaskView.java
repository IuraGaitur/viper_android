package com.paxra.android_architecture.screens.change_task;

import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.paxra.android_architecture.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class ChangeTaskView {
    private final ChangeTaskActivity activity;
    private View view;
    @BindView(R.id.save)
    View save;
    @BindView(R.id.task)
    EditText task;
    @BindView(R.id.loading)
    View loading;

    public ChangeTaskView(ChangeTaskActivity activity) {
        this.activity = activity;
        view = View.inflate(activity, R.layout.activity_change, null);
        ButterKnife.bind(this, view);
    }

    public View getView() {
        return view;
    }

    public Observable<Object> getSaveClickObservable() {
        return RxView.clicks(save);
    }

    public String getTaskText() {
        return task.getText().toString();
    }

    public void showProgress(Boolean visibility) {
        loading.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    public ChangeTaskActivity getActivity() {
        return activity;
    }
}
