package it.unive.dais.sa.dronesystem;

import javax.persistence.*;

@Entity
public class Delivery {
    private int item;
    private int drone;
    private int x, y;
    private int id;

    public Delivery() {}
    public Delivery(int drone, int item, int x, int y) {
        this.item = item;
        this.drone = drone;
        this.x = x;
        this.y = y;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getDrone() {
        return drone;
    }

    public void setDrone(int drone) {
        this.drone = drone;
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

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
