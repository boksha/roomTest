package com.example.miodragmilosevic.roomtest.attacklist;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by miodrag.milosevic on 2/6/2018.
 */

public class AttackListRepository {

    private EpiAttackDao mEpiAttackDao;

    public AttackListRepository(EpiAttackDao dao) {
        mEpiAttackDao = dao;
    }


    public Observable<List<EpiAttackModel>> getEpiAttacks( ){
        return mEpiAttackDao.getAllAttacksJoined().toObservable();
    }

}
