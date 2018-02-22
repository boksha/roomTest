package com.example.miodragmilosevic.roomtest.settings.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miodragmilosevic.roomtest.R;
import com.example.miodragmilosevic.roomtest.settings.base.BaseSettingsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miodrag.milosevic on 2/13/2018.
 */

public class BaseSettingsAdapter extends BaseAdapter {
    private List<BaseSettingsItem> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public BaseSettingsItem getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String title = getItem(position).getName();
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_settings, parent, false);
        }
        // Lookup view for data population
        TextView tvName =  convertView.findViewById(R.id.item_title);
        // Populate the data into the template view using the data object
        tvName.setText(title);
        // Return the completed view to render on screen
        return convertView;
    }

    public void setItems(List<BaseSettingsItem> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}
