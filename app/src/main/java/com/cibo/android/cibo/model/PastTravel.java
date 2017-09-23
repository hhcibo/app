package com.cibo.android.cibo.model;

/**
 * Created by gerogerke on 23.09.17.
 */

public class PastTravel {

    public String fromStation;
    public long startTime;
    public String endStation;
    public long endTime;
    public int cost;
    public String line;
    public String lineColor;
    public int travelLength;
    public int stations;

    public PastTravel(String fromStation, long startTime, String endStation, long endTime, int cost, String line, String lineColor, int travelLength, int stations) {
        this.fromStation = fromStation;
        this.startTime = startTime;
        this.endStation = endStation;
        this.endTime = endTime;
        this.cost = cost;
        this.line = line;
        this.lineColor = lineColor;
        this.travelLength = travelLength;
        this.stations = stations;
    }

}
