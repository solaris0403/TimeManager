package com.tony.timemanager.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity{
    @BindView(R.id.layout_container)
    FrameLayout mLayoutContainer;

    private Fragment mToDoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToDoFragment = new ToDoFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.layout_container, mToDoFragment).commit();

//        getLoaderManager().initLoader(0, null, this);
//        mTextView = (TextView) findViewById(R.id.textView);
//        mBtnInsert = (Button) findViewById(R.id.btn_insert);
//        mBtnQuery = (Button) findViewById(R.id.btn_query);
//        mBtnDelete = (Button) findViewById(R.id.btn_delete);
//        mBtnInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                insert("ssssssssssssss");
//            }
//        });
//        mBtnQuery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                query();
//            }
//        });
//        mBtnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                delete();
//            }
//        });
    }

}
