package com.tony.timemanager.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseFragment;
import com.tony.timemanager.db.Notes;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tony on 8/15/16.
 */
public class ToDoFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    private static final Uri uri = Notes.CONTENT_NOTE_URI;
    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.rv_todo)
    RecyclerView mRvTodo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo, container, false);
        ButterKnife.bind(this, rootView);
        mBtnAdd.setOnClickListener(this);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this.getActivity(), uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.i("main", "-------------->onLoadFinished");
        while (cursor.moveToNext()) {
            Log.i("main", "-------------->" + cursor.getString(cursor.getColumnIndex(Notes.CONTENT)));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(View view) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Notes.CONTENT, "ssssssssssss");
            getActivity().getContentResolver().insert(uri, contentValues);
    }
}
