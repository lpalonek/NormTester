package com.master.normtester.app.norm.components;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.master.normtester.app.App;
import com.master.normtester.app.norm.conflicts.ConflictSource;

/**
 * Created by lpalonek on 27/10/15.
 */
public class Wifi {

    private static boolean enabled;

    public static void wifiState(ConflictSource.Wifi conflicts) {
        switch (conflicts) {
            case DISABLED:
                disable();
                break;
            case ENABLED:
                enable();
                break;
        }
    }

    public static void enable() {
        enable(false);
    }

    public static void disable() {
        enable(true);
    }

    public static boolean isEnabled() {
        return enabled;
    }

    private static void enable(boolean enabled) {
        Wifi.enabled = enabled;
        WifiManager wifiManager = (WifiManager) App.get().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(enabled);
        }
    }
}
