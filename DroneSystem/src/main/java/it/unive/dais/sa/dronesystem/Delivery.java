package it.unive.dais.sa.dronesystem;

import javax.persistence.*;

@Entity
public class Delivery {
    private Item item;
    private Drone drone;
    private int id;

    public Delivery() {}
    public Delivery(Drone drone, Item item) {
        this.item = item;
        this.drone = drone;
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
