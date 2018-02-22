package com.example.miodragmilosevic.roomtest.attacklist;

import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.Mapper;
import com.example.miodragmilosevic.roomtest.base.ToMapper;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;

import java.util.Calendar;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public class AttackItemMapper extends ToMapper<AttackListItemUiModel,EpiAttack> {

    @Override
    public AttackListItemUiModel map(EpiAttack input) {
        AttackListItemUiModel result  = new AttackListItemUiModel();
        result.setId(input.getId());
        result.setStartTime(input.getStartTime());
        result.setAttackTitle(input.getAttackType());
        result.setElapsedTime(getFormattedElapsedTime(input.getElapsedTime()));
        return result;
    }

    private  String getFormattedElapsedTime(long elapsedTime) {
        if (elapsedTime >=0) {
            Calendar c=Calendar.getInstance();
            c.setTimeInMillis(elapsedTime);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            return String.format("%02d:%02d min",min,sec);
        } else return "";
    }
}
