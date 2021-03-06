package com.example.miodragmilosevic.roomtest.settings.adjustattackactivities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackActivity;
import com.example.miodragmilosevic.roomtest.settings.AddNewItemDialog;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsActivity;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsViewModel;

public class AdjustAttackActivitiesActivity extends BaseSettingsActivity<EpiAttackActivity> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText(R.string.title_settings_activities);
        mViewModel = ViewModelProviders.of(this,
                new BaseSettingsViewModel.Factory(
                        new AdjustAttackActivitiesRepository(AppDataBase.get(getApplicationContext()).getEpiAttackResourceDao()),
                        new AdjustAttackActivitiesSettingsItemMapper(getApplicationContext())
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
        return AddNewItemDialog.newInstance(R.string.title_add_new_acttivity_dialog,R.drawable.ic_alarm_add_40);
    }

    @Override
    protected int getListViewId() {
        return R.id.adjust_activities_list_view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adjust_attack_activities;
    }

}
