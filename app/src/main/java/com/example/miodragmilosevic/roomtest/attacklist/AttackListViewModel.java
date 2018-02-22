package com.example.miodragmilosevic.roomtest.attacklist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.BaseViewModel;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;
import com.example.miodragmilosevic.roomtest.startattack.StartAttackRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miodrag.milosevic on 2/6/2018.
 */

public class AttackListViewModel extends BaseViewModel {
    private static final String TAG = "Miki";
    private MutableLiveData<List<AttackListItemUiModel>> mViewData;
    private AttackListRepository mAttackRepository;
    private AttackItemMapper mMapper;

    public AttackListViewModel(AttackListRepository attackListRepository,AttackItemMapper mapper) {
        mAttackRepository = attackListRepository;
        mViewData = new MutableLiveData();
        mMapper = mapper;
        mDisposableList.add(mAttackRepository.getEpiAttacks().subscribeOn(Schedulers.io())
                .map(attacksDb ->
                        mMapper.mapList(attacksDb)
                )
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<AttackListItemUiModel>>() {

                    @Override
                    public void onNext(List<AttackListItemUiModel> model) {
                        Log.i(TAG, "getEpiAttacks onNext: " + model);
                        mViewData.setValue(model);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getEpiAttacks onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "getEpiAttacks onComplete: ");
                    }
                }));


        setDefaultState();
    }

    public LiveData<List<AttackListItemUiModel>> getLiveData() {
        return mViewData;
    }

    public void setDefaultState() {
//        mViewData.setValue(new AttackListItemUiModel());
    }

    public void onAddButtonClick() {
    }

    static class Factory extends ViewModelProvider.NewInstanceFactory {

        private AttackListRepository mAttackListRepository;
        private AttackItemMapper mAttackItemMapper;

        public Factory() {
        }

        public Factory(@NonNull AttackListRepository attackListRepository,AttackItemMapper mapper) {
            mAttackListRepository = attackListRepository;
            mAttackItemMapper = mapper;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked

            return (T) new AttackListViewModel(mAttackListRepository, mAttackItemMapper);
        }
    }
}
