package com.example.miodragmilosevic.roomtest.createattackrecord;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.BaseViewModel;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by miodrag.milosevic on 2/5/2018.
 */

public class CreateAttackViewModel extends BaseViewModel {

    private static final String TAG = "Miki";


    @NonNull
    private MutableLiveData<CreateAttackUiState> viewState;
    private CreateAttackRepository mCreateAttackRepository;
    //maybe DTO instead
    private EpiAttack mEpiAttack;

    public CreateAttackViewModel(CreateAttackRepository createAttackRepository) {
        mCreateAttackRepository = createAttackRepository;
        mEpiAttack = new EpiAttack();
        viewState = new MutableLiveData<>();
        setDefaultState();
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

    public void setDefaultState() {
        viewState.setValue(new CreateAttackUiState());
    }

    public void onLocationSelected(String attackLocation) {
        mEpiAttack.setAttackLocation(attackLocation);
    }

    public void onMedicamentSelected(String medicament) {
        mEpiAttack.setMedicament(medicament);
    }

    public void onAttackTypeSelected(String attackType) {
        mEpiAttack.setAttackType(attackType);
    }

    public void onActivitySelected(String activity) {
        mEpiAttack.setActivity(activity);
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

        public Factory(@NonNull CreateAttackRepository createAttackRepository1) {
            mCreateAttackRepository1 = createAttackRepository1;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CreateAttackViewModel(mCreateAttackRepository1);
        }
    }
}

