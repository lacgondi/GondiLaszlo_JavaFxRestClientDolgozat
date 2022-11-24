package com.example.gondilaszlo_javafxrestclient;

public class Bus {
    private int ID;
    private String busID;
    private int delay;
    private String destination;

    public Bus(int ID, String busID, int delay, String destination) {
        this.ID = ID;
        this.busID = busID;
        this.delay = delay;
        this.destination = destination;
    }

    public int getID() {
        return ID;
    }

    public String getBusID() {
        return busID;
    }

    public int getDelay() {
        return delay;
    }

    public String getDestination() {
        return destination;
    }
}
