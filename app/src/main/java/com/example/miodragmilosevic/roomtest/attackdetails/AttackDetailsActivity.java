package com.example.miodragmilosevic.roomtest.attackdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public class AttackDetailsActivity extends BaseToolbarActivity {

    private static final String TAG = "Miki";
    public static final String EXTRA_ATTACK_ID = "AttackId";
    public static final String KEY_SAVED_ATTACK_ID = "SavedAttackId";

    private EditText mDateValue;
    private EditText mTimeValue;
    private EditText mDescription;
    private AttackDetailsViewModel mViewModel;
    private long mAttackId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle.setText(R.string.title_attack_details);

//        setContentView(R.layout.activity_attack_detailes);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState != null){
            mAttackId = savedInstanceState.getLong(KEY_SAVED_ATTACK_ID,-1);
            Log.i(TAG, "onCreate: savedInstanceState:mAttackId= " + mAttackId);
        } else {
            Intent intent = getIntent();
            if (intent != null) {
                mAttackId = intent.getLongExtra(EXTRA_ATTACK_ID, -1);
            }
            Log.i(TAG, "onCreate: savedInstanceState == null: mAttackId = " + mAttackId);

        }
        mViewModel = ViewModelProviders.of(this,
                new AttackDetailsViewModel.Factory(
                        new AttackDetailsRepository(AppDataBase.get(this.getApplicationContext()).getEpiAttackDao()), mAttackId))
                .get(AttackDetailsViewModel.class);
        mViewModel.getLiveData().observe(this, viewData ->
        {//TODO
            Log.i(TAG, "onCreate observe: " + viewData);
//            mAttackAdapter.setAttackItems(viewData);
        });
//        mDescription = findViewById(R.id.note);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        Button chooseDate = findViewById(R.id.btn_pick_date);
//        chooseDate.setOnClickListener(this::showDatePickerDialog);
//        mDateValue = findViewById(R.id.date_value);
//        Button chooseTime = findViewById(R.id.btn_pick_time);
//        mTimeValue = findViewById(R.id.time_value);
//        chooseTime.setOnClickListener(this::showTimePickerDialog);
//        initLocationSpinner();
////        initActivitySpinner();
////        initMedicamentSpinner();
////        initAttackTypeSpinner();
//        mViewModel = ViewModelProviders.of(this,
//                new AttackDetailsViewModel.Factory(
//                        new CreateAttackRepository(AppDataBase.get(getApplicationContext()).getEpiAttackDao())))
//                .get(CreateAttackViewModel.class);
//        mViewModel.getLiveData().observe(this, viewData ->
//                {
//                    Log.i("Miki", "onCreate: view state error : " + viewData.isError());
//                    if (viewData.isError()) {
//                        if (viewData.isDataBaseInsertFailed()) {
//                            Toast.makeText(this, "db insert failed", Toast.LENGTH_LONG).show();
//                        }
//                        if (viewData.isDateMissing()) {
//                            Toast.makeText(this, "date is missing", Toast.LENGTH_LONG).show();
//                        }
//                        if (viewData.isTimeMissing()) {
//                            Toast.makeText(this, "time is missing", Toast.LENGTH_LONG).show();
//                        }
//                    } else if (viewData.isCompleted()) {
//                        //go to main screen
//                        finish();
//                    }
//                }
//        );
        Button changeRecord = findViewById(R.id.btn_change_epy_attack);
        changeRecord.setOnClickListener(view -> {
            mViewModel.onChangeButtonClick();
        });
        Button deleteRecord = findViewById(R.id.btn_delete_epy_attack);
        deleteRecord.setOnClickListener(view -> {
            mViewModel.onDeleteButtonClick();
        });
//        if (intent != null) {
//            long startTime = intent.getLongExtra(EXTRA_START_TIME, -1);
//            mViewModel.onDateSet(startTime);
//            mDateValue.setText(String.valueOf(startTime));
//            long elapsedTime = intent.getLongExtra(EXTRA_ELAPSED_TIME, -1);
//            mViewModel.onTimeSet(elapsedTime);
//            mTimeValue.setText(String.valueOf(elapsedTime));
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_attack_detailes;
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(KEY_SAVED_ATTACK_ID,mAttackId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
//    } private void initLocationSpinner() {
//        Spinner locationSpinner = (Spinner) findViewById(R.id.location_spinner);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        // Application of the Array to the Spinner
////        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, colors);
////        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
////        spinner.setAdapter(spinnerArrayAdapter);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.attack_location, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        locationSpinner.setAdapter(adapter);
//        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, "onItemSelected: locationSPinner" + parent.getItemAtPosition(position) + " " + id);
//                mViewModel.onLocationSelected((String) parent.getItemAtPosition(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Log.i(TAG, "onNothingSelected: locationSPinner");
//
//            }
//        });
}

