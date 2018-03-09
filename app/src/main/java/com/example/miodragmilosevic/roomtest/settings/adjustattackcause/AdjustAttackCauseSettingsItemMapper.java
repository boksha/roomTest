package com.example.miodragmilosevic.roomtest.settings.adjustattackcause;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItemMapper;

/**
 * Created by miodrag.milosevic on 2/19/2018.
 */

public class AdjustAttackCauseSettingsItemMapper extends BaseSettingsItemMapper<EpiAttackCause> {
    public AdjustAttackCauseSettingsItemMapper(Context applicationContext) {
        super(applicationContext);
    }

    @Override
    public BaseSettingsItem map(EpiAttackCause item) {
        String name = item.getAttackCause();
        if (item.isDefault()) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        return new BaseSettingsItem(item.getId(), name);
    }

    @Override
    public EpiAttackCause reverseMap(BaseSettingsItem item) {
        return new EpiAttackCause(item.getName(), false);

    }
}
