package com.tony.timemanager.db;

import android.net.Uri;

/**
 * Created by tony on 8/15/16.
 */
public class Notes {
    /**
     * Uri to query all notes and folders
     */
    public static final Uri CONTENT_NOTE_URI = Uri.parse("content://" + NotesContentProvider.AUTHORITY + "/note");
    /**
     * The unique ID for a row
     * <P> Type: INTEGER (long) </P>
     */
    public static final String ID = "_id";

    /**
     * Data's content
     * <P> Type: TEXT </P>
     */
    public static final String CONTENT = "content";
}
