package com.example.miodragmilosevic.roomtest.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by miodrag.milosevic on 3/1/2018.
 */

public class EpiAttackModel {

    private long id;
    @ColumnInfo(name = "start_time")
    private long startTime = -1;
    @ColumnInfo(name = "elapsed_time")
    private long elapsedTime = -1;
    //change this to res  ids when decide what to do with it
    @ColumnInfo(name = "attack_location")
    private String attackLocation;
    private String medicament;
    @ColumnInfo(name = "activity")
    private String activity;
    @ColumnInfo(name = "attack_type")
    private String attackType;

    @ColumnInfo(name = "attack_cause")
    private String attackCause;

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

    public String getAttackLocation() {
        return attackLocation;
    }

    public void setAttackLocation(String attackLocation) {
        this.attackLocation = attackLocation;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getDescription() {
        return description;
    }

    public String getAttackCause() {
        return attackCause;
    }

    public void setAttackCause(String attackCause) {
        this.attackCause = attackCause;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
