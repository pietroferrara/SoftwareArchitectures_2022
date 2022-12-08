package it.unive.dais.sa.dronesystem;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CompletedDelivery {
    private int drone;
    private int item;
    private int x, y;
    private Date time;
    private Integer id;

    public CompletedDelivery(){}
    public CompletedDelivery(Delivery del) {
        this.drone = del.getDrone();
        this.x = del.getX();
        this.y = del.getY();
        this.item = del.getItem();
        time = new Date();
    }

    public int getDrone() {
        return drone;
    }

    public void setDrone(int drone) {
        this.drone = drone;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
