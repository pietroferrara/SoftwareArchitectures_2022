package it.unive.dais.sa.dronesystem;


public class Delivery {
    private Item item;
    private Drone drone;
    private Position destination;
    private int id;

    public Delivery() {}
    public Delivery(Drone drone, Item item, Position destination) {
        this.item = item;
        this.drone = drone;
        this.destination = destination;
    }

    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
