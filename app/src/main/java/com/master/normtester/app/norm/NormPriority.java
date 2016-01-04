package com.master.normtester.app.norm;

/**
 * Created by lpalonek on 07/08/15.
 */
public enum NormPriority {

    VERY_LOW(1),
    LOW(2),
    AVERAGE(3),
    HIGH(4),
    VERY_HIGH(5);

    private int value;
    NormPriority(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
