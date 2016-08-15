package com.tony.timemanager.ui;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseActivity;
import com.tony.timemanager.db.Notes;

public class MainActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private TextView mTextView;
    private Button mBtnInsert, mBtnQuery, mBtnDelete;
    Uri uri = Notes.CONTENT_NOTE_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLoaderManager().initLoader(0, null, this);
        mTextView = (TextView) findViewById(R.id.textView);
        mBtnInsert = (Button) findViewById(R.id.btn_insert);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert("ssssssssssssss");
            }
        });
        mBtnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private void insert(String string) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notes.CONTENT, string);
        getContentResolver().insert(uri, contentValues);
    }

    private void query() {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.i("main", "-------------->" + cursor.getString(cursor.getColumnIndex(Notes.CONTENT)));
        }
    }

    private void delete() {
        getContentResolver().delete(uri, null, null);
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i("main", "-------------->onLoadFinished");
        while (cursor.moveToNext()) {
            Log.i("main", "-------------->" + cursor.getString(cursor.getColumnIndex(Notes.CONTENT)));
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
}
