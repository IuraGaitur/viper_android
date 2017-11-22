package com.paxra.android_architecture.data.database.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ListViewModel extends ViewModel{

    MutableLiveData<Boolean> showProgress = new MutableLiveData<>();

    public void showProgress(boolean state) {
        this.showProgress.postValue(state);
    }



    public MutableLiveData<Boolean> getProgressState() {
        return showProgress;
    }

}
