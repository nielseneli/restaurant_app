package com.margaret.gudfud;

import android.provider.BaseColumns;

/**
 * Contract for ItemsDbHelper
 */
public final class ItemsDbContract {
    private ItemsDbContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ITEM = "item";
        public static final String COLUMN_NAME_INGREDIENTS = "ingredients";
    }

}
