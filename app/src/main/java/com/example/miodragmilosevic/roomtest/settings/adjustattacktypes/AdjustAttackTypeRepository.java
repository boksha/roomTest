package com.example.miodragmilosevic.roomtest.settings.adjustattacktypes;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackResourceDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public class AdjustAttackTypeRepository extends BaseSettingsRepository<EpiAttackType> {

    protected AdjustAttackTypeRepository(EpiAttackResourceDao epiAttackResourceDao) {
        super(epiAttackResourceDao);
    }

    @Override
    public Observable<List<EpiAttackType>> getAllItemsToDisplay() {
        return mEpiAttackResourceDao.getAllAttackTypesToDisplay().toObservable();

    }

    @Override
    public Completable removeFromDisplayList(long id) {
         return Completable.fromAction(()->mEpiAttackResourceDao.updateAttackTypeDisplayListById(id,false));
    }

    @Override
    public Completable resetToDefault() {
        return Completable.fromAction(()->mEpiAttackResourceDao.resetAttackTypesToDefault());
    }

    @Override
    public Completable addNewItem(EpiAttackType item) {
        return Completable.fromAction(()->mEpiAttackResourceDao.insertType(item));

    }
}
