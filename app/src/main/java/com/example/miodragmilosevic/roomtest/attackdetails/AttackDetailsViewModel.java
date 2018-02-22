package com.example.miodragmilosevic.roomtest.attackdetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.BaseViewModel;
import com.example.miodragmilosevic.roomtest.createattackrecord.CreateAttackUiState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public class AttackDetailsViewModel extends BaseViewModel {

    private static final String TAG = "Miki";
    private final long mAttackId;

    private MutableLiveData<AttackDetailsUiModel> mViewData;
    private AttackDetailsRepository mAttackRepository;

    public AttackDetailsViewModel(AttackDetailsRepository attackDetailsRepository, long id) {
        mAttackRepository = attackDetailsRepository;
        Log.i(TAG, "AttackDetailsViewModel: id= " + id);
        mViewData = new MutableLiveData();
        mAttackId = id;
        mDisposableList.add(mAttackRepository.getAttackById(mAttackId).subscribeOn(Schedulers.io())
                .map(attacksDb -> {
                        /*mMapper.mapList(attacksDb)*/
                            return new AttackDetailsUiModel();
                        }
                )
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<AttackDetailsUiModel>() {
                    @Override
                    public void onSuccess(AttackDetailsUiModel attackDetailsUiModel) {
                        Log.i(TAG, "getAttackById onSuccess: " + attackDetailsUiModel);
                        mViewData.setValue(attackDetailsUiModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getAttackById onError: " + e.getMessage());
                    }

                }));


        setDefaultState();
    }

    public LiveData<AttackDetailsUiModel> getLiveData() {
        return mViewData;
    }

    public void setDefaultState() {
//        mViewData.setValue(new AttackListItemUiModel());
    }

    public void onChangeButtonClick() {

    }

    public void onDeleteButtonClick() {
        Log.i(TAG, "onDeleteButtonClick: "+ mAttackId);
        mDisposableList.add(mAttackRepository.deleteAttack(mAttackId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "deleteAttack onError: " + e.getMessage());
//                        CreateAttackUiState model = viewState.getValue();
//                        model.setDataBaseInsertFailed(true);
//                        viewState.setValue(model);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "saveEpyAttack onComplete: ");
//                        CreateAttackUiState model = viewState.getValue();
//                        model.setCompleted(true);
//                        viewState.setValue(model);
                    }
                }));

    }


    static class Factory extends ViewModelProvider.NewInstanceFactory {

        private AttackDetailsRepository mAttackDetailsRepository;
        private long mId;

        public Factory() {
        }

        public Factory(@NonNull AttackDetailsRepository attackDetailsRepository, long id) {
            mAttackDetailsRepository = attackDetailsRepository;
            mId = id;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new AttackDetailsViewModel(mAttackDetailsRepository, mId);
        }
    }
}
