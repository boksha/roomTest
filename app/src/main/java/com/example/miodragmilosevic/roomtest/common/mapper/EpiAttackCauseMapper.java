package com.example.miodragmilosevic.roomtest.common.mapper;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.base.BaseUiListItem;
import com.example.miodragmilosevic.roomtest.base.BaseUiListItemMapper;
import com.example.miodragmilosevic.roomtest.base.ToMapper;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;

/**
 * Created by miodrag.milosevic on 2/26/2018.
 */

public class EpiAttackCauseMapper extends BaseUiListItemMapper<EpiAttackCause> {

    public EpiAttackCauseMapper(Context c) {
        super(c);
    }

    @Override
    public BaseUiListItem map(EpiAttackCause item) {
        String name = item.getAttackCause();
        if (item.isDefault()) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        return new BaseUiListItem(item.getId(), name);
    }

    @Override
    public EpiAttackCause reverseMap(BaseUiListItem item) {
        return null;
    }
}