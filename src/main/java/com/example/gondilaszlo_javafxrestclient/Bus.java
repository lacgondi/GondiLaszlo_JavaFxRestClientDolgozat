package com.example.gondilaszlo_javafxrestclient;

import com.google.gson.annotations.Expose;

public class Bus {
    @Expose
    private int ID;
    @Expose
    private String busID;
    @Expose
    private int delay;
    @Expose
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
