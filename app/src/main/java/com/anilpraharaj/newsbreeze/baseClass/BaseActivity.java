package com.anilpraharaj.newsbreeze.baseClass;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.anilpraharaj.newsbreeze.callback.BackPressedCb;
import com.anilpraharaj.newsbreeze.utils.Tools;

import java.util.List;

/**
 * @author anilpraharaj on 05/12/21
 */
public abstract class BaseActivity extends AppCompatActivity {

    private BackPressedCb mBackPressedCallback; // Callback of Back Press For fragments

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        init();
        addListeners();
    }

    /**
     * Initial Method - On start of the Activity this method will be called
     *
     * Initialize all variables if needed here or set id to Views
     */
    protected abstract void init();

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

    @Override
    protected void onResume() {
        super.onResume();

        onResumeCall();
    }

    /**
     * Loading Fragments to stack
     *
     * @param containerId
     * @param fragment
     * @param tag
     */
    public void loadFragment(int containerId, BaseFragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment);
        if (tag != null) {
            fragmentTransaction.addToBackStack(tag);
        } else {
            getSupportFragmentManager().popBackStack();
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        getFragment();
        if (mBackPressedCallback == null || mBackPressedCallback.onBackPressedState()) {
            super.onBackPressed();
        }
    }

    /**
     * Set Back Pressed Callback for fragments
     *
     * @param mBackPressedCallback
     */
    public void setmBackPressedCallback(BackPressedCb mBackPressedCallback) {
        this.mBackPressedCallback = mBackPressedCallback;
    }

    /**
     * Get top fragment from the stack and assign the back pressed callback
     */
    private void getFragment() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            setmBackPressedCallback(((BaseFragment) fragments.get(fragments.size() - 1)));
        }
    }
}
