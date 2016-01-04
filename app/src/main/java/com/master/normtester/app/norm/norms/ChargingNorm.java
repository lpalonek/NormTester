package com.master.normtester.app.norm.norms;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import com.master.normtester.app.App;
import com.master.normtester.app.norm.Norm;
import com.master.normtester.app.norm.NormPriority;
import com.master.normtester.app.norm.conflicts.ConflictSource;

/**
 * Created by lpalonek on 28/10/15.
 */
public class ChargingNorm extends Norm {

    public ChargingNorm() {
        super.setPriority(NormPriority.VERY_HIGH);
    }

    @Override
    public void behavior() {
        super.conflicts.add(ConflictSource.Wifi.ENABLED);
        super.conflicts.add(ConflictSource.Compute.CLOUD);
    }

    @Override
    public boolean conditionsMet() {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = App.get().registerReceiver(null, ifilter);
        int plugged = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == BatteryManager.BATTERY_PLUGGED_AC
                || plugged == BatteryManager.BATTERY_PLUGGED_USB
                || plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS;
    }
}
