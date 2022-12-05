package it.unive.dais.sa.dronesystem.droneMovers;

import it.unive.dais.sa.dronesystem.Drone;
import it.unive.dais.sa.dronesystem.Position;

public abstract class ParametrizedMover implements MoverPlugin {
    private final int step;
    public ParametrizedMover(int step) {
        this.step = step;
    }
    @Override
    public Position approach(Position from, Position to, Drone d) {
        int x = from.getX() < to.getX() ?
                Math.min(to.getX(), from.getX()+step) :
                Math.max(to.getX(), from.getX()-step);
        int y = from.getY() < to.getY() ?
                Math.min(to.getY(), from.getY()+step) :
                Math.max(to.getY(), from.getY()-step);
        return new Position(x, y);
    }
}
