package com.example.miodragmilosevic.roomtest.createattackrecord;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.miodragmilosevic.roomtest.DatePickerFragment;
import com.example.miodragmilosevic.roomtest.MinSecPicker;
import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.base.BaseToolbarActivity;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class CreateAttackRecordActivity extends BaseToolbarActivity {

    private static final String TAG = "Miki";
    public static final String EXTRA_START_TIME = "StartTime";
    public static final String EXTRA_ELAPSED_TIME = "ElapsedTime";
    public static final String DATE_PICKER_TAG = "datePicker";

    private EditText mDateValue;
    private EditText mTimeValue;
    private EditText mDescription;
    private CreateAttackViewModel mViewModel;
    private DatePickerFragment.OnDateSetListener mOnDateSetListener = new DatePickerFragment.OnDateSetListener() {
        @Override
        public void onDateSet(long timeInMillis) {
            mViewModel.onDateSet(timeInMillis);
            updateDate(timeInMillis);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create_epy_attack_record);
        Intent intent = getIntent();
        mDescription = findViewById(R.id.note);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Button chooseDate = findViewById(R.id.btn_pick_date);
        chooseDate.setOnClickListener(this::showDatePickerDialog);
        mDateValue = findViewById(R.id.date_value);
        Button chooseTime = findViewById(R.id.btn_pick_time);
        mTimeValue = findViewById(R.id.time_value);
        chooseTime.setOnClickListener(this::showTimePickerDialog);
        initLocationSpinner();
        initActivitySpinner();
        initMedicamentSpinner();
        initAttackTypeSpinner();
        mViewModel = ViewModelProviders.of(this,
                new CreateAttackViewModel.Factory(
                        new CreateAttackRepository(AppDataBase.get(getApplicationContext()).getEpiAttackDao(),
                                AppDataBase.get(getApplicationContext()).getEpiAttackResourceDao())))
                .get(CreateAttackViewModel.class);
        mViewModel.getLiveData().observe(this, viewData ->
                {
                    Log.i("Miki", "onCreate: view state error : " + viewData.isError());
                    if (viewData.isError()){
                        if (viewData.isDataBaseInsertFailed()){
                            Toast.makeText(this,"db insert failed",Toast.LENGTH_LONG).show();
                        }
                        if (viewData.isDateMissing()){
                            Toast.makeText(this,"date is missing",Toast.LENGTH_LONG).show();
                        }
                        if (viewData.isTimeMissing()){
                            Toast.makeText(this,"time is missing",Toast.LENGTH_LONG).show();
                        }
                    } else if(viewData.isCompleted()){
                        //go to main screen
                        finish();
                    }
                }
        );
        Button saveRecord = findViewById(R.id.btn_save_epy_attack);
        saveRecord.setOnClickListener(view -> {
            mViewModel.setDescription(mDescription.getText().toString());
            mViewModel.onSaveButtonClick();
        });
        if (intent!=null){
            long startTime = intent.getLongExtra(EXTRA_START_TIME,-1) ;
            if (startTime !=-1) {
                mViewModel.onDateSet(startTime);
                updateDate(startTime);
            }
            long elapsedTime = intent.getLongExtra(EXTRA_ELAPSED_TIME,-1) ;
            if(elapsedTime != -1) {
                mViewModel.onTimeSet(elapsedTime);
                mTimeValue.setText(String.valueOf(elapsedTime));
            }
            if(elapsedTime == -1 && startTime == -1){
                mTitle.setText(R.string.title_manual_insert);

            } else {
                mTitle.setText(R.string.title_create_record);
            }
        }
//        setResult(RESULT_OK);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_epy_attack_record;
    }

    @Override
    protected void onResume() {
        super.onResume();
        DatePickerFragment dialog = (DatePickerFragment) getSupportFragmentManager().findFragmentByTag(DATE_PICKER_TAG);
        if(dialog  != null){
            dialog.setOnDateSetListener(mOnDateSetListener);
        }
    }

    private void initLocationSpinner() {
        Spinner locationSpinner = (Spinner) findViewById(R.id.location_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.attack_location, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        locationSpinner.setAdapter(adapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: locationSPinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onLocationSelected((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: locationSPinner");

            }
        });
    }

    private void initMedicamentSpinner() {
        Spinner medicamentSpinner = (Spinner) findViewById(R.id.cure_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.medicaments, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
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
        Spinner attackTypeSpinner = findViewById(R.id.possible_cause_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList(strings));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.possible_cause_of_attack, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        attackTypeSpinner.setAdapter(adapter);
        attackTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: attackTypeSpinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onAttackTypeSelected((String) parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "onNothingSelected: attackTypeSpinner");

            }
        });
    }

    private void initActivitySpinner() {
        Spinner locationSpinner = (Spinner) findViewById(R.id.activity_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_before_attack, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        locationSpinner.setAdapter(adapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected: activitySpinner" + parent.getItemAtPosition(position) + " " + id);
                mViewModel.onActivitySelected((String) parent.getItemAtPosition(position));

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

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new MinSecPicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

}
