package com.tony.timemanager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by tony on 8/15/16.
 */
public class NotesDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "NotesDBHelper";
    private static final String DB_NAME = "note.db";
    private static final int DB_VERSION = 1;

    private static NotesDBHelper mInstance;

    public static synchronized NotesDBHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NotesDBHelper(context);
        }
        return mInstance;
    }

    public interface TABLE {
        public static final String NOTE = "note";
    }

    public NotesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final String CREATE_NOTE_TABLE_SQL =
            "CREATE TABLE " + TABLE.NOTE + "(" +
                    Note.ID + " INTEGER PRIMARY KEY, " +
                    Note.NOTE_ID + " TEXT NOT NULL DEFAULT '', " +
                    Note.TITLE + " TEXT NOT NULL DEFAULT '', " +
                    Note.SNIPPET + " TEXT NOT NULL DEFAULT '', " +
                    Note.CONTENT + " TEXT NOT NULL DEFAULT '', " +
                    Note.CREATED_DATE + " INTEGER NOT NULL DEFAULT (strftime('%s','now') * 1000), " +
                    Note.MODIFIED_DATE + " INTEGER NOT NULL DEFAULT (strftime('%s','now') * 1000), " +
                    Note.ALERTED_DATE + " INTEGER NOT NULL DEFAULT 0," +
                    Note.TYPE + " INTEGER NOT NULL DEFAULT 0, " +
                    Note.VERSION + " INTEGER NOT NULL DEFAULT 0" +
                    ")";

    private void createNoteTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTE_TABLE_SQL);
        Log.d(TAG, "note table has been created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createNoteTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
