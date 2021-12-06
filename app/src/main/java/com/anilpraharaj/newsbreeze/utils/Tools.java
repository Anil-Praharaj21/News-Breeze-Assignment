package com.anilpraharaj.newsbreeze.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.anilpraharaj.newsbreeze.NewsBreezeApplication;
import com.anilpraharaj.newsbreeze.R;
import com.anilpraharaj.newsbreeze.constant.Constant;
import com.google.android.material.snackbar.Snackbar;

/**
 * @author anilpraharaj on 05/12/21
 */
public class Tools {

    /**
     * State Method to check if the internet connection is there or not
     */
    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
