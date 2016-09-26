package com.tony.timemanager.db;

import android.content.ContentValues;
import android.content.Context;

import com.tony.timemanager.util.UUIDUtils;

/**
 * Created by tony on 8/16/16.
 */
public class DBWrapper {
    public static void insert(Context context, ContentValues contentValues) {
        contentValues.put(Note.NOTE_ID, UUIDUtils.getUUID());
        context.getContentResolver().insert(Note.CONTENT_NOTE_URI, contentValues);
    }

    public static void delete(Context context, String note_id) {
        String whereClause = Note.NOTE_ID + " = ?";
        String[] selectionArgs = new String[]{note_id};
        context.getContentResolver().delete(Note.CONTENT_NOTE_URI, whereClause, selectionArgs);
    }

    public static void update(Context context, ContentValues contentValues, String note_id) {
        String whereClause = Note.NOTE_ID + " = ?";
        String[] selectionArgs = new String[]{note_id};
        context.getContentResolver().update(Note.CONTENT_NOTE_URI, contentValues, whereClause, selectionArgs);
    }

    public static void query(Context context, String[] strings, String s, String[] strings1, String s1) {
//        context.getContentResolver().query(Note.CONTENT_NOTE_URI, contentValues, whereClause, selectionArgs);
    }
}
