package com.example.antojitos;

import android.app.Application;

import com.facebook.FacebookSdk;

import com.facebook.appevents.AppEventsLogger;

public class AntojitosLolisApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
