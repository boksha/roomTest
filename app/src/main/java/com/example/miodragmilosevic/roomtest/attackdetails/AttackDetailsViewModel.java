package com.example.miodragmilosevic.roomtest.attackdetails;

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
import com.example.miodragmilosevic.roomtest.createattackrecord.CreateAttackUiState;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

import java.util.List;

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
    private Context mApplication;
    private BaseUiListItemMapper<EpiAttackActivity> mActivityMapper;
    private BaseUiListItemMapper<EpiAttackCause> mCauseMapper;
    private BaseUiListItemMapper<EpiAttackLocation> mLocationMapper;
    private BaseUiListItemMapper<EpiAttackType> mTypeMapper;
    private MutableLiveData<AttackDetailsUiModel> mViewData;
    private AttackDetailsRepository mAttackRepository;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackLocation;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackType;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackCause;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackActivity;
    private MutableLiveData<List<BaseUiListItem>> mSpinnerAttackMedicament;

    public AttackDetailsViewModel(Context context, AttackDetailsRepository attackDetailsRepository, long id) {
        mAttackRepository = attackDetailsRepository;
        mApplication = context;
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
        mTypeMapper = new EpiAttackTypeMapper(mApplication);
        mLocationMapper = new EpiAttackLocationMapper(mApplication);
        mCauseMapper = new EpiAttackCauseMapper(mApplication);
        mActivityMapper = new EpiAttackActivityMapper(mApplication);
        mSpinnerAttackLocation = new MutableLiveData<>();
        mSpinnerAttackType = new MutableLiveData<>();
        mSpinnerAttackCause = new MutableLiveData<>();
        mSpinnerAttackActivity = new MutableLiveData<>();
        mSpinnerAttackMedicament = new MutableLiveData<>();
        loadSpinners();
        setDefaultState();
    }

    private void loadSpinners(){
        mDisposableList.add(mAttackRepository.getAttackActivities().subscribeOn(Schedulers.io())
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
        mDisposableList.add(mAttackRepository.getAttackCauses().subscribeOn(Schedulers.io())
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
        mDisposableList.add(mAttackRepository.getAttackLocations().subscribeOn(Schedulers.io())
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

        mDisposableList.add(mAttackRepository.getAttackTypes().subscribeOn(Schedulers.io())
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


    public void onLocationSelected(BaseUiListItem attackLocationitem) {
//        mEpiAttack.setAttackLocationId(attackLocationitem.getName());
    }

    public void onMedicamentSelected(String medicamentItem) {
//        mEpiAttack.setMedicament(medicamentItem);//change latter
    }

    public void onAttackCauseSelected(BaseUiListItem attackCauseItem) {
//        mEpiAttack.set(attackType);
    }

    public void onAttackTypeSelected(BaseUiListItem attackTypeItem) {
//        mEpiAttack.setAttackType(attackTypeItem.getName());
    }

    public void onActivitySelected(BaseUiListItem activityItem) {
//        mEpiAttack.setActivity(activityItem.getName());
    }

    static class Factory extends ViewModelProvider.NewInstanceFactory {

        private AttackDetailsRepository mAttackDetailsRepository;
        private long mId;
        private Context  mApplicationContext;

        public Factory() {
        }

        public Factory(Context context,@NonNull AttackDetailsRepository attackDetailsRepository, long id) {
            mAttackDetailsRepository = attackDetailsRepository;
            mApplicationContext = context.getApplicationContext();
            mId = id;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new AttackDetailsViewModel(mApplicationContext,mAttackDetailsRepository, mId);
        }
    }
}
