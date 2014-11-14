package com.example.dherbric.testapp;

/**
 * Created on 10.11.2014.
 *
 * @author dherbric
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.neenbedankt.bundles.annotation.Argument;
import com.neenbedankt.bundles.annotation.Frozen;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    @Inject
    Bus bus;

    @Argument String text;
    @Argument(required = false) String text2;

    @Frozen
    int cnt=0;

    @InjectView(R.id.tv_info) TextView tvInfo;


    public PlaceholderFragment() {
    }

    @Override public void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TestApplication)getActivity().getApplication()).inject(this);
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlaceholderFragmentBuilder.injectArguments(this);
        PlaceholderFragmentState.restoreInstanceState(this, savedInstanceState);
        cnt++;
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PlaceholderFragmentState.saveInstanceState(this, outState);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvInfo.setText(text + " cnt = " + cnt + " text2 =" + text2);
    }

    @Subscribe public void onEvent(String event) {
        Toast.makeText(getActivity(), event, Toast.LENGTH_LONG).show();
    }
}
