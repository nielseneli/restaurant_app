package com.margaret.gudfud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A profile??? Or just see all of the orders for a given person
 */
public class ProfileFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final ListView listView = (ListView) view.findViewById(R.id.profileListView);
        final EditText editText = (EditText) view.findViewById(R.id.userOrders);
        Button enterButton = (Button) view.findViewById(R.id.profileEnterButton);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameInput = editText.getText().toString();

                //get the ordersDbHelper and the listAdapter all set up...
                OrdersDbHelper ordersDbHelper = new OrdersDbHelper(view.getContext());
                final SQLiteDatabase db = ordersDbHelper.getReadableDatabase();

                final ArrayList<Order> orders = ordersDbHelper.getOrdersSearch(usernameInput);
                final OrdersListAdapter listAdapter = new OrdersListAdapter(view.getContext(), orders);
                listView.setAdapter(listAdapter);

            }
        });

        return view;
    }

}
