package com.example.gondilaszlo_javafxrestclient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bus {
    private int id;
    @Expose
    @SerializedName("bus")
    private String busID;
    @Expose
    private int delay;
    @Expose
    private String destination;

    public Bus(int ID, String busID, int delay, String destination) {
        this.id = ID;
        this.busID = busID;
        this.delay = delay;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
