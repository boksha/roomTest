package com.example.miodragmilosevic.roomtest.registration;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.miodragmilosevic.roomtest.attacklist.AttackListFragment;

/**
 * Created by miodrag.milosevic on 1/19/2018.
 */

public class MainScreenPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MainScreenPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages.
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for a particular page.
        @Override
        public Fragment getItem(int position) {
            Log.i("Miki", "getItem: " + position);
            switch (position) {
                case 0:
//                    return StartAttackFragment.newInstance("Fragment 1");
                case 1:
                    return AttackListFragment.newInstance("Fragment 2");
                case 2:
//                    return FragmentFirst.newInstance("Fragment 3");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }


}
