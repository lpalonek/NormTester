package com.master.normtester.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by lpalonek on 27/10/15.
 */
public class App extends Application {

    private static App instance;
    public static App get() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.e("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
