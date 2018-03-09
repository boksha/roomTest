package com.example.miodragmilosevic.roomtest.common.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by miodrag.milosevic on 2/2/2018.
 */

public  class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private OnDateSetListener mOnDateSetListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Calendar cal = new GregorianCalendar(year, month, day);
//        setDate(cal);
        if(mOnDateSetListener !=null){
            mOnDateSetListener.onDateSet(cal.getTimeInMillis());
        }
    }

    public void setOnDateSetListener(OnDateSetListener onDateSetListener) {
        mOnDateSetListener = onDateSetListener;
    }

    /**
     * Interface for sending the Date and Time to the calling object
     */
    public interface OnDateSetListener {
        void onDateSet(long timeInMillis);
    }
}
