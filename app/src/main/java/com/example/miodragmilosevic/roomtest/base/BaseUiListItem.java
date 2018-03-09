package com.example.miodragmilosevic.roomtest.base;

/**
 * Created by miodrag.milosevic on 2/26/2018.
 */

public class BaseUiListItem {
    private long mId;
    private String mName;

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public BaseUiListItem(long id, String name) {
        this.mId = id;
        this.mName = name;
    }
}
