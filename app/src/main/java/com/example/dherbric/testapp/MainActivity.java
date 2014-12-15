package com.example.dherbric.testapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.neenbedankt.bundles.annotation.Frozen;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;

import java.util.HashMap;
import java.util.Random;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    private static final String FR_TAG = "TAG";
    private static final String ARG = "text";
    @Inject Bus bus;
    private Random r = new Random();

    @OnClick(R.id.bt_change_args) void changeArgs() {
        getSupportFragmentManager().findFragmentByTag(FR_TAG).getArguments().putString(ARG,
                "Text " + r.nextInt());
        bus.post(produceEvent());
    }

    @Override protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Frozen
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TestApplication) getApplication()).inject(this);

        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragmentBuilder("initial text").text2("bla text2").build(),
                            FR_TAG)
                    .commit();
            car = new Car();
            car.setName("My Car");
            car.setType("Audi A3");
            car.setProperties(new HashMap<String, String>() {{
                put("Key1", "Val1");
            }});
        }
        MainActivityState.restoreInstanceState(this, savedInstanceState);
        Log.d("Bla", "Car = " + car);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MainActivityState.saveInstanceState(this, outState);
    }


    @Produce public String produceEvent() {
        return "TestEvent " + r.nextLong();
    }


}
