package com.tony.timemanager.ui;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseFragment;
import com.tony.timemanager.db.DBWrapper;
import com.tony.timemanager.db.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tony on 8/16/16.
 */
public class ToDoDetailFragment extends BaseFragment implements View.OnClickListener {
    public static final String MODE_KEY = "mode";
    public static final int MODE_PREVIEW = 1;//查看
    public static final int MODE_ADD = 2;//添加
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.edt_title)
    EditText mEdtTitle;
    @BindView(R.id.edt_content)
    EditText mEdtContent;
    @BindView(R.id.btn_save)
    Button mBtnSave;
    private int mMode;
    private com.tony.timemanager.module.Note mNote;

    public static ToDoDetailFragment newInstance() {
        return new ToDoDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMode = getArguments().getInt(MODE_KEY);
        mNote = getArguments().getParcelable("note");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo_detail, container, false);
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {
        switch (mMode){
            case MODE_ADD:
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                mTvTime.setText(sdf.format(cal.getTime()));
                break;
            case MODE_PREVIEW:
                mTvTime.setText(String.valueOf(mNote.getCreated_date()));
                mEdtTitle.setText(mNote.getTitle());
                mEdtContent.setText(mNote.getContent());
                break;
        }
        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                switch (mMode){
                    case MODE_ADD:
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Note.TITLE, mEdtTitle.getText().toString());
                        contentValues.put(Note.CONTENT, mEdtContent.getText().toString());
                        DBWrapper.insert(getActivity(), contentValues);
                        pop();
                        break;
                    case MODE_PREVIEW:
                        contentValues = new ContentValues();
                        contentValues.put(Note.TITLE, mEdtTitle.getText().toString());
                        contentValues.put(Note.CONTENT, mEdtContent.getText().toString());
                        DBWrapper.update(getActivity(), contentValues, mNote.getNote_id());
                        pop();
                        break;
                }
                break;
        }
    }
}
