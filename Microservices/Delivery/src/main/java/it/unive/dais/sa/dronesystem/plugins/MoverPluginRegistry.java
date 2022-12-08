package it.unive.dais.sa.dronesystem.plugins;

import org.springframework.plugin.core.PluginRegistry;

public class MoverPluginRegistry {
    public static final PluginRegistry<MoverPlugin, Integer> registry =
            PluginRegistry.of(new SlowApproacherPlugin(), new FastApproacherPlugin());
}
