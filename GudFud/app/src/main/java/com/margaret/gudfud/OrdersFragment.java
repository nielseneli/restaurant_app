package com.margaret.gudfud;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

        return view;
    }

}
