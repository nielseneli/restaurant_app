package com.margaret.gudfud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lnielsen on 10/12/16.
 */

public class IngredientsListAdapter extends ArrayAdapter<Ingredient>{
    private ArrayList<Ingredient> ingredients;

    public IngredientsListAdapter(Context context, ArrayList<Ingredient> ingredients) {
        super(context, 0, ingredients);
        this.ingredients = ingredients;
    }

    // thank you stackoverflow
    // http://stackoverflow.com/questions/17525886/listview-with-add-and-delete-buttons-in-each-row-in-android
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final Ingredient ingredient = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.ingredients_list_item, parent, false);
        }

        TextView textView = (TextView) view.findViewById(R.id.ingItemEditText);
        textView.setText(ingredient.getIng());

        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.deleteIngButton);
        ImageButton editButton = (ImageButton) view.findViewById(R.id.editIngButton);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ingredients.remove(position);
                notifyDataSetChanged();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyDataSetChanged();
            }
        });

        return view;

    }
}
