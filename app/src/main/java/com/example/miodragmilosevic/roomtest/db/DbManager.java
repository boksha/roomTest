package com.example.miodragmilosevic.roomtest.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by miodrag.milosevic on 1/8/2018.
 */
//no need for this Miki!!!!
public class DbManager {
    private static DbManager sInstance;
    private AppDataBase db;

    private DbManager(Context c) {
        db = Room.databaseBuilder(c.getApplicationContext(),
                AppDataBase.class, "epilepsi-tracker").build();
        }

    public static DbManager getInstance(Context c) {
        if (sInstance == null) {
            sInstance = new DbManager(c);
        }
        return sInstance;
    }

    AppDataBase getdataBase(){
        return db;
    }
}
