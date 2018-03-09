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

@Entity(tableName = "attack_activities",indices = {@Index(value = {"attack_activity"},
        unique = true)})
public class EpiAttackActivity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "attack_activity")
    private String attackActivity;


    @ColumnInfo(name = "is_displayed")
    private boolean isDisplayed;

    @ColumnInfo(name = "is_default")
    private boolean isDefault;

    public EpiAttackActivity(String attackActivity, boolean isDisplayed, boolean isDefault) {
        this.attackActivity = attackActivity;
        this.isDisplayed = isDisplayed;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackActivity(String attackActivity, boolean isDefault) {
        this.attackActivity = attackActivity;
        this.isDisplayed = true;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackActivity(@Nullable String defaultType) {
        this.isDisplayed = true;
        this.isDefault = true;
        this.attackActivity = defaultType;

    }

    public int getId() {
        return id;
    }

    public String getAttackActivity() {
        return attackActivity;
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

    public void setAttackActivity(String attackActivity) {
        this.attackActivity = attackActivity;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

}

