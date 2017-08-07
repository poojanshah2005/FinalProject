package com.example.shahp.finalproject;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Base64;

import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.security.SecureRandom;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by shahp on 28/07/2017.
 */

public class MyApplication extends Application {
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String KEY = "KEY";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);



        Realm.init(getApplicationContext());
        byte[] key;
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        /**
         * for storing and getting Realm Key, with exposing it to the outside world.
         */
        if(!settings.contains(KEY)){
            /**
             * setting Realm key
             */
            key = new byte[64];
            new SecureRandom().nextBytes(key);
            String saveThis = Base64.encodeToString(key, Base64.DEFAULT);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(KEY,saveThis);
            editor.commit();
        } else{
            /**
             * getting Realm key
             */
            String stringFromSharedPrefs = settings.getString(KEY,"");
            key = Base64.decode(stringFromSharedPrefs, Base64.DEFAULT);
        }


        /**
         * RealmConfiguration
         */
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .encryptionKey(key)
                .deleteRealmIfMigrationNeeded()
                .build();
        // Commit the edits!

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}