package com.example.asus.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 23.07.2017.
 */

public class DbManager {
    DbHelper dbHelper;
    public DbManager(Context context){
        dbHelper = new DbHelper(context);
    }

    public List<Event> readAll(){
        List<Event> events = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DbHelper.FeedEntry._ID,
                DbHelper.FeedEntry.COLUMN_NAME_TEXT,
                DbHelper.FeedEntry.COLUMN_NAME_DATE,
                DbHelper.FeedEntry.COLUMN_NAME_TRASH

        };

        String selection = DbHelper.FeedEntry.COLUMN_NAME_TRASH+ " = ?";
        String[] selectionArgs = { "0" };

        Cursor cursor = db.query(
                DbHelper.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.FeedEntry._ID));
            String text = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.FeedEntry.COLUMN_NAME_TEXT));
            long date = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.FeedEntry.COLUMN_NAME_DATE));
            long crash = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.FeedEntry.COLUMN_NAME_TRASH));
            events.add(new Event(itemId, text, date, convertBoolean(crash)));
        }
        cursor.close();

        return events;

    }

    public void addEvent(Event event){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.FeedEntry.COLUMN_NAME_TEXT, event.getText());
        values.put(DbHelper.FeedEntry.COLUMN_NAME_DATE, event.getDate());
        values.put(DbHelper.FeedEntry.COLUMN_NAME_TRASH, convertLong(event.isTrash()));

        long newRowId = db.insert(DbHelper.FeedEntry.TABLE_NAME, null, values);
    }

    public void deleteEvent(long eventId){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = DbHelper.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(eventId)};
        db.delete(DbHelper.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }
    public void setTrash(long id, boolean value){
        updateTrash(id, convertLong(value));
    }

    public void updateTrash(long eventId, long trash){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.FeedEntry.COLUMN_NAME_TRASH, trash);

        String selection = DbHelper.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(eventId) };

        int count = db.update(
                DbHelper.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public long convertLong(boolean value){
        if(value){
            return 1;
        }else{
            return 0;
        }
    }

    public boolean convertBoolean(long value){
        if(value == 1){
            return true;
        }
        else{
            return false;
        }
    }
}
