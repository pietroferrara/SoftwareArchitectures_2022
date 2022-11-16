package it.unive.dais.sa.dronesystem.plugins;

import it.unive.dais.sa.dronesystem.Drone;
import it.unive.dais.sa.dronesystem.Position;

public class FastApproacherPlugin implements MoverPlugin {
    @Override
    public Position approach(Position from, Position to, Drone d) {
        int x = from.getX() < to.getX() ?
                Math.min(to.getX(), from.getX()+10) :
                Math.max(to.getX(), from.getX()-10);
        int y = from.getY() < to.getY() ?
                Math.min(to.getY(), from.getY()+10) :
                Math.max(to.getY(), from.getY()-10);
        return new Position(x, y);
    }

    @Override
    public boolean supports(Drone delimiter) {
        return delimiter.getId()%2==0;
    }

}
