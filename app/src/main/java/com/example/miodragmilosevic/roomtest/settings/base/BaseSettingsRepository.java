package com.example.miodragmilosevic.roomtest.settings.base;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackResourceDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by miodrag.milosevic on 2/13/2018.
 */

public abstract class BaseSettingsRepository<T> {

    protected EpiAttackResourceDao mEpiAttackResourceDao;

    protected BaseSettingsRepository(EpiAttackResourceDao epiAttackResourceDao) {
        mEpiAttackResourceDao = epiAttackResourceDao;
    }

    public abstract Observable<List<T>> getAllItemsToDisplay() ;

    public abstract Completable removeFromDisplayList(long id);

    public abstract Completable resetToDefault();
}
