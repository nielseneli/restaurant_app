package com.margaret.gudfud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * A Database Helper for menu items
 */
public class ItemsDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "ItemsDbHelper"; //dunno for now
    private static final String TEXT_TYPE = " TEXT";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ItemsDbContract.FeedEntry.TABLE_NAME + " (" +
                    ItemsDbContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ItemsDbContract.FeedEntry.COLUMN_NAME_ITEM + TEXT_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ItemsDbContract.FeedEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public ItemsDbHelper (Context context) {
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

    public ArrayList<MenuItem> getAllItems() {
        ArrayList<MenuItem> tasks = new ArrayList<>();
        //https://github.com/codepath/android_guides/wiki/Local-Databases-with-SQLiteOpenHelper

        // SELECT * FROM POSTS
        String POSTS_SELECT_QUERY =
                "SELECT * FROM " + ItemsDbContract.FeedEntry.TABLE_NAME;

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    MenuItem newItem = new MenuItem();
                    newItem.setName(cursor.getString(cursor.getColumnIndex(ItemsDbContract.FeedEntry.COLUMN_NAME_ITEM)));
                    newItem.setId(cursor.getLong(cursor.getColumnIndex(ItemsDbContract.FeedEntry._ID)));

                    tasks.add(newItem);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return tasks;
    }

    public boolean deleteTask(MenuItem item) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(ItemsDbContract.FeedEntry.TABLE_NAME , ItemsDbContract.FeedEntry._ID
                + "=" + item.getId(), null) > 0;
    }

    public boolean editTask(MenuItem item, String editstring) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ItemsDbContract.FeedEntry._ID, item.getId());
        args.put(ItemsDbContract.FeedEntry.COLUMN_NAME_ITEM, editstring);
        return db.update(ItemsDbContract.FeedEntry.TABLE_NAME, args, ItemsDbContract.FeedEntry._ID + "=" + item.getId(), null) > 0;
    }


}