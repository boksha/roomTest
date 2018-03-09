package com.example.miodragmilosevic.roomtest.common.mapper;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.base.BaseUiListItem;
import com.example.miodragmilosevic.roomtest.base.BaseUiListItemMapper;
import com.example.miodragmilosevic.roomtest.base.ToMapper;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;

/**
 * Created by miodrag.milosevic on 2/26/2018.
 */

public class EpiAttackLocationMapper extends BaseUiListItemMapper<EpiAttackLocation> {

    public EpiAttackLocationMapper(Context c) {
        super(c);
    }

    @Override
    public BaseUiListItem map(EpiAttackLocation item) {
        String name = item.getAttackLocation();
        if (item.isDefault()) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        return new BaseUiListItem(item.getId(), name);
    }

    @Override
    public EpiAttackLocation reverseMap(BaseUiListItem item) {
        return null;
    }
}
