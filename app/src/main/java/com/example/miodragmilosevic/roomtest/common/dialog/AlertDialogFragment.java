package com.example.miodragmilosevic.roomtest.common.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.miodragmilosevic.roomtest.R;


/**
 * Created by miodrag.milosevic on 2/23/2018.
 */

public class AlertDialogFragment extends DialogFragment {

    private OnPositiveClickListener mOnPositiveClickListener;

    public AlertDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    public static AlertDialogFragment newInstance(@StringRes int title , @StringRes int message) {
        AlertDialogFragment frag = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("message", message);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int message = getArguments().getInt("message");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(getString(title));
        alertDialogBuilder.setMessage(getString(message));
        alertDialogBuilder.setPositiveButton(getString(R.string.ok), (dialog, which) -> {
            // on success
            if(mOnPositiveClickListener != null){
                mOnPositiveClickListener.onClick();
            }
        });
        alertDialogBuilder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
            if (dialog != null ) {
                dialog.dismiss();
            }
        });


        return alertDialogBuilder.create();
    }

    public void setOnPositiveClickListener(OnPositiveClickListener onPositiveClickListener) {
        mOnPositiveClickListener = onPositiveClickListener;
    }

    public interface OnPositiveClickListener {

        void onClick();
    }
}