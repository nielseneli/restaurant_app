package com.margaret.gudfud;

import android.provider.BaseColumns;

/**
 * One contract coming right up.
 */
public final class OrdersDbContract {
    private OrdersDbContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "orders";
        public static final String COLUMN_NAME_CUSTOMER = "customer";
        public static final String COLUMN_NAME_ORDER = "orderItems"; //cant be just order b/c that's a keyword lol
    }

}
