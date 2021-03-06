package com.example.miodragmilosevic.roomtest.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.settings.AddNewItemDialog;

import java.util.Calendar;

/**
 * Created by miodrag.milosevic on 2/19/2018.
 */

public class MinSecPickerFragment extends DialogFragment {
    private NumberPicker mMinPicker;
    private NumberPicker mSecPicker;
    private OnTimeValueChangedListener mOnValueChangedListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // get context
        // make dialog object
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // get the layout inflater
        LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate our custom layout for the dialog to a View
        View view = li.inflate(R.layout.min_sec_picker, null);
        // inform the dialog it has a custom View
        builder.setView(view);

        builder.setPositiveButton(getString(R.string.ok), (dialog, which) -> {
            if(mOnValueChangedListener != null){
                mOnValueChangedListener.onTimeValueChanged(mMinPicker.getValue(),mSecPicker.getValue());
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
            if (dialog != null ) {
                dialog.dismiss();
            }
        });
        // and if you need to call some method of the class
        mMinPicker =  view.findViewById(R.id.number_picker_min);
        mMinPicker.setFormatter(i -> String.format("%02d", i));
        mSecPicker =  view.findViewById(R.id.number_picker_sec);
        mSecPicker.setFormatter(i -> String.format("%02d", i));
        mMinPicker.setMinValue(0);
        mMinPicker.setMaxValue(59);
        mSecPicker.setMaxValue(59);
        mSecPicker.setMinValue(0);
        return builder.create();
    }

    public void setOnTimeValueChangedListener(OnTimeValueChangedListener onTimeChangedListener) {
        mOnValueChangedListener = onTimeChangedListener;
    }

    /**
     * Interface for sending the Date and Time to the calling object
     */
    public interface OnTimeValueChangedListener {
        void onTimeValueChanged(int min,int sec);
    }
//       final Context themeContext = getContext();
//final LayoutInflater inflater = LayoutInflater.from(themeContext);
//    final View view = inflater.inflate(R.layout.date_picker_dialog, null);
//    setView(view);
//
//    setButton(BUTTON_POSITIVE, themeContext.getString(R.string.ok), this);
//    setButton(BUTTON_NEGATIVE, themeContext.getString(R.string.cancel), this);
//    setButtonPanelLayoutHint(LAYOUT_HINT_SIDE);
//
//        if (calendar != null) {
//        year = calendar.get(Calendar.YEAR);
//        monthOfYear = calendar.get(Calendar.MONTH);
//        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//    }
//
//    mDatePicker = (DatePicker) view.findViewById(R.id.datePicker);
//        mDatePicker.init(year, monthOfYear, dayOfMonth, this);
//        mDatePicker.setValidationCallback(mValidationCallback);
//
//    mDateSetListener = listener;
//    private NumberPicker.OnValueChangeListener valueChangeListener;
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        final NumberPicker numberPicker = new NumberPicker(getActivity());
//
//        numberPicker.setMinValue(20);
//        numberPicker.setMaxValue(60);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Choose Value");
//        builder.setMessage("Choose a number :");
//
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                valueChangeListener.onValueChange(numberPicker,
//                        numberPicker.getValue(), numberPicker.getValue());
//            }
//        });
//
//        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                valueChangeListener.onValueChange(numberPicker,
//                        numberPicker.getValue(), numberPicker.getValue());
//            }
//        });
//
//        builder.setView(numberPicker);
//        return builder.create();
//    }

//    public NumberPicker.OnValueChangeListener getValueChangeListener() {
//        return valueChangeListener;
//    }
//
//    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
//        this.valueChangeListener = valueChangeListener;
//    }
}
