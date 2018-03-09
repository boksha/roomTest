package com.example.miodragmilosevic.roomtest.attacklist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.attackdetails.AttackDetailsActivity;
import com.example.miodragmilosevic.roomtest.db.AppDataBase;

/**
 * Created by miodrag.milosevic on 1/19/2018.
 */

public class AttackListFragment extends Fragment {


    private static final String TAG = "Miki";
    private AttackListAdapter mAttackAdapter;
    private AttackListViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_attack_list, container, false);

        initAttackListRecyclerView(rootView);
        return rootView;
    }

    private String title;
//    private int image;

    public static Fragment newInstance(String title) {
        AttackListFragment fragment = new AttackListFragment();
        Bundle args = new Bundle();
//        args.putInt("image", resImage);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        image = getArguments().getInt("image", 0);
        title = getArguments().getString("title");
        mViewModel = ViewModelProviders.of(this,
                new AttackListViewModel.Factory(
                        new AttackListRepository(AppDataBase.get(getActivity().getApplicationContext()).getEpiAttackDao()),
                        new AttackItemMapper(getActivity())))
                .get(AttackListViewModel.class);
        mViewModel.getLiveData().observe(this, viewData ->
        {//TODO
            Log.i(TAG, "onCreate observe: " + viewData);
            mAttackAdapter.setAttackItems(viewData);
        });

    }


    private void initAttackListRecyclerView(View rootView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAttackAdapter = new AttackListAdapter();
        mAttackAdapter.setOnItemClickListener((long attackId) -> {
            Log.i("Miki", "initAttackListRecyclerView: " + attackId);
            Intent intent = new Intent(getActivity(), AttackDetailsActivity.class);
            intent.putExtra(AttackDetailsActivity.EXTRA_ATTACK_ID,attackId);
            startActivity(intent);
        });
        RecyclerView reviewsRecyclerView = rootView.findViewById(R.id.recycler_attack_list);
        reviewsRecyclerView.setLayoutManager(layoutManager);
        reviewsRecyclerView.setAdapter(mAttackAdapter);
    }
}

