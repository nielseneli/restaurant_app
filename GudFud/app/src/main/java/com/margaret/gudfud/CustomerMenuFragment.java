package com.margaret.gudfud;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * the fragment for the customer's menu
 */
public class CustomerMenuFragment extends Fragment{

    public CustomerMenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu_customer, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listViewCustomer);
        Button button = (Button) view.findViewById(R.id.button);

        ItemsDbHelper itemsDbHelper = new ItemsDbHelper(getContext());
        final SQLiteDatabase itemsDb = itemsDbHelper.getReadableDatabase();

        final ArrayList<MenuItem> list = itemsDbHelper.getAllItems();
        final CustomerMenuListAdapter customerAdapter = new CustomerMenuListAdapter(getActivity(), list);

        OrdersDbHelper ordersDbHelper = new OrdersDbHelper(getContext());
        final SQLiteDatabase ordersDb = ordersDbHelper.getWritableDatabase();

        //source for a fair bit of this: http://kb4dev.com/tutorial/android-listview/android-listview-with-checkbox
        listView.setAdapter(customerAdapter);
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        final ArrayList<Integer> checkedPositions = new ArrayList<Integer>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                    CheckedTextView ctv = (CheckedTextView) view.findViewById(R.id.customerCheckedTextView);
                    if (ctv.isChecked()) {
                        Toast.makeText(getContext(), "now it is unchecked", Toast.LENGTH_SHORT).show();
                        ctv.setChecked(false);
                        checkedPositions.remove(new Integer(position));

                    } else {
                        Toast.makeText(getContext(), "now it is checked", Toast.LENGTH_SHORT).show();
                        ctv.setChecked(true);
                        checkedPositions.add(position);

                    }

            }
        });

        button.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View v) {

                Context context = getContext();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("Please enter your name.");
                final EditText edittext = new EditText(context);
                alertDialogBuilder.setView(edittext);

                alertDialogBuilder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //get the checked values, and make them a string.
                        String checked = "";
                        for (int i=0; i < checkedPositions.size(); i++) {
                            checked += list.get(checkedPositions.get(i)).getName() + " ";
                        }
                        Toast.makeText(getContext(), "" + checked, Toast.LENGTH_LONG).show();

                        //get the customer name.
                        String textInput = edittext.getText().toString();

                        //and put them both into SQL
                        ContentValues values = new ContentValues();
                        values.put(OrdersDbContract.FeedEntry.COLUMN_NAME_CUSTOMER, textInput);
                        values.put(OrdersDbContract.FeedEntry.COLUMN_NAME_ORDER, checked);
                        long newRowId = ordersDb.insert(OrdersDbContract.FeedEntry.TABLE_NAME, null, values);

                    }
                });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        return view;
    }

}
