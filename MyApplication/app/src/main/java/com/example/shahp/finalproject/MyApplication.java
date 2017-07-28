package com.example.shahp.finalproject;

import android.app.Application;

import com.mapbox.mapboxsdk.Mapbox;

/**
 * Created by shahp on 28/07/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), "pk.eyJ1IjoicG9vamFuIiwiYSI6ImNqNW1oMDlzNzBxajEyeHJ3N28yanVqNzQifQ.o2l67-t1c_OLzKcPIsr68g");
    }
}