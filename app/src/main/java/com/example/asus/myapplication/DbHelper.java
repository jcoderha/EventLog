package com.example.asus.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by asus on 23.07.2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_TEXT + " TEXT," +
                    FeedEntry.COLUMN_NAME_DATE + " INTEGER," +
                    FeedEntry.COLUMN_NAME_TRASH + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public DbHelper(Context context) {
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

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME_TEXT = "text";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TRASH = "trash";
    }
}