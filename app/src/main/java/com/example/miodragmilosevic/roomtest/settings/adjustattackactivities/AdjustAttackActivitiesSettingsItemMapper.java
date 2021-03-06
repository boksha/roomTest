package com.example.miodragmilosevic.roomtest.settings.adjustattackactivities;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItemMapper;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

class AdjustAttackActivitiesSettingsItemMapper extends BaseSettingsItemMapper<EpiAttackActivity> {
    public AdjustAttackActivitiesSettingsItemMapper(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public BaseSettingsItem map(EpiAttackActivity item) {
        String name = item.getAttackActivity();
        if (item.isDefault()) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        return new BaseSettingsItem(item.getId(), name);
    }

    @Override
    public EpiAttackActivity reverseMap(BaseSettingsItem item) {
        return new EpiAttackActivity(item.getName(), false);
    }
}
