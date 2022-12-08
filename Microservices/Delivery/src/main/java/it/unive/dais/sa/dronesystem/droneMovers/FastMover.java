package it.unive.dais.sa.dronesystem.droneMovers;

public class FastMover extends ParametrizedMover {
    public FastMover() {
        super(5);
    }
    @Override
    public boolean supports(Integer d) {
        return d%3==1;
    }
}
