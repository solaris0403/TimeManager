package com.tony.timemanager.module;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tony on 8/17/16.
 */
public class Note implements Parcelable {
    private String note_id;
    private String title;
    private String snippet;
    private String content;
    private int created_date;
    private int modified_date;
    private int alert_date;
    private int type;
    private int version;

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreated_date() {
        return created_date;
    }

    public void setCreated_date(int created_date) {
        this.created_date = created_date;
    }

    public int getModified_date() {
        return modified_date;
    }

    public void setModified_date(int modified_date) {
        this.modified_date = modified_date;
    }

    public int getAlert_date() {
        return alert_date;
    }

    public void setAlert_date(int alert_date) {
        this.alert_date = alert_date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.note_id);
        dest.writeString(this.title);
        dest.writeString(this.snippet);
        dest.writeString(this.content);
        dest.writeInt(this.created_date);
        dest.writeInt(this.modified_date);
        dest.writeInt(this.alert_date);
        dest.writeInt(this.type);
        dest.writeInt(this.version);
    }

    public Note() {
    }

    protected Note(Parcel in) {
        this.note_id = in.readString();
        this.title = in.readString();
        this.snippet = in.readString();
        this.content = in.readString();
        this.created_date = in.readInt();
        this.modified_date = in.readInt();
        this.alert_date = in.readInt();
        this.type = in.readInt();
        this.version = in.readInt();
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
