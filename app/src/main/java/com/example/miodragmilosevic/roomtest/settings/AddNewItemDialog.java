package com.example.miodragmilosevic.roomtest.settings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miodragmilosevic.roomtest.R;

/**
 * Created by miodrag.milosevic on 2/23/2018.
 */

public class AddNewItemDialog  extends DialogFragment {

    private OnAddItemListener mOnAddItemListener;

    private EditText mEditText;
    private ImageView mIconView;
    private TextView mTitle;
    public AddNewItemDialog() {
            // Empty constructor required for DialogFragment
        }

        public static AddNewItemDialog newInstance(@StringRes int title , @DrawableRes int icon) {
            AddNewItemDialog frag = new AddNewItemDialog();
            Bundle args = new Bundle();
            args.putInt("title", title);
            args.putInt("icon", icon);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int title = getArguments().getInt("title");
            int icon = getArguments().getInt("icon");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // inflate our custom layout for the dialog to a View
            View view = li.inflate(R.layout.dialog_add_new_item, null);
            // inform the dialog it has a custom View
            alertDialogBuilder.setView(view);
            mEditText = view.findViewById(R.id.dialog_add_new_item);
            mIconView = view.findViewById(R.id.dialog_icon);
            mTitle = view.findViewById(R.id.dialog_title);

            if(icon != -1) {
                mIconView.setImageResource(icon);
            }
            if(title != -1) {
                mTitle.setText(title);
            }

            alertDialogBuilder.setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                if(mOnAddItemListener != null){
                    mOnAddItemListener.onAddItem(mEditText.getText().toString());
                }
            });

            alertDialogBuilder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> {
                if (dialog != null ) {
                    dialog.dismiss();
                }
            });

            return alertDialogBuilder.create();
        }

    public void setOnAddItemListener(OnAddItemListener onAddItemListener) {
        mOnAddItemListener = onAddItemListener;
    }

    /**
     * Interface for sending the Date and Time to the calling object
     */
    public interface OnAddItemListener {
        void onAddItem(String item);
    }
}
