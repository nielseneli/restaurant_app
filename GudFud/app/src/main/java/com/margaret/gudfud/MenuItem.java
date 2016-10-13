package com.margaret.gudfud;

import java.util.ArrayList;

/**
 * MenuItem class which consists of a name, and some other stuff like ingredients.
 */
public class MenuItem {
    private String name;
    private java.util.ArrayList<String> ingredients;
    private long id;

    public MenuItem() {

    }
    public MenuItem(String name) {
        this.name = name;
    }

    public MenuItem(String name, ArrayList<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }


}
