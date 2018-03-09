package com.example.miodragmilosevic.roomtest.createattackrecord;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.miodragmilosevic.roomtest.common.dialog.DatePickerFragment;
import com.example.miodragmilosevic.roomtest.common.dialog.MinSecPickerFragment;
import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.base.BaseSpinnerAdapter;
import com.example.miodragmilosevic.roomtest.base.BaseUiListItem;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CreateAttackRecordActivity extends BaseToolbarActivity {

    private static final String TAG = "Miki";
    public static final String EXTRA_START_TIME = "StartTime";
    public static final String EXTRA_ELAPSED_TIME = "ElapsedTime";
    public static final String DATE_PICKER_TAG = "datePicker";
    private static final String TIME_PICKER_TAG = "timePicker";

    private EditText mDateValue;
    private EditText mTimeValue;
    private EditText mDescription;
    private CreateAttackViewModel mViewModel;
    private ArrayAdapter<BaseUiListItem> mLocationAdapter;
    private ArrayAdapter<BaseUiListItem> mTypeAdapter;
    private ArrayAdapter<BaseUiListItem> mCauseAdapter;
    private ArrayAdapter<BaseUiListItem> mActivityAdapter;
    private ArrayAdapter<BaseUiListItem> mMedicamentAdapter;
    private DatePickerFragment.OnDateSetListener mOnDateSetListener = new DatePickerFragment.OnDateSetListener() {
        @Override
        public void onDateSet(long timeInMillis) {
            mViewModel.onDateSet(timeInMillis);
            updateDate(timeInMillis);
        }
    };
    private MinSecPickerFragment.OnTimeValueChangedListener mOnTimeChangedListener = new MinSecPickerFragment.OnTimeValueChangedListener() {
        @Override
        public void onTimeValueChanged(int min, int sec) {
            long elapsedTime = (min * 60 + sec) * 1000;
            mViewModel.onTimeSet(elapsedTime);
            mTimeValue.setText(String.format("%02d.%02d min", min, sec));
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mDescription = findViewById(R.id.note);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ImageView chooseDate = findViewById(R.id.btn_pick_date);
        chooseDate.setOnClickListener(this::showDatePickerDialog);
        mDateValue = findViewById(R.id.date_value);
        ImageView chooseTime = findViewById(R.id.btn_pick_time);
        mTimeValue = findViewById(R.id.time_value);
        chooseTime.setOnClickListener(this::showTimePickerDialog);

        mViewModel = ViewModelProviders.of(this,
                new CreateAttackViewModel.Factory(this,
                        new CreateAttackRepository(AppDataBase.get(getApplicationContext()).getEpiAttackDao(),
                                AppDataBase.get(getApplicationContext()).getEpiAttackResourceDao())))
                .get(CreateAttackViewModel.class);
        mViewModel.getLiveData().observe(this, viewData ->
                {
                    Log.i("Miki", "onCreate: view state error : " + viewData.isError());
                    if (viewData.isError()) {
                        if (viewData.isDataBaseInsertFailed()) {
                            Toast.makeText(this, "db insert failed", Toast.LENGTH_LONG).show();
                        }
                        if (viewData.isDateMissing()) {
                            Toast.makeText(this, "date is missing", Toast.LENGTH_LONG).show();
                        }
                        if (viewData.isTimeMissing()) {
                            Toast.makeText(this, "time is missing", Toast.LENGTH_LONG).show();
                        }
                    } else if (viewData.isCompleted()) {
                        //go to main screen
                        finish();
                    }
                }
        );
        initLocationSpinner();
        initActivitySpinner();
        initMedicamentSpinner();
        initAttackTypeSpinner();
        initAttackCauseSpinner();
        Button saveRecord = findViewById(R.id.btn_save_epy_attack);
        saveRecord.setOnClickListener(view -> {
            mViewModel.setDescription(mDescription.getText().toString());
            mViewModel.onSaveButtonClick();
        });
        if (intent != null) {
            long startTime = intent.getLongExtra(EXTRA_START_TIME, -1);
            if (startTime != -1) {
                mViewModel.onDateSet(startTime);
                updateDate(startTime);
            }
            long elapsedTime = intent.getLongExtra(EXTRA_ELAPSED_TIME, -1);
            if (elapsedTime != -1) {
                mViewModel.onTimeSet(elapsedTime);
                updateTime(elapsedTime);
            }
            if (elapsedTime == -1 && startTime == -1) {
                mTitle.setText(R.string.title_manual_insert);

            } else {
                mTitle.setText(R.string.title_create_record);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_epy_attack_record;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatePickerFragment dialogDate = (DatePickerFragment) getSupportFragmentManager().findFragmentByTag(DATE_PICKER_TAG);
        if (dialogDate != null) {
            dialogDate.setOnDateSetListener(mOnDateSetListener);
        }

        MinSecPickerFragment dialogTime = (MinSecPickerFragment) getSupportFragmentManager().findFragmentByTag(TIME_PICKER_TAG);
        if (dialogTime != null) {
            dialogTime.setOnTimeValueChangedListener(mOnTimeChangedListener);
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

    public void showDatePickerDialog(View v) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setOnDateSetListener(mOnDateSetListener);
        datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG);
    }

    private void updateDate(long timestamp) {
        String myFormat = "d. MMM, yyyy"; //In which you need put here add 4xM for full
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        mDateValue.setText(sdf.format(timestamp));
    }

    private void updateTime(long timestamp) {
        long sec = (timestamp / 1000) % 60;
        long min = ((timestamp / 1000) - sec) / 60;
        mTimeValue.setText(String.format("%02d.%02d min", min, sec));
    }

    public void showTimePickerDialog(View v) {
        MinSecPickerFragment minSecPicker = new MinSecPickerFragment();
        minSecPicker.setOnTimeValueChangedListener(mOnTimeChangedListener);
        minSecPicker.show(getSupportFragmentManager(), TIME_PICKER_TAG);
    }

}
