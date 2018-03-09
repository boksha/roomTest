package com.example.miodragmilosevic.roomtest.startattack;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.util.Calendar;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by miodrag.milosevic on 1/25/2018.
 */

public class StartAttackUiModel {

    private int mState;
    private long mElapsedTime;
    private long mStartTime;

    @Retention(SOURCE)
    @IntDef({INACTIVE, STARTED, IN_PROCESSING,ERROR,DELETE_COMPLETED})
    public @interface State {}
    public static final int INACTIVE = 0;
    public static final int STARTED = 1;
    public static final int IN_PROCESSING = 2;
    public static final int ERROR = 3;
    public static final int DELETE_COMPLETED = 4;

    public StartAttackUiModel(int state, long startTime, long elapsedTime) {
        this.mState = state;
        this.mElapsedTime = elapsedTime;
        this.mStartTime = startTime;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }

    public long getElapsedTime() {
        return mElapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        mElapsedTime = elapsedTime;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public void setStartTime(long startTime) {
        mStartTime = startTime;
    }


    public String getFormattedElapsedTime() {
        if (mElapsedTime >=0) {
            Calendar c=Calendar.getInstance();
            c.setTimeInMillis(mElapsedTime);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            return String.format("%02d.%02d",min,sec);
        } else return "";

    }

}
