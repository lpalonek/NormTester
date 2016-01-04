package com.master.normtester.app.norm.compute;

/**
 * Created by lpalonek on 14/11/15.
 */
public enum ProblemSize {

    SMALL, AVERAGE, BIG;

    public static ProblemSize calculateProblemSize(int lines){
        if(lines < 3){
            return SMALL;
        }else if(lines < 5){
            return AVERAGE;
        }else{
            return BIG;
        }
    }
}
