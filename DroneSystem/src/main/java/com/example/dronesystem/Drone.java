package com.example.dronesystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Drone {
    int x, y;
    Integer id;
    double weightLimit;
    double battery;

    public Drone() {}

    public Drone(int x, int y, double weightLimit, double battery) {
        this.x = x;
        this.y = y;
        this.weightLimit = weightLimit;
        this.battery = battery;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public double getBattery() {
        return battery;
    }
}
