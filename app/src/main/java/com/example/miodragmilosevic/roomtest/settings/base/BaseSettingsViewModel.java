package com.example.miodragmilosevic.roomtest.settings.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miodrag.milosevic on 2/13/2018.
 */

public class BaseSettingsViewModel<T> extends BaseViewModel {

    private static final String TAG = "Miki";
    private MutableLiveData<List<BaseSettingsItem>> mViewData;
    private BaseSettingsRepository<T> mBaseSettingsRepository;
    private BaseSettingsItemMapper<T> mMapper;

    public BaseSettingsViewModel(BaseSettingsRepository baseSettingsRepository, BaseSettingsItemMapper mapper) {
        mBaseSettingsRepository = baseSettingsRepository;
        mViewData = new MutableLiveData();
        mMapper = mapper;
        mDisposableList.add(mBaseSettingsRepository.getAllItemsToDisplay().subscribeOn(Schedulers.io())
                .map(attacksDb ->
                        mMapper.mapList(attacksDb)
                )
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<BaseSettingsItem>>() {

                    @Override
                    public void onNext(List<BaseSettingsItem> model) {
                        Log.i(TAG, "getAllItemsToDisplay onNext: " + model);
                        mViewData.setValue(model);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getAllItemsToDisplay onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "getAllItemsToDisplay onComplete: ");
                    }
                }));
    }

    public LiveData<List<BaseSettingsItem>> getLiveData() {
        return mViewData;
    }

    public void onDeleteItemButtonClick(BaseSettingsItem item){
        mDisposableList.add(mBaseSettingsRepository.removeFromDisplayList(item.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "removeFromDisplayList onComplete: " );

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "removeFromDisplayList onError: " + e);
                    }
                }));
    }

    public void onResetMenuItemClick() {
        mDisposableList.add(mBaseSettingsRepository.resetToDefault()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "resetToDefault onComplete: " );

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "resetToDefault onError: " + e);
                    }
                }));
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private BaseSettingsRepository mBaseSettingsRepository;
        private BaseSettingsItemMapper mMapper;


        public Factory(@NonNull BaseSettingsRepository baseSettingsRepository, BaseSettingsItemMapper mapper) {
            mBaseSettingsRepository = baseSettingsRepository;
            mMapper = mapper;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked

            return (T) new BaseSettingsViewModel(mBaseSettingsRepository, mMapper);
        }
    }
}
