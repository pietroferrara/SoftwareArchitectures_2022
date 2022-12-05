package it.unive.dais.sa.dronesystem.droneMovers;

import it.unive.dais.sa.dronesystem.Drone;
import it.unive.dais.sa.dronesystem.Position;

public class SlowMover extends ParametrizedMover {
    public SlowMover() {
        super(1);
    }
    @Override
    public boolean supports(Drone d) {
        return d.getId()%3==0;
    }
}
