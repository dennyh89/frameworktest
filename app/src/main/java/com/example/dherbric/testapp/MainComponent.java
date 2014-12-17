package com.example.dherbric.testapp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 17.12.2014.
 *
 * @author dherbric
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
    void inject(PlaceholderFragment placeholderFragment);
}
