package com.example.miodragmilosevic.roomtest.startattack;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.createattackrecord.CreateAttackRecordActivity;

/**
 * Created by miodrag.milosevic on 1/25/2018.
 */

public class StartAttackFragment extends Fragment {

    private static final int SHOW_ADD_EPYATTACKS_REQ_CODE = 100;
    private String title;
    private Button mAttackButton;
    private FloatingActionButton mAddAttackButton;
    private StartAttackViewModel mViewModel;
    private TextView mCounter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_start_attack, container, false);
        mAddAttackButton = rootView.findViewById(R.id.add_attack);
        mAddAttackButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),CreateAttackRecordActivity.class);
            mViewModel.setDefaultState();
            startActivityForResult(intent,SHOW_ADD_EPYATTACKS_REQ_CODE);
        });
        mAttackButton = rootView.findViewById(R.id.attack_btn);
        mAttackButton.setOnClickListener(view -> {
            mViewModel.onAttackButtonClick();
        });
        mCounter = rootView.findViewById(R.id.attack_counter);
        return rootView;
    }


    public static Fragment newInstance(String title) {
        StartAttackFragment fragment = new StartAttackFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this,
                new StartAttackViewModel.Factory(new StartAttackRepository()))
                .get(StartAttackViewModel.class);
        mViewModel.getLiveData().observe(this, viewData ->
                {
                    int viewState = viewData.getState();
                    Log.i("Miki", "onCreate: view state" + viewState);
                    if (viewState == StartAttackUiModel.STARTED){
                        mAttackButton.setText(R.string.btn_stop_attack);
                        mCounter.setText(viewData.getFormattedElapsedTime());
                        mAddAttackButton.setVisibility(View.GONE);
                    } else if (viewState == StartAttackUiModel.IN_PROCESSING){
                        Intent intent = new Intent(getActivity(),CreateAttackRecordActivity.class);
                        intent.putExtra(CreateAttackRecordActivity.EXTRA_START_TIME,viewData.getStartTime());
                        intent.putExtra(CreateAttackRecordActivity.EXTRA_ELAPSED_TIME,viewData.getElapsedTime());
                        mViewModel.setDefaultState();
                        startActivityForResult(intent,SHOW_ADD_EPYATTACKS_REQ_CODE);
                    } else {
                        mAttackButton.setText(R.string.btn_start_attack);
                        mAddAttackButton.setVisibility(View.VISIBLE);
                        mCounter.setText(viewData.getFormattedElapsedTime());
                    }
                }
        );
        title = getArguments().getString("title");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SHOW_ADD_EPYATTACKS_REQ_CODE) {
//            mViewModel.setDefaultState();
        }
    }
}