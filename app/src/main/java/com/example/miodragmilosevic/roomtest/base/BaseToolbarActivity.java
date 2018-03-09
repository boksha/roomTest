package com.example.miodragmilosevic.roomtest.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.miodragmilosevic.roomtest.R;

/**
 * Created by miodrag.milosevic on 2/20/2018.
 */

public abstract class BaseToolbarActivity extends AppCompatActivity {
    protected TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        // enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar mTopToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = findViewById(R.id.toolbar_title);
    }

    //add this if slide entry exit transitions are needed
    //    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.slide_in_left_to_right,R.anim.slide_out_left_to_right);
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_in_right_to_left,R.anim.slide_out_right_to_left);
//    }

    protected abstract int getLayoutId();
}
