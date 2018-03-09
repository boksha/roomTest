package com.example.miodragmilosevic.roomtest.settings.adjustattackactivities;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackResourceDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

class AdjustAttackActivitiesRepository extends BaseSettingsRepository<EpiAttackActivity> {
    public AdjustAttackActivitiesRepository(EpiAttackResourceDao epiAttackResourceDao) {
        super(epiAttackResourceDao);
    }

    @Override
    public Observable<List<EpiAttackActivity>> getAllItemsToDisplay() {
        return mEpiAttackResourceDao.getAllActivitiesToDisplay().toObservable();
    }

    @Override
    public Completable removeFromDisplayList(long id) {
        return Completable.fromAction(()->mEpiAttackResourceDao.updateAttackActivitiesDisplayListById(id,false));
    }

    @Override
    public Completable resetToDefault() {
        return Completable.fromAction(()->mEpiAttackResourceDao.resetAttackActivitiesToDefault());
    }

    @Override
    public Completable addNewItem(EpiAttackActivity item) {
        return Completable.fromAction(()->mEpiAttackResourceDao.insertActivity(item));
    }
}
