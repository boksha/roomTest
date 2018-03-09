package com.example.miodragmilosevic.roomtest.base;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;

/**
 * Created by miodrag.milosevic on 2/26/2018.
 */

public abstract class BaseUiListItemMapper<T> extends Mapper<BaseUiListItem, T> {

    protected final Context mApplicationContext;

    protected BaseUiListItemMapper(Context c) {
        mApplicationContext = c.getApplicationContext();
    }


    @Override
    public abstract BaseUiListItem map(T type);

    @Override
    public abstract T reverseMap(BaseUiListItem item);
}

