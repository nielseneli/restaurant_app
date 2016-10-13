package com.margaret.gudfud;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * The fragment for the cook's menu
 */
public class CookMenuFragment extends Fragment{

    private OnFragmentInteractionListener mListener;

    public CookMenuFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu_cook, container, false);

        Button addItemButton = (Button) view.findViewById(R.id.addItemButton);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        ItemsDbHelper itemsDbHelper = new ItemsDbHelper(view.getContext());
        final SQLiteDatabase db = itemsDbHelper.getWritableDatabase();

        ArrayList<MenuItem> list = itemsDbHelper.getAllItems();
        final CookMenuListAdapter listAdapter = new CookMenuListAdapter(getActivity(), list);
        listView.setAdapter(listAdapter);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Enter a menu item");
                final EditText edittext = new EditText(getActivity());
                alertDialogBuilder.setView(edittext);

                alertDialogBuilder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String textInput = edittext.getText().toString();
                        MenuItem itemInput = new MenuItem(textInput);
                        listAdapter.add(itemInput);

                        ContentValues values = new ContentValues();
                        values.put(ItemsDbContract.FeedEntry.COLUMN_NAME_ITEM, textInput);

                        long newRowId = db.insert(ItemsDbContract.FeedEntry.TABLE_NAME, null, values);
                        itemInput.setId(newRowId);
                    }
                });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        return view;
    }

    public void onCreate() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void goToItemFragment(int position);
    }

}
