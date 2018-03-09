package com.example.miodragmilosevic.roomtest.createattackrecord;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.BaseUiListItem;
import com.example.miodragmilosevic.roomtest.base.BaseUiListItemMapper;
import com.example.miodragmilosevic.roomtest.base.BaseViewModel;
import com.example.miodragmilosevic.roomtest.common.mapper.EpiAttackActivityMapper;
import com.example.miodragmilosevic.roomtest.common.mapper.EpiAttackCauseMapper;
import com.example.miodragmilosevic.roomtest.common.mapper.EpiAttackLocationMapper;
import com.example.miodragmilosevic.roomtest.common.mapper.EpiAttackTypeMapper;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miodrag.milosevic on 2/5/2018.
 */

public class CreateAttackViewModel extends BaseViewModel {

    private static final String TAG = "Miki";


    @NonNull
    private MutableLiveData<CreateAttackUiState> viewState;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackLocation;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackType;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackCause;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackActivity;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackMedicament;

    private CreateAttackRepository mCreateAttackRepository;
    //maybe DTO instead
    private EpiAttack mEpiAttack;
    private Context mApplication;
    private BaseUiListItemMapper<EpiAttackActivity> mActivityMapper;
    private BaseUiListItemMapper<EpiAttackCause> mCauseMapper;
    private BaseUiListItemMapper<EpiAttackLocation> mLocationMapper;
    private BaseUiListItemMapper<EpiAttackType> mTypeMapper;

    public CreateAttackViewModel(Context context,CreateAttackRepository createAttackRepository) {
        mCreateAttackRepository = createAttackRepository;
        mApplication = context;
        mTypeMapper = new EpiAttackTypeMapper(mApplication);
        mLocationMapper = new EpiAttackLocationMapper(mApplication);
        mCauseMapper = new EpiAttackCauseMapper(mApplication);
        mActivityMapper = new EpiAttackActivityMapper(mApplication);
        mEpiAttack = new EpiAttack();
        viewState = new MutableLiveData<>();
        mSpinnerAttackLocation = new MutableLiveData<>();
        mSpinnerAttackType = new MutableLiveData<>();
        mSpinnerAttackCause = new MutableLiveData<>();
        mSpinnerAttackActivity = new MutableLiveData<>();
        mSpinnerAttackMedicament = new MutableLiveData<>();
        loadSpinners();
        setDefaultState();
    }

    private void loadSpinners(){
        mDisposableList.add(mCreateAttackRepository.getAttackActivities().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<EpiAttackActivity>>() {
                    @Override
                    public void onNext(List<EpiAttackActivity> list) {
                        mSpinnerAttackActivity.setValue(mActivityMapper.mapList(list));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getAttackActivities onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "getAttackActivities onComplete: ");

                    }
                }));
        mDisposableList.add(mCreateAttackRepository.getAttackCauses().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<EpiAttackCause>>() {
                    @Override
                    public void onNext(List<EpiAttackCause> list) {
                        mSpinnerAttackCause.setValue(mCauseMapper.mapList(list));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getAttackCauses onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "getAttackCauses onComplete: ");

                    }
                }));
        mDisposableList.add(mCreateAttackRepository.getAttackLocations().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<EpiAttackLocation>>() {
                    @Override
                    public void onNext(List<EpiAttackLocation> list) {
                        mSpinnerAttackLocation.setValue(mLocationMapper.mapList(list));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getAttackCauses onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "getAttackCauses onComplete: ");

                    }
                }));

        mDisposableList.add(mCreateAttackRepository.getAttackTypes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<List<EpiAttackType>>() {
                    @Override
                    public void onNext(List<EpiAttackType> list) {
                        mSpinnerAttackType.setValue(mTypeMapper.mapList(list));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "getAttackCauses onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "getAttackCauses onComplete: ");

                    }
                }));
    }
    public void onSaveButtonClick() {
        if (validation())  {
            mDisposableList.add(mCreateAttackRepository.saveEpyAttack(mEpiAttack).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableCompletableObserver() {

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG, "saveEpyAttack onError: " + e.getMessage());
                            CreateAttackUiState model = viewState.getValue();
                            model.setDataBaseInsertFailed(true);
                            viewState.setValue(model);
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "saveEpyAttack onComplete: ");
                            CreateAttackUiState model = viewState.getValue();
                            model.setCompleted(true);
                            viewState.setValue(model);
                        }
                    }));
        }
    }

    private boolean validation() {
        boolean result = true;
        CreateAttackUiState model = viewState.getValue();
        if (mEpiAttack.getElapsedTime() == -1){
            model.setTimeMissing(true);
            result = false;
        }
        if (mEpiAttack.getStartTime() == -1){
            model.setDateMissing(true);
            result = false;
        }
        if (!result) {
            viewState.setValue(model);
        }
        return result;
    }

    public LiveData<CreateAttackUiState> getLiveData() {
        return viewState;
    }

    public LiveData<List<BaseUiListItem>> getSpinnerTypeLiveData() {
        return mSpinnerAttackType;
    }

    public LiveData<List<BaseUiListItem>> getSpinnerLocationLiveData() {
        return mSpinnerAttackLocation;
    }

    public LiveData<List<BaseUiListItem>> getSpinnerMedicamentLiveData() {
        return mSpinnerAttackMedicament;
    }

    public LiveData<List<BaseUiListItem>> getSpinnerActivityLiveData() {
        return mSpinnerAttackActivity;
    }

    public LiveData<List<BaseUiListItem>> getSpinnerCauseLiveData() {
        return mSpinnerAttackCause;
    }

    public void setDefaultState() {
        viewState.setValue(new CreateAttackUiState());
    }

    public void onLocationSelected(BaseUiListItem attackLocationitem) {
        mEpiAttack.setAttackLocationId(attackLocationitem.getId());
    }

    public void onMedicamentSelected(String medicamentItem) {
        mEpiAttack.setMedicament(medicamentItem);//change latter
    }

    public void onAttackCauseSelected(BaseUiListItem attackCauseItem) {
        mEpiAttack.setAttackCauseId(attackCauseItem.getId());
    }

    public void onAttackTypeSelected(BaseUiListItem attackTypeItem) {
        mEpiAttack.setAttackTypeId(attackTypeItem.getId());
    }

    public void onActivitySelected(BaseUiListItem activityItem) {
        mEpiAttack.setActivityId(activityItem.getId());
    }

    public void setDescription(String description) {
        mEpiAttack.setDescription(description);
    }

    public void onDateSet(long startTime) {
        mEpiAttack.setStartTime(startTime);
    }

    public void onTimeSet(long elapsedTime) {
        mEpiAttack.setElapsedTime(elapsedTime);
    }

    static class Factory extends ViewModelProvider.NewInstanceFactory {

        private CreateAttackRepository mCreateAttackRepository1;
        private Context  mApplicationContext;

        public Factory(Context context, @NonNull CreateAttackRepository createAttackRepository1) {
            mCreateAttackRepository1 = createAttackRepository1;
            mApplicationContext = context.getApplicationContext();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CreateAttackViewModel(mApplicationContext, mCreateAttackRepository1);
        }
    }
}

