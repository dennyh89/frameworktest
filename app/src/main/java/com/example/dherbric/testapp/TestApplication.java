package com.example.dherbric.testapp;

import android.app.Application;

/**
 * Created on 11.11.2014.
 *
 * @author dherbric
 */
public class TestApplication extends Application {

    MainComponent component;

    @Override public void onCreate() {
        super.onCreate();
        component = Dagger_MainComponent.builder().mainModule(new MainModule()).build();
    }

    public MainComponent getComponent() {
        return component;
    }
}
