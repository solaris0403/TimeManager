package com.tony.timemanager.db;

import android.net.Uri;

/**
 * Created by tony on 8/15/16.
 */
public interface Note {
    public static final Uri CONTENT_NOTE_URI = Uri.parse("content://" + NotesContentProvider.AUTHORITY + "/note");
    public static final String ID = "_id";//主键
    public static final String NOTE_ID = "note_id";//记录id
    public static final String TITLE = "title";//标题
    public static final String SNIPPET = "snippet";//摘要
    public static final String CONTENT = "content";//内容
    public static final String CREATED_DATE = "created_date";//创建时间
    public static final String MODIFIED_DATE = "modified_date";//修改时间
    public static final String ALERTED_DATE = "alert_date";//提示时间
    public static final String TYPE = "type";//类型
    public static final String VERSION = "version";//版本
    public static final String BG_COLOR_ID = "bg_color_id";//颜色
    public static final String SYNC_ID = "sync_id";//同步key
}
