package it.unive.dais.sa.dronesystem;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CompletedDelivery {
    private Drone drone;
    private Item item;
    private Position destination;
    private Date time;
    private Integer id;

    public CompletedDelivery(){}
    public CompletedDelivery(Delivery del) {
        this.drone = del.getDrone();
        this.destination = del.getDestination();
        this.item = del.getItem();
        time = new Date();
    }

    @ManyToOne
    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    @ManyToOne
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Embedded
    public Position getDestination() {
        return destination;
    }

    public void setDestination(Position destination) {
        this.destination = destination;
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
