package com.example.miodragmilosevic.roomtest.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by miodrag.milosevic on 1/8/2018.
 */

@Dao
public interface EpiAttackResourceDao {
    @Query("SELECT * FROM attack_types")
    Single<List<EpiAttackType>> getAllAttackTypes();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertType(EpiAttackType... attackType);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertLocation(EpiAttackLocation... attackLocation);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertActivity(EpiAttackActivity... attackActivity);

    @Insert(onConflict = OnConflictStrategy.ABORT )
    void insertCause(EpiAttackCause... attackCause);
    @Update
    void update(EpiAttackType... attackType);

    @Delete
    void delete(EpiAttackType... attackType);

    @Query("SELECT * FROM attack_types WHERE is_displayed = 1")
    Flowable<List<EpiAttackType>> getAllAttackTypesToDisplay();

    @Query("UPDATE attack_types SET is_displayed= :isDisplayed WHERE id = :id" )
    void updateAttackTypeDisplayListById(long id, boolean isDisplayed);

    @Query("UPDATE attack_types SET is_displayed = is_default")
    void resetAttackTypesToDefault();

    @Query("SELECT * FROM attack_locations WHERE is_displayed = 1")
    Flowable<List<EpiAttackLocation>> getAllLocationsToDisplay();

    @Query("UPDATE attack_locations SET is_displayed= :isDisplayed WHERE id = :id" )
    void updateAttackLocationDisplayListById(long id, boolean isDisplayed);

    @Query("UPDATE attack_locations SET is_displayed = is_default")
    void resetAttackLocationToDefault();

    @Query("SELECT * FROM attack_activities WHERE is_displayed = 1")
    Flowable<List<EpiAttackActivity>> getAllActivitiesToDisplay();

    @Query("UPDATE attack_activities SET is_displayed= :isDisplayed WHERE id = :id" )
    void updateAttackActivitiesDisplayListById(long id, boolean isDisplayed);

    @Query("UPDATE attack_activities SET is_displayed = is_default")
    void resetAttackActivitiesToDefault();

    @Query("SELECT * FROM attack_causes WHERE is_displayed = 1")
    Flowable<List<EpiAttackCause>> getAllCausesToDisplay();

    @Query("UPDATE attack_causes SET is_displayed= :isDisplayed WHERE id = :id" )
    void updateAttackCausesDisplayListById(long id, boolean isDisplayed);

    @Query("UPDATE attack_causes SET is_displayed = is_default")
    void resetAttackCausesToDefault();
    /**
     *
     *


     // Gets all people in the database with a favorite color
     @Query("SELECT * FROM person WHERE favoriteColor LIKE :color")
     List<Person> getAllPeopleWithFavoriteColor(String color);


     @Query("SELECT * FROM user WHERE uid IN (:userIds)")
     List<User> loadAllByIds(int[] userIds);

     @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
     + "last_name LIKE :last LIMIT 1")
     User findByName(String first, String last);


     */
}
