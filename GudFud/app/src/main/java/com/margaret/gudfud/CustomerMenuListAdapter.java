package com.margaret.gudfud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A list adapter for the menu from the customer's view.
 */
public class CustomerMenuListAdapter extends ArrayAdapter<MenuItem>{
    @BindView(R.id.tvText) TextView tvText;
    private ArrayList<MenuItem> items;

    public CustomerMenuListAdapter(Context context, ArrayList<MenuItem> items) {
        super(context, 0, items);
        this.items = items;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final MenuItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_cook, parent, false);
        }

        ButterKnife.bind(this, convertView);

        tvText.setText(item.getName());

        return convertView;
    }
}
