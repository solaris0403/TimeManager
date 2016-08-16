package com.tony.timemanager.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseFragment;
import com.tony.timemanager.ui.adapter.FragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.btn_todo)
    Button mBtnTodo;
    @BindView(R.id.btn_alarm)
    Button mBtnAlarm;
    @BindView(R.id.btn_article)
    Button mBtnArticle;

    private BaseFragment[] mFragments = new BaseFragment[3];

    private FragmentPagerAdapter mFragmentPagerAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        mFragments[0] = ToDoFragment.newInstance();
        mFragments[1] = AlarmFragment.newInstance();
        mFragments[2] = ArticleFragment.newInstance();
        mFragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager(), mFragments);
        mViewPager.setOffscreenPageLimit(mFragments.length);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mBtnTodo.setOnClickListener(this);
        mBtnAlarm.setOnClickListener(this);
        mBtnArticle.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_todo:
                mViewPager.setCurrentItem(0, true);
                break;
            case R.id.btn_alarm:
                mViewPager.setCurrentItem(1, true);
                break;
            case R.id.btn_article:
                mViewPager.setCurrentItem(2, true);
                break;
        }
    }
}
