package it.unive.dais.sa.dronesystem.droneMovers;

import it.unive.dais.sa.dronesystem.Drone;

public class FastMover extends ParametrizedMover {
    public FastMover() {
        super(5);
    }
    @Override
    public boolean supports(Drone d) {
        return d.getId()%3==1;
    }
}
