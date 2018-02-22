package com.example.miodragmilosevic.roomtest.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by miodrag.milosevic on 2/5/2018.
 */
@Dao
public interface EpiAttackDao {

        @Query("SELECT * FROM epi_attacks")
        Flowable<List<EpiAttack>> getAllAttacks();

        @Query("SELECT * FROM epi_attacks WHERE id = :id")
        Single<EpiAttack> getAttackById(long id);

        // Adds attack to the database
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(EpiAttack... attack);
        // updates attack from the database
        @Update
        void update(EpiAttack... attack);
        // Removes attacks from the database
        @Delete
        void delete(EpiAttack... attack);

        @Query("DELETE FROM epi_attacks WHERE id = :id")
        int deleteById(final long id);

        @Query("DELETE FROM epi_attacks")
        void deleteAll();
}
