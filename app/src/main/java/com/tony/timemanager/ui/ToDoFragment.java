package com.tony.timemanager.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseFragment;
import com.tony.timemanager.db.DBWrapper;
import com.tony.timemanager.db.Note;
import com.tony.timemanager.ui.adapter.ToDoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tony on 8/15/16.
 */
public class ToDoFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    @BindView(R.id.rv_todo)
    RecyclerView mRvTodo;
    @BindView(R.id.btn_add)
    Button mBtnAdd;

    private ToDoAdapter mAdapter;
    private List<com.tony.timemanager.module.Note> mList = new ArrayList<>();

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
        mBtnAdd.setOnClickListener(this);
        mAdapter = new ToDoAdapter(getActivity(), mList);
        mAdapter.setOnItemClickListener(onItemClickListener);
        mAdapter.setOnItemLongClickListener(onItemLongClickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvTodo.setLayoutManager(linearLayoutManager);
        mRvTodo.setHasFixedSize(true);
        mRvTodo.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this.getActivity(), Note.CONTENT_NOTE_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mList.clear();
        while (cursor.moveToNext()) {
            com.tony.timemanager.module.Note note = new com.tony.timemanager.module.Note();
            note.setNote_id(cursor.getString(cursor.getColumnIndex(Note.NOTE_ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(Note.TITLE)));
            note.setSnippet(cursor.getString(cursor.getColumnIndex(Note.SNIPPET)));
            note.setContent(cursor.getString(cursor.getColumnIndex(Note.CONTENT)));
            note.setCreated_date(cursor.getInt(cursor.getColumnIndex(Note.CREATED_DATE)));
            note.setModified_date(cursor.getInt(cursor.getColumnIndex(Note.MODIFIED_DATE)));
            note.setAlert_date(cursor.getInt(cursor.getColumnIndex(Note.ALERTED_DATE)));
            note.setType(cursor.getInt(cursor.getColumnIndex(Note.TYPE)));
            note.setVersion(cursor.getInt(cursor.getColumnIndex(Note.VERSION)));
            mList.add(note);
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
                Bundle bundle = new Bundle();
                com.tony.timemanager.module.Note note = (com.tony.timemanager.module.Note) data;
                bundle.putInt(ToDoDetailFragment.MODE_KEY, ToDoDetailFragment.MODE_PREVIEW);
                bundle.putParcelable("note", note);
                ToDoDetailFragment fragment = ToDoDetailFragment.newInstance();
                fragment.setArguments(bundle);
                getPreFragment().start(fragment);
            }
        }
    };

    private ToDoAdapter.OnItemLongClickListener onItemLongClickListener = new ToDoAdapter.OnItemLongClickListener() {
        @Override
        public void onItemLongClick(View view, int position, Object data) {
            final String note_id = ((com.tony.timemanager.module.Note) data).getNote_id();
            new MaterialDialog.Builder(ToDoFragment.this.getActivity())
                    .title(R.string.dialog_title)
                    .content(R.string.dialog_content_delete)
                    .positiveText(R.string.dialog_positive)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            DBWrapper.delete(getActivity(), note_id);
                        }
                    })
                    .negativeText(R.string.dialog_negative)
                    .show();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                if (getPreFragment() instanceof MainFragment) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ToDoDetailFragment.MODE_KEY, ToDoDetailFragment.MODE_ADD);
                    ToDoDetailFragment fragment = ToDoDetailFragment.newInstance();
                    fragment.setArguments(bundle);
                    getPreFragment().start(fragment);
                }
                break;
        }
    }
}
