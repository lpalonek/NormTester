package com.master.normtester.app.norm.conflicts;

/**
 * Created by lpalonek on 06/08/15.
 */
public interface ConflictSource {

    enum Wifi implements ConflictSource {
        ENABLED, DISABLED
    }

    enum Compute implements ConflictSource{
        LOCAL, CLOUD, DELAYED
    }

}
