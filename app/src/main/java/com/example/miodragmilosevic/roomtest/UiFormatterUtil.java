package com.example.miodragmilosevic.roomtest;

import java.util.Calendar;

/**
 * Created by miodrag.milosevic on 2/23/2018.
 */

public class UiFormatterUtil {

    public static String getFormattedElapsedTime(long mElapsedTime) {
        if (mElapsedTime >=0) {
            Calendar c=Calendar.getInstance();
            c.setTimeInMillis(mElapsedTime);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            return String.format("%02d.%02d",min,sec);
        } else return "";

    }
}
