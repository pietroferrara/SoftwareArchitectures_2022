package it.unive.dais.sa.dronesystem.droneMovers;

public class SlowMover extends ParametrizedMover {
    public SlowMover() {
        super(1);
    }
    @Override
    public boolean supports(Integer d) {
        return d%3==0;
    }
}
