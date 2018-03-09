package com.example.miodragmilosevic.roomtest.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.miodragmilosevic.roomtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miodrag.milosevic on 2/26/2018.
 */

public class BaseSpinnerAdapter extends ArrayAdapter<BaseUiListItem> {

    private LayoutInflater mInflater;
    private List<BaseUiListItem> items  = new ArrayList<>();
    private final int mResource;

    public BaseSpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull List<BaseUiListItem> objects) {
        super(context, resource, 0, objects);
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView spinnerItemName = (TextView) view.findViewById(R.id.spinner_text_view);
        BaseUiListItem item = items.get(position);
        spinnerItemName.setText(item.getName());
        return view;
    }
}
