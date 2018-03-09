package com.example.miodragmilosevic.roomtest.settings.adjustattacklocations;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.settings.AddNewItemDialog;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsActivity;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsViewModel;

public class AdjustAttackLocationActivity extends BaseSettingsActivity<EpiAttackLocation>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText(R.string.title_settings_location);
        mViewModel = ViewModelProviders.of(this,
                new BaseSettingsViewModel.Factory(
                        new AdjustAttackLocationRepository(AppDataBase.get(getApplicationContext()).getEpiAttackResourceDao()),
                        new AdjustAttackLocationSettingsItemMapper(getApplicationContext())
                )
        )
                .get(BaseSettingsViewModel.class);
        mViewModel.getLiveData().observe(this, viewData ->
        {//TODO
            Log.i("Miki", "onCreate observe: " + viewData);
            mAdapter.setItems(viewData);
        });
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            mViewModel.onDeleteItemButtonClick(mAdapter.getItem(position));
        });
        mOnAddItemListener = item -> {
            Log.i("Miki","add new Item "+ item);
            mViewModel.onAddItemButtonClick(item);

        };
    }

    @Override
    protected AddNewItemDialog createAddNewItemDialog() {
        return AddNewItemDialog.newInstance(R.string.title_add_new_location_dialog,R.drawable.ic_alarm_add_40);
    }

    @Override
    protected int getListViewId() {
        return R.id.adjust_locations_list_view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adjust_attack_location;
    }

}
