package com.tony.timemanager.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tony.timemanager.R;
import com.tony.timemanager.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends BaseFragment {
    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

}
