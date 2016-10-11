package com.margaret.gudfud;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        View tvView = inflater.inflate(R.layout.menu_item_customer, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listViewCustomer);
        CheckedTextView checkedTextView = (CheckedTextView) tvView.findViewById(R.id.customerCheckedTextView);

        if (listView == null){
            Log.d("ButterSadness", "where tf is the listView");
        } else {
            Log.d("ButterSadness", "succes");
        }

        ArrayList<MenuItem> list = new ArrayList<>();

        final CustomerMenuListAdapter customerAdapter = new CustomerMenuListAdapter(getActivity(), list);

        ArrayList<String> testIngredients = new ArrayList<>();
        testIngredients.add("milk");
        testIngredients.add("flour");
        testIngredients.add("goat's blood");

        MenuItem testItem1 = new MenuItem("cookies", testIngredients);
        customerAdapter.add(testItem1);

        listView.setAdapter(customerAdapter);
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, do something...

                    CheckedTextView ctv = (CheckedTextView) view.findViewById(R.id.customerCheckedTextView);
                    if (ctv.isChecked()) {
                        Toast.makeText(getContext(), "now it is unchecked", Toast.LENGTH_SHORT).show();
                        ctv.setSelected(false);
                    } else {
                        Toast.makeText(getContext(), "now it is checked", Toast.LENGTH_SHORT).show();
                        ctv.setSelected(true);
                    }

            }
        });

        return view;
    }

}
