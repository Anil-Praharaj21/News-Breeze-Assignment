package com.anilpraharaj.newsbreeze.baseClass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anilpraharaj.newsbreeze.callback.BackPressedCb;

/**
 * @author anilpraharaj on 05/12/21
 */
public abstract class BaseFragment extends Fragment implements BackPressedCb {

    View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = mRootView == null ? inflater.inflate(getContentView(), null) : mRootView;
        return mRootView;
    }

    /**
     * Initial Method - On start of the Fragment, this method will be called
     *
     * Initialize all variables if needed here or set id to Views
     */
    protected abstract void init(View view);

    /**
     * Add all callback listeners here if needed
     */
    protected abstract void addListeners();

    /**
     * Set all method inside when required to refresh the screen data
     */
    protected abstract void onRefresh();

    /**
     * This method is called during on Resume of the activity lifecycle is called
     */
    protected abstract void onResumeCall();

    /**
     * Add XML layout
     *
     * @return int
     */
    protected abstract int getContentView();

    /**
     * Called on system back button is pressed
     *
     * @return boolean (State says if the backtrack should be performed or not)
     */
    protected abstract boolean onBackPressed();

    /**
     * Get all saved instance bundle
     */
    public void onSavedInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
        addListeners();
        if (savedInstanceState != null) {
            onSavedInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        onResumeCall();
    }


    @Override
    public boolean onBackPressedState() {
        return onBackPressed();
    }
}
