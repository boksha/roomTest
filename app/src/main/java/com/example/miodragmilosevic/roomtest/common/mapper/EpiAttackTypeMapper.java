package com.example.miodragmilosevic.roomtest.common.mapper;

import android.content.Context;

import com.example.miodragmilosevic.roomtest.base.BaseUiListItem;
import com.example.miodragmilosevic.roomtest.base.BaseUiListItemMapper;
import com.example.miodragmilosevic.roomtest.base.ToMapper;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

/**
 * Created by miodrag.milosevic on 2/26/2018.
 */

public class EpiAttackTypeMapper extends BaseUiListItemMapper<EpiAttackType> {

    public EpiAttackTypeMapper(Context c) {
        super(c);
    }

    @Override
    public BaseUiListItem map(EpiAttackType item) {
        String name = item.getAttackType();
        if (item.isDefault()) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        return new BaseUiListItem(item.getId(), name);
    }

    @Override
    public EpiAttackType reverseMap(BaseUiListItem item) {
        return null;
    }
}
