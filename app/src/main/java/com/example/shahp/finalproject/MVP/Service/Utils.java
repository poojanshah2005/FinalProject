package com.example.shahp.finalproject.MVP.Service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

/**
 * Created by shahp on 07/08/2017.
 */

/**
 * used is to check if network is available
 */
public class Utils {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
