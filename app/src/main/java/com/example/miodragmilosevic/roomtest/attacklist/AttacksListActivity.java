package com.example.miodragmilosevic.roomtest.attacklist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.attackdetails.AttackDetailsActivity;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.createattackrecord.CreateAttackRecordActivity;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;
import com.example.miodragmilosevic.roomtest.startattack.StartAttackFragment;
import com.example.miodragmilosevic.roomtest.startattack.StartAttackRepository;
import com.example.miodragmilosevic.roomtest.startattack.StartAttackUiModel;
import com.example.miodragmilosevic.roomtest.startattack.StartAttackViewModel;

/**
 * Created by miodrag.milosevic on 2/21/2018.
 */

public class AttacksListActivity extends BaseToolbarActivity {


    private static final String TAG = "Miki";
    private AttackListAdapter mAttackAdapter;
    private AttackListViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText(R.string.title_attack_list);
        initAttackListRecyclerView();
        mViewModel = ViewModelProviders.of(this,
                new AttackListViewModel.Factory(
                        new AttackListRepository(AppDataBase.get(getApplicationContext()).getEpiAttackDao()),
                        new AttackItemMapper()))
                .get(AttackListViewModel.class);
        mViewModel.getLiveData().observe(this, viewData ->
        {//TODO
            Log.i(TAG, "onCreate observe: " + viewData);
            mAttackAdapter.setAttackItems(viewData);
        });
        Button addRecord = findViewById(R.id.btn_add_epy_attack);
        addRecord.setOnClickListener(view -> {
            mViewModel.onAddButtonClick();
        });
    }


    private void initAttackListRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAttackAdapter = new AttackListAdapter();
        mAttackAdapter.setOnItemClickListener((long attackId) -> {
            Log.i("Miki", "initAttackListRecyclerView: " + attackId);
            Intent intent = new Intent(this, AttackDetailsActivity.class);
            intent.putExtra(AttackDetailsActivity.EXTRA_ATTACK_ID, attackId);
            startActivity(intent);
        });
        RecyclerView reviewsRecyclerView = findViewById(R.id.recycler_attack_list);
        reviewsRecyclerView.setLayoutManager(layoutManager);
        reviewsRecyclerView.setAdapter(mAttackAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_attack_list;
    }

}
