package it.unive.dais.sa.dronesystem.droneMovers;

import it.unive.dais.sa.dronesystem.Drone;
import it.unive.dais.sa.dronesystem.Position;
import org.springframework.plugin.core.Plugin;

public interface MoverPlugin extends Plugin<Drone> {
    public Position approach(Position from, Position to, Drone d);
}
