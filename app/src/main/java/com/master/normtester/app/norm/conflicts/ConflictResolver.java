package com.master.normtester.app.norm.conflicts;

import com.google.common.collect.ImmutableSet;
import com.master.normtester.app.norm.Norm;

import java.util.Iterator;
import java.util.Set;

public class ConflictResolver {
    private ConflictResolver() {
    }

    public static Set<Conflict> resolveConflicts(Set<Norm> fullifiedNorms) {
        ImmutableSet<Conflict> conflicts = new ImmutableSet.Builder<Conflict>().build();
        for (Norm norm : fullifiedNorms) {
            norm.behavior();
            for (ConflictSource conflictSource : norm.getConflicts()) {
                Conflict conflict = new Conflict(conflictSource, norm.getPriority());
                conflict.setClazz(conflictSource.getClass());
                if (conflicts.contains(conflict)) {
                    Iterator<Conflict> oldConflicts = conflicts.iterator();
                    while (oldConflicts.hasNext()) {
                        Conflict oldConflict = oldConflicts.next();
                        if (oldConflict.getPriority().getValue() < conflict.getPriority().getValue()) {
                            conflicts = new ImmutableSet.Builder<Conflict>().addAll(conflicts).build();
                            conflicts = new ImmutableSet.Builder<Conflict>().addAll(conflicts).add(conflict).build();
                        }
                    }
                } else {
                    conflicts = new ImmutableSet.Builder<Conflict>().addAll(conflicts).add(conflict).build();
                }
            }
        }
        return conflicts;
    }
}