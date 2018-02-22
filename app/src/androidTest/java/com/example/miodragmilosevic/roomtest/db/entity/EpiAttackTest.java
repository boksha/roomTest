package com.example.miodragmilosevic.roomtest.db.entity;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.miodragmilosevic.roomtest.db.AppDataBase;
import com.example.miodragmilosevic.roomtest.db.dao.EpiAttackDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by miodrag.milosevic on 2/6/2018.
 */
@RunWith(AndroidJUnit4.class)
public class EpiAttackTest {
    private EpiAttackDao mEpiAttackDao;
    private AppDataBase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).build();
        mEpiAttackDao = mDb.getEpiAttackDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeEpiAttackAndReadInList() throws Exception {
        EpiAttack attack = new EpiAttack();
//        user.setName("george");
//        mEpiAttackDao.insert(user);
//        List<User> byName = mEpiAttackDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
    }
}
