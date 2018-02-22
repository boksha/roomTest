package com.example.miodragmilosevic.roomtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by miodrag.milosevic on 1/19/2018.
 */

public class FragmentFirst extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_first, container, false);
        TextView tvLabel = (TextView) rootView.findViewById(R.id.message);
        tvLabel.setText(title);
        return rootView;
    }

    private String title;
//    private int image;

    public static Fragment newInstance(String title) {
        FragmentFirst fragment = new FragmentFirst();
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
    }


}

