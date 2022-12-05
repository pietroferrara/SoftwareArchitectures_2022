package it.unive.dais.sa.dronesystem.droneMovers;

import it.unive.dais.sa.dronesystem.Drone;
import it.unive.dais.sa.dronesystem.Position;

public class BatteryConsumerMover extends FastMover {

    @Override
    public Position approach(Position from, Position to, Drone d) {
        d.setBattery(d.getBattery()-1);
        return super.approach(from, to, d);
    }
    @Override
    public boolean supports(Drone d) {
        return d.getId()%3==2;
    }
}
