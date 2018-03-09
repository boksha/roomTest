package com.example.miodragmilosevic.roomtest.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

/**
 * Created by miodrag.milosevic on 2/19/2018.
 */

@Entity(tableName = "attack_causes",indices = {@Index(value = {"attack_cause"},
        unique = true)})
public class EpiAttackCause {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "attack_cause")
    private String attackCause;


    @ColumnInfo(name = "is_displayed")
    private boolean isDisplayed;

    @ColumnInfo(name = "is_default")
    private boolean isDefault;

    public EpiAttackCause(String attackCause, boolean isDisplayed, boolean isDefault) {
        this.attackCause = attackCause;
        this.isDisplayed = isDisplayed;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackCause(String attackCause, boolean isDefault) {
        this.attackCause = attackCause;
        this.isDisplayed = true;
        this.isDefault = isDefault;
    }

    @Ignore
    public EpiAttackCause(@Nullable String defaultType) {
        this.isDisplayed = true;
        this.isDefault = true;
        this.attackCause = defaultType;

    }

    public int getId() {
        return id;
    }

    public String getAttackCause() {
        return attackCause;
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

    public void setAttackCause(String attackCause) {
        this.attackCause = attackCause;
    }

    public void setDisplayed(boolean displayed) {
        isDisplayed = displayed;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

}