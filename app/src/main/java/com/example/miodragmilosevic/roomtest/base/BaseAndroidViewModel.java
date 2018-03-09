package com.example.miodragmilosevic.roomtest.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by miodrag.milosevic on 2/26/2018.
 */

public class BaseAndroidViewModel extends AndroidViewModel {

    private static final String TAG = "BaseViewModel";
    protected CompositeDisposable mDisposableList = new CompositeDisposable();

    public BaseAndroidViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
        mDisposableList.clear();
    }

}
