package com.example.miodragmilosevic.roomtest.base;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public class BaseViewModel extends ViewModel {

    private static final String TAG = "BaseViewModel";
    protected CompositeDisposable mDisposableList = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "onCleared: ");
        mDisposableList.clear();
    }

}
