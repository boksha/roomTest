package com.example.miodragmilosevic.roomtest.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by miodrag.milosevic on 1/25/2018.
 */
@Entity(tableName = "epi_attacks")
public class EpiAttack {

    @PrimaryKey(autoGenerate = true)
    public long id;
    //        @ColumnInfo(name = "type")
    public long startTime = -1;
    public long elapsedTime = -1;
    //change this to res  ids when decide what to do with it
    public String attackLocation;
    public String medicament;
    public String activity;
    public String attackType;

    public String description;

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

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


//might need getter and setter instead of public

}
