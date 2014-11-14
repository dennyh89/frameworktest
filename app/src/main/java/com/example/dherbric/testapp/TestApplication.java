package com.example.dherbric.testapp;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created on 11.11.2014.
 *
 * @author dherbric
 */
public class TestApplication extends Application {

    ObjectGraph graph;
    @Override public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(new MainModule());
    }

    public void inject(Object target){
        graph.inject(target);
    }

}
