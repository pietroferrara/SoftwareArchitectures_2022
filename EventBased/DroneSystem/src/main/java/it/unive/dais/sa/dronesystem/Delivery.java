package it.unive.dais.sa.dronesystem;

import javax.persistence.*;

@Entity
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

    @Embedded
    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    @OneToOne
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @OneToOne
    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
