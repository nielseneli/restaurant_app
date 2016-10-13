package com.margaret.gudfud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * The fragment for the orders page, which displays a list of customers and the items that they ordered.
 */
public class OrdersFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        ListView listView = (ListView) view.findViewById(R.id.ordersListView);

        //get the ordersDbHelper and the listAdapter all set up...
        OrdersDbHelper ordersDbHelper = new OrdersDbHelper(view.getContext());
        final SQLiteDatabase db = ordersDbHelper.getReadableDatabase();

        final ArrayList<Order> orders = ordersDbHelper.getAllOrders();
        final OrdersListAdapter listAdapter = new OrdersListAdapter(view.getContext(), orders);
        listView.setAdapter(listAdapter);


        return view;
    }

}
