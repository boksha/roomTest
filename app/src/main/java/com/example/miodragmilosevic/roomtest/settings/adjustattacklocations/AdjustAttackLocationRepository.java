package com.example.miodragmilosevic.roomtest.settings.adjustattacklocations;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackResourceDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public class AdjustAttackLocationRepository extends BaseSettingsRepository<EpiAttackLocation> {

        protected AdjustAttackLocationRepository(EpiAttackResourceDao epiAttackResourceDao) {
            super(epiAttackResourceDao);
        }

        @Override
        public Observable<List<EpiAttackLocation>> getAllItemsToDisplay() {
            return mEpiAttackResourceDao.getAllLocationsToDisplay().toObservable();

        }

        @Override
        public Completable removeFromDisplayList(long id) {
            return Completable.fromAction(()->mEpiAttackResourceDao.updateAttackLocationDisplayListById(id,false));
        }

        @Override
        public Completable resetToDefault() {
            return Completable.fromAction(()->mEpiAttackResourceDao.resetAttackLocationToDefault());
        }

    @Override
    public Completable addNewItem(EpiAttackLocation item) {
        return Completable.fromAction(()->mEpiAttackResourceDao.insertLocation(item));
    }

}
