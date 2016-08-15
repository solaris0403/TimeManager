package com.tony.timemanager.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.tony.timemanager.db.NotesDBHelper.TABLE;

/**
 * Created by tony on 8/15/16.
 */
public class NotesContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.tony.timemanager.db.NotesContentProvider";

    private static final UriMatcher mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private NotesDBHelper mDBHelper;

    private static final int URI_NOTE = 1;

    static {
        mMatcher.addURI(AUTHORITY, "note", URI_NOTE);
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new NotesDBHelper(getContext());
        return mDBHelper != null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor = null;
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        switch (mMatcher.match(uri)) {
            case URI_NOTE:
                cursor = db.query(TABLE.NOTE, strings, s, strings1, null, null, s1);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long insertedId = 0;
        switch (mMatcher.match(uri)) {
            case URI_NOTE:
                insertedId = db.insert(TABLE.NOTE, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (insertedId > 0){
            getContext().getContentResolver().notifyChange(ContentUris.withAppendedId(uri, insertedId), null);
        }
        return ContentUris.withAppendedId(uri, insertedId);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int count = 0;
        switch (mMatcher.match(uri)){
            case URI_NOTE:
                count = db.delete(TABLE.NOTE, s, strings);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int count = 0;
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        switch (mMatcher.match(uri)){
            case URI_NOTE:
                count = db.update(TABLE.NOTE, contentValues, s, strings);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }
}
