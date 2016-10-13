package com.margaret.gudfud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A list adapter for orders...
 */
public class OrdersListAdapter extends ArrayAdapter<Order> {
    @BindView(R.id.tvCustomerName) TextView tvCustomer;
    @BindView(R.id.tvCustomerOrder) TextView tvOrder;
    private ArrayList<Order> orders;

    OrdersDbHelper ordersDbHelper = new OrdersDbHelper(getContext());
    final SQLiteDatabase ordersDb = ordersDbHelper.getReadableDatabase();

    public OrdersListAdapter(Context context, ArrayList<Order> orders) {
        super(context, 0, orders);
        this.orders = orders;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final Order order = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_single_order, parent, false);
        }

        ButterKnife.bind(this, convertView);

        tvCustomer.setText(order.getCustomer());
        tvOrder.setText(order.getOrder());

        return convertView;
    }
}
