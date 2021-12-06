package com.anilpraharaj.newsbreeze.activity;

import com.anilpraharaj.newsbreeze.baseClass.BaseActivity;
import com.anilpraharaj.newsbreeze.baseClass.BaseFragment;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.anilpraharaj.newsbreeze.fragment.DashboardFragment;

/**
 * @author anilpraharaj on 05/12/21
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        BaseFragment fragment = new DashboardFragment();
        loadFragment(Constant.BASE_CONTAINER_ID, fragment, DashboardFragment.class.getSimpleName());
    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void onRefresh() {

    }

    @Override
    protected void onResumeCall() {

    }

    @Override
    protected int getContentView() {
        return Constant.MAIN_ACTIVITY_LAYOUT_ID;
    }
}