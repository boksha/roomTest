package com.example.miodragmilosevic.roomtest.settings.adjustattacklocations;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItemMapper;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

class AdjustAttackLocationItemMapper extends BaseSettingsItemMapper<EpiAttackLocation> {
    public AdjustAttackLocationItemMapper(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public BaseSettingsItem map(EpiAttackLocation item) {
        String name = item.getAttackLocation();
        if (item.isDefault()) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        return new BaseSettingsItem(item.getId(), name);
    }
}
