package com.paxra.android_architecture.screens.list_task;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding2.view.RxMenuItem;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.paxra.android_architecture.R;
import com.paxra.android_architecture.data.domain.Task;
import com.paxra.android_architecture.screens.list_task.list.TaskAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class ListTaskView {
    private final TaskAdapter taskAdapter;
    private final ListTaskActivity activity;
    private View view;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MenuItem saveItem;

    public ListTaskView(ListTaskActivity activity, TaskAdapter taskAdapter) {
        this.activity = activity;
        this.taskAdapter = taskAdapter;
        view = View.inflate(activity, R.layout.activity_task_list, null);
        ButterKnife.bind(this, view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(taskAdapter);
    }

    public View getView() {
        return view;
    }

    public boolean addOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        saveItem = menu.findItem(R.id.add);
        return true;
    }

    public Observable<Object> addMenuItemClicked() {
        return RxMenuItem.clicks(saveItem);
    }

    public Observable<Object> swipeToRefreshObservable() {
        return RxSwipeRefreshLayout.refreshes(refreshLayout);
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void swapData(List<Task> tasks) {
        taskAdapter.swapData(tasks);
    }

    public void setTootlbar() {
        activity.setSupportActionBar(toolbar);
    }

    public void setToolbarTitle(String toolbarTitle) {
        this.toolbar.setTitle(toolbarTitle);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void showToast() {
        Snackbar.make(view, "On Resume called", Snackbar.LENGTH_SHORT).show();
    }

    public void showRefresh(boolean show) {
        refreshLayout.setRefreshing(show);
    }

    public Observable<Task> removeItemClickObservable() {
        return taskAdapter.getRemoveClickObservable();
    }

    public Observable<Task> listItemClick() {
        return taskAdapter.getEditClickObservable();
    }
}
