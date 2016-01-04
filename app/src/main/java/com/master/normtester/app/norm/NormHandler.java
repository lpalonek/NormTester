package com.master.normtester.app.norm;

import com.master.normtester.app.norm.conflicts.Conflict;
import com.master.normtester.app.norm.conflicts.ConflictResolver;
import com.master.normtester.app.norm.norms.ChargingNorm;
import com.master.normtester.app.norm.norms.CriticalBatteryNorm;
import com.master.normtester.app.norm.norms.LowBatteryNorm;
import com.master.normtester.app.norm.norms.NoInternetConnectionNorm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lpalonek on 06/08/15.
 */
public class NormHandler {

    private Set<Norm> norms;

    public NormHandler(){
        initializeNorms();
    }

    private void initializeNorms() {
        norms = new HashSet<>();
        norms.add(new ChargingNorm());
        norms.add(new LowBatteryNorm());
        norms.add(new CriticalBatteryNorm());
        norms.add(new NoInternetConnectionNorm());
    }

    public Set<Conflict> applyNorms(){
        Set<Norm> fullifiedNorms = findFullifiedNorms();
        return ConflictResolver.resolveConflicts(fullifiedNorms);
    }

    private Set<Norm> findFullifiedNorms() {
        Set<Norm> fullifiedNorms = new HashSet<>();
        for (Norm norm : norms){
            if( norm.conditionsMet()){
                fullifiedNorms.add(norm);
            }
        }
        return fullifiedNorms;
    }
}
