package com.margaret.gudfud;

import android.provider.BaseColumns;

/**
 * Contract 4 da ingredients database, ingredients have an id and a name.
 */
public final class IngredientsDbContract {
    private IngredientsDbContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Ingredients";
        public static final String COLUMN_NAME_INGREDIENT = "ingredient";
    }
}
