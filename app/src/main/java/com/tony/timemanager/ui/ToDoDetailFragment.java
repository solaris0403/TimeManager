package com.tony.timemanager.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tony on 8/16/16.
 */
public class ToDoDetailFragment extends BaseFragment {
    @BindView(R.id.btn_edit)
    Button mBtnEdit;
    @BindView(R.id.edt_content)
    EditText mEdtContent;

    public static ToDoDetailFragment newInstance() {
        return new ToDoDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_todo_detail, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }
}
