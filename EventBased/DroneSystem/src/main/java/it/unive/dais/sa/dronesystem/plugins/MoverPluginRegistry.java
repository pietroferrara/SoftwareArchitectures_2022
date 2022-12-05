package it.unive.dais.sa.dronesystem.plugins;

import it.unive.dais.sa.dronesystem.Drone;
import org.springframework.plugin.core.PluginRegistry;

public class MoverPluginRegistry {
    public static final PluginRegistry<MoverPlugin, Drone> registry =
            PluginRegistry.of(new SlowApproacherPlugin(), new FastApproacherPlugin());
}
