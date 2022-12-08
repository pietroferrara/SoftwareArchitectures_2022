package it.unive.dais.sa.dronesystem.droneMovers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.plugin.core.PluginRegistry;

import java.lang.reflect.Modifier;
import java.util.*;

public final class MoverPluginRegistry {

    private static final Logger logger = LogManager.getLogger("Plugins");

    public static final PluginRegistry<MoverPlugin, Integer> registry = PluginRegistry.of(getMoverPlugins());


    private static List<MoverPlugin> getMoverPlugins() {
        Reflections ref = new Reflections(MoverPluginRegistry.class.getPackageName());
        List<MoverPlugin> result = new ArrayList<>();
        for(Class c : ref.getSubTypesOf(MoverPlugin.class)) {
            if(! c.isInterface() && !Modifier.isAbstract(c.getModifiers())) {
                try {
                    result.add((MoverPlugin) c.getConstructor().newInstance());
                } catch (Exception e) {
                    logger.error("Unable to create plugin "+c.getName());
                }
            }
        }
        return result;
    }
}
