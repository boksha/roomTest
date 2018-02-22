package com.example.miodragmilosevic.roomtest.settings.base;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.base.ToMapper;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public abstract class BaseSettingsItemMapper<T> extends ToMapper<BaseSettingsItem, T> {

    protected final Context mApplicationContext;

    protected BaseSettingsItemMapper(Context c) {
        mApplicationContext = c.getApplicationContext();
    }


    @Override
    public abstract BaseSettingsItem map(T type);
}
