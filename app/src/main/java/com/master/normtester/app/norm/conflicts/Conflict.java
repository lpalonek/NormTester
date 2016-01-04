package com.master.normtester.app.norm.conflicts;


import com.master.normtester.app.norm.NormPriority;

import java.util.Objects;

/**
 * Created by lpalonek on 27/10/15.
 */
public class Conflict <T>{
    private ConflictSource conflictSource;
    private Class<T> clazz;
    private NormPriority priority;

    public Conflict(ConflictSource conflictSource, NormPriority priority) {
        this.conflictSource = conflictSource;
        this.priority = priority;
    }

    public ConflictSource getConflictSource() {
        return conflictSource;
    }

    public void setConflictSource(ConflictSource conflictSource) {
        this.conflictSource = conflictSource;
    }

    public NormPriority getPriority() {
        return priority;
    }

    public void setPriority(NormPriority priority) {
        this.priority = priority;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conflict<?> conflict = (Conflict<?>) o;
        return Objects.equals(clazz, conflict.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz);
    }
}
