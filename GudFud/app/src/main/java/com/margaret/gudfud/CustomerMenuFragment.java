package com.margaret.gudfud;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CheckedTextView;
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
        final SQLiteDatabase db = itemsDbHelper.getReadableDatabase();

        final ArrayList<MenuItem> list = itemsDbHelper.getAllItems();
        final CustomerMenuListAdapter customerAdapter = new CustomerMenuListAdapter(getActivity(), list);

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
                String checked = "";
                for (int i=0; i < checkedPositions.size(); i++) {
                    //do stuff
                    checked += list.get(checkedPositions.get(i)).getName() + " ";
                }
                Toast.makeText(getContext(), "" + checked, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
