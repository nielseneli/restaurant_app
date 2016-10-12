package com.margaret.gudfud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Db helper 4 da database of orders
 */
public class OrdersDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "OrdersDbHelper";
    private static final String TEXT_TYPE = " TEXT";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + OrdersDbContract.FeedEntry.TABLE_NAME + " (" +
                    OrdersDbContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    OrdersDbContract.FeedEntry.COLUMN_NAME_CUSTOMER + TEXT_TYPE +
                    OrdersDbContract.FeedEntry.COLUMN_NAME_ORDER + TEXT_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + OrdersDbContract.FeedEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public OrdersDbHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        //https://github.com/codepath/android_guides/wiki/Local-Databases-with-SQLiteOpenHelper

        // SELECT * FROM POSTS
        String POSTS_SELECT_QUERY =
                "SELECT * FROM " + OrdersDbContract.FeedEntry.TABLE_NAME;

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Order newOrder = new Order();
                    newOrder.setCustomer(cursor.getString(cursor.getColumnIndex(OrdersDbContract.FeedEntry.COLUMN_NAME_CUSTOMER)));
                    newOrder.setOrder(cursor.getString(cursor.getColumnIndex(OrdersDbContract.FeedEntry.COLUMN_NAME_ORDER)));
                    newOrder.setId(cursor.getLong(cursor.getColumnIndex(ItemsDbContract.FeedEntry._ID)));

                    orders.add(newOrder);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return orders;
    }




}
