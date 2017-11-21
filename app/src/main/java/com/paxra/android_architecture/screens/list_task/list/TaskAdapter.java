package com.paxra.android_architecture.screens.list_task.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.paxra.android_architecture.R;
import com.paxra.android_architecture.data.domain.Task;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class TaskAdapter extends RecyclerView.Adapter {

    List<Task> taskList = new ArrayList<>();
    PublishSubject<Integer> removeClickSubject = PublishSubject.create();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskHolder(View.inflate(parent.getContext(), R.layout.list_item, null), removeClickSubject);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TaskHolder) holder).bind(taskList.get(position));
    }

    public io.reactivex.Observable<Task> getClickObservable() {
        return removeClickSubject.map(position -> taskList.get(position));
    }

    public void swapData(List<Task> tasks) {
        taskList.clear();
        taskList.addAll(tasks);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
