package com.tony.timemanager.ui;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseFragment;
import com.tony.timemanager.db.Notes;
import com.tony.timemanager.ui.adapter.ToDoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tony on 8/15/16.
 */
public class ToDoFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    private static final Uri uri = Notes.CONTENT_NOTE_URI;
    @BindView(R.id.rv_todo)
    RecyclerView mRvTodo;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private ToDoAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    public static ToDoFragment newInstance() {
        return new ToDoFragment();
    }

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
        mAdapter = new ToDoAdapter(getActivity(), mList);
        mAdapter.setOnItemClickListener(onItemClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTodo.setLayoutManager(linearLayoutManager);
        mRvTodo.setHasFixedSize(true);
        mRvTodo.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this.getActivity(), uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mList.clear();
        while (cursor.moveToNext()) {
            mList.add(cursor.getString(cursor.getColumnIndex(Notes.CONTENT)));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private ToDoAdapter.OnItemClickListener onItemClickListener = new ToDoAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position, Object data) {
            if (getPreFragment() instanceof MainFragment) {
                getPreFragment().start(ToDoDetailFragment.newInstance());
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_add:
//                ContentValues contentValues = new ContentValues();
//                contentValues.put(Notes.CONTENT, "ssssssssssss");
//                getActivity().getContentResolver().insert(uri, contentValues);
//                break;
        }
    }
}
