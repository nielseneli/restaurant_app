package com.margaret.gudfud;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * the fragment for the customer's menu
 */
public class CustomerMenuFragment extends Fragment{

    @BindView(R.id.listViewCustomer) ListView listView;
    @BindView(R.id.customerCheckedTextView) CheckedTextView checkedTextView;

    public CustomerMenuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu_customer, container, false);

        ButterKnife.bind(this, view);

        ArrayList<MenuItem> list = new ArrayList<MenuItem>();

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
                CheckedTextView ctv = (CheckedTextView)view;
                if(ctv.isChecked()){
                    Toast.makeText(getContext(), "now it is unchecked", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "now it is checked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
