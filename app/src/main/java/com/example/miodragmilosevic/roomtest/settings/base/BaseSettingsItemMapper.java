package com.example.miodragmilosevic.roomtest.settings.base;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.base.Mapper;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public abstract class BaseSettingsItemMapper<T> extends Mapper<BaseSettingsItem, T> {

    protected final Context mApplicationContext;

    protected BaseSettingsItemMapper(Context c) {
        mApplicationContext = c.getApplicationContext();
    }


    @Override
    public abstract BaseSettingsItem map(T type);

    @Override
    public abstract T reverseMap(BaseSettingsItem item);
}
