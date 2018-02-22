package com.example.miodragmilosevic.roomtest.settings.adjustattackcause;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;
import com.example.miodragmilosevic.roomtest.db.entity.EpiAttackCause;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsActivity;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsViewModel;

/**
 * Created by miodrag.milosevic on 2/19/2018.
 */

public class AdjustAttackCauseActivity extends BaseSettingsActivity<EpiAttackCause> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText(R.string.title_settings_possible_cause);
        mViewModel = ViewModelProviders.of(this,
                new BaseSettingsViewModel.Factory(
                        new AdjustAttackCauseRepository(AppDataBase.get(getApplicationContext()).getEpiAttackResourceDao()),
                        new AdjustAttackCausesItemMapper(getApplicationContext())
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
        return R.id.adjust_causes_list_view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adjust_attack_causes;
    }

}
