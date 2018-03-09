package com.example.miodragmilosevic.roomtest.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.miodragmilosevic.roomtest.common.ResourceProvider;
import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackDao;
import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackResourceDao;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

import java.util.concurrent.Executors;

/**
 * Created by miodrag.milosevic on 1/8/2018.
 */


@Database(entities = {EpiAttackType.class, EpiAttack.class, EpiAttackLocation.class, EpiAttackActivity.class, EpiAttackCause.class
        /*, AnotherEntityType.class, AThirdEntityType.class */}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DB_NAME = "epilepsy_tracker.db";

    public abstract EpiAttackResourceDao getEpiAttackResourceDao();

    public abstract EpiAttackDao getEpiAttackDao();

    private static AppDataBase sInstance;

    public synchronized static AppDataBase get(Context context) {
        if (sInstance == null) {
            create(context);
        }
        return sInstance;
    }

    private static void create(Context context) {
        sInstance = Room.databaseBuilder(context.getApplicationContext(),
                AppDataBase.class, DB_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor()
                        .execute(() -> {
                            get(context).getEpiAttackResourceDao().insertType(ResourceProvider.get(context).populateDefaultAttackTypes());
                            get(context).getEpiAttackResourceDao().insertLocation(ResourceProvider.get(context).populateDefaultAttackLocations());
                            get(context).getEpiAttackResourceDao().insertActivity(ResourceProvider.get(context).populateDefaultAttackActivities());
                            get(context).getEpiAttackResourceDao().insertCause(ResourceProvider.get(context).populateDefaultAttackCauses());
                        });
            }
        }).build();
    }
}
