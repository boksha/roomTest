package com.example.miodragmilosevic.roomtest.attackdetails;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public class AttackDetailsRepository {

    private EpiAttackDao mEpiAttackDao;

    public AttackDetailsRepository(EpiAttackDao dao) {
        mEpiAttackDao = dao;
    }

    public Single<EpiAttack> getAttackById(long id){
        return mEpiAttackDao.getAttackById(id);
    }

    public Completable deleteAttack(long attackId) {
        return Completable.fromAction(()->   mEpiAttackDao.deleteById(attackId));

    }
}
