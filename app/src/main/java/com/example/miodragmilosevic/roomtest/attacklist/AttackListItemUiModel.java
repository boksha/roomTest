package com.example.miodragmilosevic.roomtest.attacklist;

import android.arch.persistence.room.PrimaryKey;

/**
 * Created by miodrag.milosevic on 2/6/2018.
 */

public class AttackListItemUiModel {
    private long id;
    private long startTime = -1;

    public long getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(long dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    private long dayOfMonth = -1;
    private String elapsedTime;
    private String dayOfWeek;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

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

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getAttackTitle() {
        return attackTitle;
    }

    public void setAttackTitle(String attackTitle) {
        this.attackTitle = attackTitle;
    }

    private String attackTitle;

}
