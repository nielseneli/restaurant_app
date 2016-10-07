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
 * A list adapter for the menu items. .
 */
public class MenuItemsListAdapter extends ArrayAdapter<MenuItem>{
    @BindView(R.id.tvText) TextView tvtext;
    private ArrayList<MenuItem> items;

    public MenuItemsListAdapter(Context context, ArrayList<MenuItem> items) {
        super(context, 0, items);
        this.items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MenuItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }



        // Return the completed view to render on screen
        return convertView;
        }
    }
