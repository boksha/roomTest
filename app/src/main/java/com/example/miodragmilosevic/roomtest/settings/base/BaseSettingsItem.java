package com.example.miodragmilosevic.roomtest.settings.base;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public class BaseSettingsItem {

    private long mId;
    private String mName;

    public BaseSettingsItem(long id, String name) {
        mId = id;
        mName = name;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
