package com.example.miodragmilosevic.roomtest.attackdetails;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.miodragmilosevic.roomtest.common.dialog.AlertDialogFragment;
import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.base.BaseSpinnerAdapter;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.base.BaseUiListItem;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;

import java.util.ArrayList;

/**
 * Created by miodrag.milosevic on 2/7/2018.
 */

public class AttackDetailsActivity extends BaseToolbarActivity {

    private static final String TAG = "Miki";
    public static final String EXTRA_ATTACK_ID = "AttackId";
    public static final String KEY_SAVED_ATTACK_ID = "SavedAttackId";
    private static final String DELETE_DIALOG_TAG = "deleteDialog";

    private EditText mDateValue;
    private EditText mTimeValue;
    private EditText mDescription;
    private AttackDetailsViewModel mViewModel;
    private ArrayAdapter<BaseUiListItem> mLocationAdapter;
    private ArrayAdapter<BaseUiListItem> mTypeAdapter;
    private ArrayAdapter<BaseUiListItem> mCauseAdapter;
    private ArrayAdapter<BaseUiListItem> mActivityAdapter;
    private ArrayAdapter<BaseUiListItem> mMedicamentAdapter;
    private long mAttackId = -1;
    private AlertDialogFragment.OnPositiveClickListener mOnDeleteListener = new AlertDialogFragment.OnPositiveClickListener() {
        @Override
        public void onClick() {
            mViewModel.onDeleteButtonClick();
        }
    };

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
                new AttackDetailsViewModel.Factory(this,
                        new AttackDetailsRepository(AppDataBase.get(this.getApplicationContext()).getEpiAttackDao(),
                                AppDataBase.get(this.getApplicationContext()).getEpiAttackResourceDao()
                        ), mAttackId))
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
        initLocationSpinner();
        initActivitySpinner();
        initMedicamentSpinner();
        initAttackTypeSpinner();
        initAttackCauseSpinner();
        Button changeRecord = findViewById(R.id.btn_change_epy_attack);
        changeRecord.setOnClickListener(view -> {
            mViewModel.onChangeButtonClick();
        });
        Button deleteRecord = findViewById(R.id.btn_delete_epy_attack);
        deleteRecord.setOnClickListener(view -> {
            showDeleteDialog();
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

    private void showDeleteDialog() {
        AlertDialogFragment deleteDialog = AlertDialogFragment.newInstance(R.string.title_dialog_delete,R.string.message_delete);
        deleteDialog.setOnPositiveClickListener(mOnDeleteListener);
        deleteDialog.show(getSupportFragmentManager(), DELETE_DIALOG_TAG);
    }
    @Override
    protected void onResume() {
        super.onResume();
        AlertDialogFragment dialog = (AlertDialogFragment) getSupportFragmentManager().findFragmentByTag(DELETE_DIALOG_TAG);
        if (dialog != null) {
            dialog.setOnPositiveClickListener(mOnDeleteListener);
        }
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
    private void initLocationSpinner() {
        Spinner locationSpinner = findViewById(R.id.location_spinner);
        mLocationAdapter = new BaseSpinnerAdapter(this, R.layout.spinner_item, new ArrayList<>());
        mViewModel.getSpinnerLocationLiveData().observe(this, viewData ->
                {
                    mLocationAdapter.clear();
                    mLocationAdapter.addAll(viewData);
                }
        );
        mLocationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(mLocationAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: locationSPinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onLocationSelected((BaseUiListItem) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: locationSPinner");

            }
        });
    }

    private void initMedicamentSpinner() {
        Spinner medicamentSpinner = (Spinner) findViewById(R.id.cure_spinner);
        //TODO change this to medicament list when is defined!!!!! Miki
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.medicaments, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicamentSpinner.setAdapter(adapter);
        medicamentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: medicamentSpinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onMedicamentSelected((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: medicamentSpinner");

            }
        });
    }

    private void initAttackTypeSpinner() {
        Spinner attackTypeSpinner = findViewById(R.id.attack_type_spinner);
        mTypeAdapter = new BaseSpinnerAdapter(this, R.layout.spinner_item, new ArrayList<>());
        mViewModel.getSpinnerTypeLiveData().observe(this, viewData ->
                {
                    mTypeAdapter.clear();
                    mTypeAdapter.addAll(viewData);
                }
        );

        mTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attackTypeSpinner.setAdapter(mTypeAdapter);
        attackTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: attackTypeSpinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onAttackTypeSelected((BaseUiListItem) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: attackTypeSpinner");

            }
        });
    }

    private void initAttackCauseSpinner() {
        Spinner attackCauseSpinner = findViewById(R.id.possible_cause_spinner);
        mCauseAdapter = new BaseSpinnerAdapter(this, R.layout.spinner_item, new ArrayList<>());
        mViewModel.getSpinnerCauseLiveData().observe(this, viewData ->
                {
                    mCauseAdapter.clear();
                    mCauseAdapter.addAll(viewData);
                }
        );

        mCauseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attackCauseSpinner.setAdapter(mCauseAdapter);
        attackCauseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: attackCauseSpinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onAttackCauseSelected((BaseUiListItem) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: attackCauseSpinner");

            }
        });
    }

    private void initActivitySpinner() {
        Spinner activitySpinner = (Spinner) findViewById(R.id.activity_spinner);
        mActivityAdapter = new BaseSpinnerAdapter(this, R.layout.spinner_item, new ArrayList<>());
        mViewModel.getSpinnerActivityLiveData().observe(this, viewData ->
                {
                    mActivityAdapter.clear();
                    mActivityAdapter.addAll(viewData);
                }
        );

        mActivityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitySpinner.setAdapter(mActivityAdapter);
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: activitySpinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onActivitySelected((BaseUiListItem) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: activitySpinner");

            }
        });
    }

}

