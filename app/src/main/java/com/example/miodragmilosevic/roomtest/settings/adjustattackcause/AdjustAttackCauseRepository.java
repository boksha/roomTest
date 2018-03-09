package com.example.miodragmilosevic.roomtest.settings.adjustattackcause;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackResourceDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by miodrag.milosevic on 2/19/2018.
 */

public class AdjustAttackCauseRepository extends BaseSettingsRepository<EpiAttackCause> {

    public AdjustAttackCauseRepository(EpiAttackResourceDao epiAttackResourceDao) {
        super(epiAttackResourceDao);
    }

    @Override
    public Observable<List<EpiAttackCause>> getAllItemsToDisplay() {
        return mEpiAttackResourceDao.getAllCausesToDisplay().toObservable();
    }

    @Override
    public Completable removeFromDisplayList(long id) {
        return Completable.fromAction(() -> mEpiAttackResourceDao.updateAttackCausesDisplayListById(id, false));
    }

    @Override
    public Completable resetToDefault() {
        return Completable.fromAction(() -> mEpiAttackResourceDao.resetAttackCausesToDefault());
    }

    @Override
    public Completable addNewItem(EpiAttackCause item) {
        return Completable.fromAction(()->mEpiAttackResourceDao.insertCause(item));
    }
}
