package com.example.monitor.model;

public class Station {
    private String name;
    private int stationNumber;

    public Station(String name, int stationNumber, float nextStationDis) {
        this.name = name;
        this.stationNumber = stationNumber;
        this.nextStationDis = nextStationDis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public float getNextStationDis() {
        return nextStationDis;
    }

    public void setNextStationDis(float nextStationDis) {
        this.nextStationDis = nextStationDis;
    }

    private float nextStationDis;
}
