package com.example.miodragmilosevic.roomtest.settings.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.settings.AddNewItemDialog;

/**
 * Created by miodrag.milosevic on 2/14/2018.
 */

public abstract class BaseSettingsActivity<T> extends BaseToolbarActivity {

    private static final String ADD_NEW_ITEM_TAG = "AddNewItemDialog";
    protected BaseSettingsAdapter mAdapter;
    protected ListView mListView;
    protected BaseSettingsViewModel<T> mViewModel;
    protected AddNewItemDialog.OnAddItemListener mOnAddItemListener;


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
                showAddNewItemDialog();
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
    protected void onResume() {
        super.onResume();
        AddNewItemDialog dialog = (AddNewItemDialog) getSupportFragmentManager().findFragmentByTag(ADD_NEW_ITEM_TAG);
        if(dialog  != null){
            Log.i("Miki", "onResume: " + mOnAddItemListener);
            dialog.setOnAddItemListener(mOnAddItemListener);
        }
    }
    protected void showAddNewItemDialog(){
        AddNewItemDialog addNewItemDialog = createAddNewItemDialog();
        addNewItemDialog.setOnAddItemListener(mOnAddItemListener);
        addNewItemDialog.show(getSupportFragmentManager(), ADD_NEW_ITEM_TAG);
    }

    protected abstract AddNewItemDialog createAddNewItemDialog();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    protected abstract int getListViewId();

}
