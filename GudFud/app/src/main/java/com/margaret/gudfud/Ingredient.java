package com.margaret.gudfud;

/**
 * Created by lnielsen on 10/12/16.
 */

public class Ingredient {
    private String ing;
    private long id;

    public Ingredient() {

    }
    public Ingredient(String ing) {
        this.ing = ing;
    }

    public String getIng() {
        return ing;
    }

    public long getIngId() {
        return id;
    }

    public void setIng(String ing) {
        this.ing = ing;
    }

    public void setIngId(long id) {
        this.id = id;
    }

}
