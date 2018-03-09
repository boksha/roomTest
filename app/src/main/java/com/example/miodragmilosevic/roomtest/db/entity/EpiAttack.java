package com.example.miodragmilosevic.roomtest.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by miodrag.milosevic on 1/25/2018.
 */

@Entity(tableName = "epi_attacks",foreignKeys = {@ForeignKey(entity = EpiAttackActivity.class,
        parentColumns = "id",
        childColumns = "activity_id"),
        @ForeignKey(entity = EpiAttackType.class,
                parentColumns = "id",
                childColumns = "attack_type_id"),
        @ForeignKey(entity = EpiAttackCause.class,
                parentColumns = "id",
                childColumns = "attack_cause_id"),
        @ForeignKey(entity = EpiAttackLocation.class,
                parentColumns = "id",
                childColumns = "attack_location_id")})
public class EpiAttack {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "start_time")
    private long startTime = -1;
    @ColumnInfo(name = "elapsed_time")
    private long elapsedTime = -1;
    //change this to res  ids when decide what to do with it
    @ColumnInfo(name = "attack_location_id")
    private long attackLocationId;
    private String medicament;
    @ColumnInfo(name = "activity_id")
    private long activityId;
    @ColumnInfo(name = "attack_type_id")
    private long attackTypeId;

    @ColumnInfo(name = "attack_cause_id")
    private long attackCauseId;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getAttackLocationId() {
        return attackLocationId;
    }

    public void setAttackLocationId(long attackLocationId) {
        this.attackLocationId = attackLocationId;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public long getAttackTypeId() {
        return attackTypeId;
    }

    public void setAttackTypeId(long attackTypeId) {
        this.attackTypeId = attackTypeId;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getDescription() {
        return description;
    }

    public long getAttackCauseId() {
        return attackCauseId;
    }

    public void setAttackCauseId(long attackCauseId) {
        this.attackCauseId = attackCauseId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
