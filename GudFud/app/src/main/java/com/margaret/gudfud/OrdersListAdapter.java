package com.margaret.gudfud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * A list adapter for orders...
 */
public class OrdersListAdapter extends ArrayAdapter<Order> {
    private ArrayList<Order> orders;

    public OrdersListAdapter(Context context, ArrayList<Order> orders) {
        super(context, 0, orders);
        this.orders = orders;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_single_order, parent, false);
        }



        return convertView;
    }
}
