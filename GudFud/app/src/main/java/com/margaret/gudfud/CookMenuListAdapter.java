package com.margaret.gudfud;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A list adapter for the cook's menu items.
 */
public class CookMenuListAdapter extends ArrayAdapter<MenuItem>{
    @BindView(R.id.cookMenuText) TextView tvText;
    private ArrayList<MenuItem> items;

    ItemsDbHelper itemsDbHelper = new ItemsDbHelper(getContext());
    final SQLiteDatabase db = itemsDbHelper.getWritableDatabase();

    public CookMenuListAdapter(Context context, ArrayList<MenuItem> items) {
        super(context, 0, items);
        this.items = items;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MenuItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_cook, parent, false);
        }

        ButterKnife.bind(this, convertView);

        tvText.setText(item.getName());

        //onClick, you'll eventually go to the item view page.
        tvText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //go to the item fragment
            }
        });

        // Return the completed view to render on screen
        return convertView;
        }
    }
