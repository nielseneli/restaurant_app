import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.margaret.gudfud.ItemsDbContract;

/**
 * A Database Helper for menu items
 */
public class ItemsDbHelper extends SQLiteOpenHelper {
    private static final String TAG = ""; //dunno for now
    private static final String TEXT_TYPE = " TEXT";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ItemsDbContract.FeedEntry.TABLE_NAME + " (" +
                    ItemsDbContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    ItemsDbContract.FeedEntry.COLUMN_NAME_ITEM + TEXT_TYPE +
                    ItemsDbContract.FeedEntry.COLUMN_NAME_INGREDIENTS + " )";
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



}
