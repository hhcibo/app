package com.cibo.android.cibo.model;

/**
 * Created by gerogerke on 23.09.17.
 */

public class PastTravel {

    public String fromStation;
    public String startTime;
    public String endStation;
    public String endTime;
    public String cost;

    public PastTravel(String fromStation, String startTime, String endStation, String endTime, String cost) {
        this.fromStation = fromStation;
        this.startTime = startTime;
        this.endStation = endStation;
        this.endTime = endTime;
        this.cost = cost;
    }

}
