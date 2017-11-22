package com.paxra.android_architecture.screens.list_task.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.paxra.android_architecture.R;
import com.paxra.android_architecture.data.domain.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

public class TaskHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.remove)
    ImageView remove;

    public TaskHolder(View itemView, PublishSubject<Integer> editClickSubject, PublishSubject clickSubject) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ((View)title.getParent()).setOnClickListener(view -> {editClickSubject.onNext(getAdapterPosition());});
        remove.setOnClickListener(view -> {clickSubject.onNext(getAdapterPosition());});
    }

    public void bind(Task task) {
        title.setText(task.getName());
    }
}
