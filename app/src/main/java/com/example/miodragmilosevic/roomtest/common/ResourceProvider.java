package com.example.miodragmilosevic.roomtest.common;

import android.content.Context;
import android.content.res.Resources;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackType;

/**
 * Created by miodrag.milosevic on 2/13/2018.
 */

public class ResourceProvider {

    private final Context mContext;
    private static ResourceProvider INSTANCE;

    private ResourceProvider(Context context){
        mContext = context.getApplicationContext();
    }

    public synchronized static ResourceProvider get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ResourceProvider(context);
        }
        return INSTANCE;
    }

    public EpiAttackType[] populateDefaultAttackTypes(){
        Resources resources = mContext.getResources();
        return  new EpiAttackType[]{
                (new EpiAttackType(resources.getResourceEntryName(R.string.attack_type_1))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_2)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_3)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_4)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_5)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_6)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_7)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_8)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_9)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_10)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_11)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_12)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_13)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_14)))),
                (new EpiAttackType((resources.getResourceEntryName(R.string.attack_type_15))))
        };
    }

    public EpiAttackLocation[] populateDefaultAttackLocations() {
        Resources resources = mContext.getResources();
        return  new EpiAttackLocation[]{
                (new EpiAttackLocation(resources.getResourceEntryName(R.string.attack_location_1))),
                (new EpiAttackLocation((resources.getResourceEntryName(R.string.attack_location_2)))),
                (new EpiAttackLocation((resources.getResourceEntryName(R.string.attack_location_3)))),
                (new EpiAttackLocation((resources.getResourceEntryName(R.string.attack_location_4)))),
                (new EpiAttackLocation((resources.getResourceEntryName(R.string.attack_location_5)))),
                (new EpiAttackLocation((resources.getResourceEntryName(R.string.attack_location_6)))),
        };
    }

    public EpiAttackActivity[] populateDefaultAttackActivities() {
        Resources resources = mContext.getResources();
        return  new EpiAttackActivity[]{
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_1))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_2))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_3))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_4))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_5))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_6))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_7))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_8))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_9))),
                (new EpiAttackActivity(resources.getResourceEntryName(R.string.attack_activity_10))),
        };
    }

    public EpiAttackCause[] populateDefaultAttackCauses() {
        Resources resources = mContext.getResources();
        return  new EpiAttackCause[]{
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_1))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_2))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_3))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_4))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_5))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_6))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_7))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_8))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_9))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_10))),
                (new EpiAttackCause(resources.getResourceEntryName(R.string.attack_cause_11))),
        };
    }
}
