package com.tony.timemanager.ui;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity{
    @BindView(R.id.layout_container)
    FrameLayout mLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.layout_container, MainFragment.newInstance());
        }
    }
}
