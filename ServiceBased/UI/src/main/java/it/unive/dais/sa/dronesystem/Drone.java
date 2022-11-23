package it.unive.dais.sa.dronesystem;

public class Drone {
    private Position p;
    private Integer id;
    private double weightLimit;
    private double battery;

    public Drone() {}

    public Drone(Position p, double weightLimit, double battery) {
        this.p = p;
        this.weightLimit = weightLimit;
        this.battery = battery;
    }

    public Position getPosition() {
        return p;
    }

    public void setPosition(Position p) {
        this.p = p;
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
