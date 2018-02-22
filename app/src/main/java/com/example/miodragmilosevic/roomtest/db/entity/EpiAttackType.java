package com.example.miodragmilosevic.roomtest.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

/**
 * Created by miodrag.milosevic on 1/8/2018.
 */


@Entity(tableName = "attack_types")
public class EpiAttackType {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "attack_type")
    private String attackType;


    @ColumnInfo(name = "is_displayed")
    private boolean isDisplayed;

    @ColumnInfo(name = "is_default")
    private boolean isDefault;

    public EpiAttackType(String attackType, boolean isDisplayed, boolean isDefault) {
        this.attackType = attackType;
        this.isDisplayed = isDisplayed;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackType(String attackType, boolean isDefault) {
        this.attackType = attackType;
        this.isDisplayed = true;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackType(@Nullable String defaultType) {
        this.isDisplayed = true;
        this.isDefault = true;
        this.attackType = defaultType;

    }

    public int getId() {
        return id;
    }

    public String getAttackType() {
        return attackType;
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

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

}