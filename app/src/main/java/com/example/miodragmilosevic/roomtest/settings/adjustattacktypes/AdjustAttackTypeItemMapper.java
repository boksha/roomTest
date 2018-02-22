package com.example.miodragmilosevic.roomtest.settings.adjustattacktypes;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItemMapper;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public class AdjustAttackTypeItemMapper extends BaseSettingsItemMapper<EpiAttackType> {

    public AdjustAttackTypeItemMapper(Context c) {
        super(c);
    }

    @Override
    public BaseSettingsItem map(EpiAttackType item) {
        String name = item.getAttackType();
        if (item.isDefault()) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        return new BaseSettingsItem(item.getId(), name);
    }

}
