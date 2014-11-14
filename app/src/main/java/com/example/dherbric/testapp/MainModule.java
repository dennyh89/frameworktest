package com.example.dherbric.testapp;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 11.11.2014.
 *
 * @author dherbric
 */
@Module(
        injects = {MainActivity.class, PlaceholderFragment.class}
)
public class MainModule {
    @Provides @Singleton Bus provideBus(){
        return new Bus("TestBus");
    }
}
