package com.master.normtester.app.norm.norms;

import android.util.Log;
import com.master.normtester.app.norm.Norm;
import com.master.normtester.app.norm.NormPriority;
import com.master.normtester.app.norm.components.Wifi;
import com.master.normtester.app.norm.conflicts.ConflictSource;

import java.io.IOException;

/**
 * Created by lpalonek on 28/10/15.
 */
public class NoInternetConnectionNorm extends Norm {

    public NoInternetConnectionNorm() {
        super.setPriority(NormPriority.HIGH);
    }

    @Override
    public void behavior() {
        super.conflicts.add(ConflictSource.Compute.LOCAL);
    }

    @Override
    public boolean conditionsMet() {
        return Wifi.isEnabled() && !isOnline();
    }

    private boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try

        {

            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException |
                InterruptedException e
                )

        {
            Log.e(NoInternetConnectionNorm.class.getName(), "Error occured during connection check", e);
        }

        return false;
    }
}
