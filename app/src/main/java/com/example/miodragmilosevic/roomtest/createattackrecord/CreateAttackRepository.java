package com.example.miodragmilosevic.roomtest.createattackrecord;

import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackDao;
import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackResourceDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by miodrag.milosevic on 2/5/2018.
 */

public class CreateAttackRepository {

    private EpiAttackDao mEpiAttackDao;
    private EpiAttackResourceDao mEpiAttackResourceDao;

    public CreateAttackRepository(EpiAttackDao dao,EpiAttackResourceDao resourceDao) {
        mEpiAttackDao = dao;
        mEpiAttackResourceDao = resourceDao;
    }


    public Completable saveEpyAttack(EpiAttack epiAttack){
        return Completable.fromAction(()-> mEpiAttackDao.insert(epiAttack));
    }

    public Observable<List<EpiAttackActivity>> getAttackActivities(){
        return mEpiAttackResourceDao.getAllActivitiesToDisplay().toObservable();
    }

    public Observable<List<EpiAttackType>> getAttackTypes(){
        return mEpiAttackResourceDao.getAllAttackTypesToDisplay().toObservable();
    }
    public Observable<List<EpiAttackCause>> getAttackCauses(){
        return mEpiAttackResourceDao.getAllCausesToDisplay().toObservable();
    }
    public Observable<List<EpiAttackLocation>> getAttackLocations(){
        return mEpiAttackResourceDao.getAllLocationsToDisplay().toObservable();
    }

}
