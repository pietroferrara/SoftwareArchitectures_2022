package it.unive.dais.sa.dronesystem.plugins;

import it.unive.dais.sa.dronesystem.Position;
import org.springframework.plugin.core.Plugin;

public interface MoverPlugin extends Plugin<Integer> {
    public Position approach(Position from, Position to, Integer d);
}
