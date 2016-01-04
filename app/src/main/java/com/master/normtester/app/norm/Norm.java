package com.master.normtester.app.norm;


import com.master.normtester.app.norm.conflicts.ConflictSource;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by lpalonek on 06/08/15.
 */
public abstract class Norm {

    private NormPriority priority;

    protected Set<ConflictSource> conflicts = new HashSet<>();

    public abstract void behavior();

    public abstract boolean conditionsMet();

    public NormPriority getPriority() {
        return priority;
    }

    public void setPriority(NormPriority priority) {
        this.priority = priority;
    }

    public Set<ConflictSource> getConflicts() {
        return conflicts;
    }

    public void setConflicts(Set<ConflictSource> conflicts) {
        this.conflicts = conflicts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Norm norm = (Norm) o;
        return Objects.equals(priority, norm.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priority);
    }
}
