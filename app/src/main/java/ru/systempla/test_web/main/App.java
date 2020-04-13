package ru.systempla.test_web.main;

import android.app.Application;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        Timber.plant(new Timber.DebugTree());
        super.onCreate();
    }
}
