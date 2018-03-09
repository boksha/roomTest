package com.example.miodragmilosevic.roomtest.attacklist;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.base.Mapper;
import com.example.miodragmilosevic.roomtest.base.ToMapper;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttack;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public class AttackItemMapper extends ToMapper<AttackListItemUiModel,EpiAttackModel> {

    protected final Context mApplicationContext;
    protected AttackItemMapper(Context c) {
        mApplicationContext = c.getApplicationContext();
    }

    @Override
    public AttackListItemUiModel map(EpiAttackModel item) {
        AttackListItemUiModel result  = new AttackListItemUiModel();
        result.setId(item.getId());
        result.setStartTime(item.getStartTime());
        String name  = item.getAttackType();
        if (name.contains("attack_type")) {
            int resId = mApplicationContext.getResources().getIdentifier(name, "string", mApplicationContext.getPackageName());
            name = mApplicationContext.getString(resId);
        }
        result.setAttackTitle(name);
        result.setElapsedTime(getFormattedElapsedTime(item.getElapsedTime()));
        result.setDayOfMonth(getDayOfMonth(item.getStartTime()));
        result.setDayOfWeek(getFormattedDayOfWeek(item.getStartTime()));
        return result;
    }

    private  String getFormattedElapsedTime(long elapsedTime) {
        if (elapsedTime >=0) {
            Calendar c=Calendar.getInstance();
            c.setTimeInMillis(elapsedTime);
            int min = c.get(Calendar.MINUTE);
            int sec = c.get(Calendar.SECOND);
            return String.format("%02d.%02d min",min,sec);
        } else return "";
    }

    private  String getFormattedDayOfWeek(long startTime) {
        if (startTime >=0) {
            Locale serbiaLocale = serbianLatinLocale();
            String pattern = "EEE";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, serbiaLocale);
            String result =  simpleDateFormat.format(startTime);
            return result.substring(0,1).toUpperCase() + result.substring(1);
        } else return "";
    }
    private  long getDayOfMonth(long startTime) {
        if (startTime >=0) {
            Calendar c=Calendar.getInstance();
            c.setTimeInMillis(startTime);
            return c.get(Calendar.DAY_OF_MONTH);
        } else return -1;
    }

    private Locale serbianLatinLocale(){
        //move to util
        Locale locale = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            for (Locale checkLocale : Locale.getAvailableLocales()) {
                if (checkLocale.getISO3Language().equals("srp") && checkLocale.getCountry().equals("LATN") && checkLocale.getVariant().equals("")) {
                    locale = checkLocale;
                }
            }
        } else {
            locale = new Locale.Builder().setLanguage("sr").setRegion("RS").setScript("Latn").build();
        }
        return locale;
    }
}
