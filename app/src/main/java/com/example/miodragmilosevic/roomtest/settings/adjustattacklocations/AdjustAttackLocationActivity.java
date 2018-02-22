package com.example.miodragmilosevic.roomtest.settings.adjustattacklocations;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackLocation;
import com.example.miodragmilosevic.roomtest.settings.adjustattacktypes.AdjustAttackTypeRepository;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsActivity;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItemMapper;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsViewModel;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsAdapter;

public class AdjustAttackLocationActivity extends BaseSettingsActivity<EpiAttackLocation>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText(R.string.title_settings_location);
        mViewModel = ViewModelProviders.of(this,
                new BaseSettingsViewModel.Factory(
                        new AdjustAttackLocationRepository(AppDataBase.get(getApplicationContext()).getEpiAttackResourceDao()),
                        new AdjustAttackLocationItemMapper(getApplicationContext())
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
