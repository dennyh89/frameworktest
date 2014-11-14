package com.example.dherbric.testapp;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created on 11.11.2014.
 *
 * @author dherbric
 */
public class BusProvider {

    private static Bus INSTANCE = new Bus("TestBus");

    public static Bus getBus(){
        return INSTANCE;
    }
    public static Bus getMainBus(){
        return new Bus(ThreadEnforcer.MAIN);
    }

    private BusProvider() {
    }
}
