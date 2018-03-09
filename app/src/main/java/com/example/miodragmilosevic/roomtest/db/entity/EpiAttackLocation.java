package com.example.miodragmilosevic.roomtest.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

@Entity(tableName = "attack_locations",indices = {@Index(value = {"attack_location"},
        unique = true)})
public class EpiAttackLocation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "attack_location")
    private String attackLocation;


    @ColumnInfo(name = "is_displayed")
    private boolean isDisplayed;

    @ColumnInfo(name = "is_default")
    private boolean isDefault;

    public EpiAttackLocation(String attackLocation, boolean isDisplayed, boolean isDefault) {
        this.attackLocation = attackLocation;
        this.isDisplayed = isDisplayed;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackLocation(String attackLocation, boolean isDefault) {
        this.attackLocation = attackLocation;
        this.isDisplayed = true;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackLocation(@Nullable String defaultType) {
        this.isDisplayed = true;
        this.isDefault = true;
        this.attackLocation = defaultType;

    }

    public int getId() {
        return id;
    }

    public String getAttackLocation() {
        return attackLocation;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAttackLocation(String attackLocation) {
        this.attackLocation = attackLocation;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

}