package com.example.miodragmilosevic.roomtest.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.settings.adjustattackactivities.AdjustAttackActivitiesActivity;
import com.example.miodragmilosevic.roomtest.settings.adjustattackcause.AdjustAttackCauseActivity;
import com.example.miodragmilosevic.roomtest.settings.adjustattacklocations.AdjustAttackLocationActivity;
import com.example.miodragmilosevic.roomtest.settings.adjustattacktypes.AdjustAttackTypesActivity;

public class SettingsListActivity extends BaseToolbarActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings_list);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView = (ListView) findViewById(R.id.settings_list_view);
        String[] listItems= getResources().getStringArray(
                R.array.settings_list_tems);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent;
            String item = adapter.getItem(position);
             if (item.equalsIgnoreCase(getString(R.string.drawer_item_adjust_attack_types))){
                intent = new Intent(this,AdjustAttackTypesActivity.class);
            } else if (item.equalsIgnoreCase(getString(R.string.drawer_item_adjust_attack_locations))){
                intent = new Intent(this,AdjustAttackLocationActivity.class);
            } else  if (item.equalsIgnoreCase(getString(R.string.drawer_item_adjust_activities_before_attack))){
                intent = new Intent(this,AdjustAttackActivitiesActivity.class);
            } else if (item.equalsIgnoreCase(getString(R.string.drawer_item_adjust_medicaments))){
                intent = new Intent(this,AdjustAttackMedicamentsActivity.class);
            } else  if (item.equalsIgnoreCase(getString(R.string.drawer_item_adjust_possible_cause))){
                intent = new Intent(this,AdjustAttackCauseActivity.class);
            } else {
                throw new IllegalStateException("settings undefined");
            }
            startActivity(intent);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings_list;
    }
}
