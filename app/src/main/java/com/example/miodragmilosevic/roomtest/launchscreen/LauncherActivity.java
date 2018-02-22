package com.example.miodragmilosevic.roomtest.launchscreen;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.mainscreen.MainActivity;
import com.example.miodragmilosevic.roomtest.registration.RegistrationActivity;

public class LauncherActivity extends AppCompatActivity {

    public static final int DELAY_MILLIS = 2000;
    Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mNextScreenRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        if (true){
            mNextScreenRunnable = () -> {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        } else {
            mNextScreenRunnable = () -> {
                Intent intent = new Intent(LauncherActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            };
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mNextScreenRunnable, DELAY_MILLIS);
    }

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(mNextScreenRunnable);
        super.onPause();
    }
}
