package com.example.miodragmilosevic.roomtest.settings.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public abstract class BaseSettingsActivity<T> extends BaseToolbarActivity {

    protected BaseSettingsAdapter mAdapter;
    protected ListView mListView;
    protected BaseSettingsViewModel<T> mViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = findViewById(getListViewId());
        mAdapter = new BaseSettingsAdapter();
        mListView.setAdapter(mAdapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new:
                return true;

            case R.id.action_reset:
                mViewModel.onResetMenuItemClick();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    protected abstract int getListViewId();

}
