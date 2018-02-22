package com.example.miodragmilosevic.roomtest.startattack;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.miodragmilosevic.roomtest.startattack.StartAttackUiModel.INACTIVE;
import static com.example.miodragmilosevic.roomtest.startattack.StartAttackUiModel.IN_PROCESSING;
import static com.example.miodragmilosevic.roomtest.startattack.StartAttackUiModel.STARTED;

/**
 * Created by miodrag.milosevic on 1/25/2018.
 */

public class StartAttackViewModel extends BaseViewModel {

    private static final String TAG = "Miki";


    private MutableLiveData<StartAttackUiModel> mViewData;
    private StartAttackRepository mStartAttackRepository;

    public StartAttackViewModel(StartAttackRepository startAttackRepository) {
        mStartAttackRepository = startAttackRepository;
        mViewData = new MutableLiveData<>();
        setDefaultState();
    }

    public void onAttackButtonClick() {
        if (mViewData.getValue().getState() == STARTED) {
            mDisposableList.clear();
            StartAttackUiModel model = mViewData.getValue();
            model.setState(IN_PROCESSING);
            mViewData.setValue(model);
        } else {
            mViewData.setValue(new StartAttackUiModel(STARTED, System.currentTimeMillis(), -1));
            mDisposableList.add(mStartAttackRepository.getElapsedTimeMillis().subscribeOn(Schedulers.io())
                    .map(elapsedTime -> {
                        StartAttackUiModel model = mViewData.getValue();
                        model.setElapsedTime(elapsedTime);
                        return model;
                    })
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<StartAttackUiModel>() {

                        @Override
                        public void onNext(StartAttackUiModel model) {
                            Log.i(TAG, "getElapsedTimeMillis onNext: " + model.getElapsedTime());
                            mViewData.setValue(model);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i(TAG, "getElapsedTimeMillis onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.i(TAG, "getElapsedTimeMillis onComplete: ");
                        }
                    }));
        }
    }

    public LiveData<StartAttackUiModel> getLiveData() {
        return mViewData;
    }

    public void setDefaultState() {
        mViewData.setValue(new StartAttackUiModel(INACTIVE, 0, -1));
    }

    static class Factory extends ViewModelProvider.NewInstanceFactory {

        private StartAttackRepository mStartAttackRepository;

        public Factory() {
        }

        public Factory(@NonNull StartAttackRepository startAttackRepository) {
            mStartAttackRepository = startAttackRepository;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked

            return (T) new StartAttackViewModel(mStartAttackRepository);
        }
    }
}

/*
open class BaseViewModel : ViewModel() {
    private val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    private val loadingStateLiveData: MutableLiveData<Int> = MutableLiveData()
    lateinit var errorObserver: Observer<Throwable>
    lateinit var loadingObserver: Observer<Int>
    fun <T> fromPublisher(publisher: Publisher<T>): MediatorLiveData<T> {
        val mainLiveData = MediatorLiveData<T>()
        mainLiveData.addSource(errorLiveData, errorObserver)
        mainLiveData.addSource(loadingStateLiveData, loadingObserver)
        publisher.subscribe(object : Subscriber<T> {

            override fun onSubscribe(s: Subscription) {
                s.request(java.lang.Long.MAX_VALUE)
                loadingStateLiveData.postValue(LoadingState.LOADING)
            }

            override fun onNext(t: T) {
                mainLiveData.postValue(t)
            }

            override fun onError(t: Throwable) {
                errorLiveData.postValue(t)
            }

            override fun onComplete() {
                loadingStateLiveData.postValue(LoadingState.NOT_LOADING)
            }
        })

        return mainLiveData
    }

}
*/




