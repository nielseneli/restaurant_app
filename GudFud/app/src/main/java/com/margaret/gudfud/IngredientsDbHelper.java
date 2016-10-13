package com.margaret.gudfud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Helper for ingredients database, which contains all possible ingredients that can be added.
 */
public class IngredientsDbHelper extends SQLiteOpenHelper{
    private static final String TAG = "IngredientsDbHelper";
    private static final String TEXT_TYPE = " TEXT";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + IngredientsDbContract.FeedEntry.TABLE_NAME + " (" +
                    IngredientsDbContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    IngredientsDbContract.FeedEntry.COLUMN_NAME_INGREDIENT + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + IngredientsDbContract.FeedEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public IngredientsDbHelper (Context context) {
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

    public ArrayList<Ingredient> getAllIngredients() {
        ArrayList<Ingredient> tasks = new ArrayList<>();

        String POSTS_SELECT_QUERY =
                "SELECT * FROM " + IngredientsDbContract.FeedEntry.TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    Ingredient newIngredient = new Ingredient();
                    newIngredient.setIng(cursor.getString(cursor.getColumnIndex(IngredientsDbContract.FeedEntry.COLUMN_NAME_INGREDIENT)));
                    newIngredient.setIngId(cursor.getLong(cursor.getColumnIndex(IngredientsDbContract.FeedEntry._ID)));

                    tasks.add(newIngredient);
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

    public void deleteIng (Ingredient ing) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(IngredientsDbContract.FeedEntry.TABLE_NAME, IngredientsDbContract.FeedEntry._ID
                    + "=" + ing.getIngId(), null);
    }

    public void editIng (Ingredient ing, String editString) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(IngredientsDbContract.FeedEntry._ID, ing.getIngId());
        args.put(IngredientsDbContract.FeedEntry.COLUMN_NAME_INGREDIENT, editString);
        db.update(IngredientsDbContract.FeedEntry.TABLE_NAME, args, IngredientsDbContract.FeedEntry._ID + "=" + ing.getIngId(), null);
    }
}
