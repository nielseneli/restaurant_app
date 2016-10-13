package com.margaret.gudfud;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * the fragment for editing a menu item (cook)
 */
public class ItemFragment extends Fragment {
    public static String ARG_POSITION = "position";

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get the arraylist so we can get the value from the associated position
        ItemsDbHelper itemsDbHelper = new ItemsDbHelper(getContext());
        final SQLiteDatabase itemsDb = itemsDbHelper.getReadableDatabase();
        final ArrayList<MenuItem> itemsList = itemsDbHelper.getAllItems();

        //get the bundle which contains the position
        Bundle b = getArguments();
        int pos = b.getInt(ARG_POSITION);
        String itemNameString = itemsList.get(pos).getName();
        Log.d("Position", "" + itemNameString);

        // inflate layout for fragment and for item in ListView in fragment
        final View view = inflater.inflate(R.layout.fragment_item, container, false);
        View ingItemView = inflater.inflate(R.layout.ingredients_list_item, container, false);

        // instantiating the buttons, the ListView, and the TextViews
        ListView listView = (ListView) view.findViewById(R.id.listViewIngredients);
        ImageButton editButton = (ImageButton) view.findViewById(R.id.editButton);
        ImageButton editDescriptionButton = (ImageButton) view.findViewById(R.id.editDescriptionButton);
        ImageButton addIngredientButton = (ImageButton) view.findViewById(R.id.addIngButton);

        final TextView itemName = (TextView) view.findViewById(R.id.itemName);
        final TextView description = (TextView) view.findViewById(R.id.description);

        itemName.setText(itemNameString);

        ArrayList<Ingredient> list = new ArrayList<>();

        final IngredientsListAdapter ingListAdapter = new IngredientsListAdapter(getActivity(), list);

        Ingredient testIng1 = new Ingredient("rocks");
        Ingredient testIng2 = new Ingredient("cilantro");
        Ingredient testIng3 = new Ingredient("a single worm");
        ingListAdapter.add(testIng1);
        ingListAdapter.add(testIng2);
        ingListAdapter.add(testIng3);

        listView.setAdapter(ingListAdapter);

        editButton.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final EditText editText = new EditText(getContext());
                editText.setText(itemName.getText());
                builder.setTitle("Please name your menu item.")
                        .setView(editText)
                        .setPositiveButton("enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemName.setText(editText.getText().toString());
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        editDescriptionButton.setOnClickListener(new AdapterView.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final EditText editText = new EditText(getContext());
                editText.setText(description.getText());
                builder.setTitle("What the heck are you serving?")
                        .setView(editText)
                        .setPositiveButton("enter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                description.setText(editText.getText().toString());
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ingredient newIngredient = new Ingredient("new ingredient");
                ingListAdapter.add(newIngredient);
            }
        });

        return view;
    }

}
