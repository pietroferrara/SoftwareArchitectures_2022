package com.example.springplugin;

import org.springframework.plugin.core.Plugin;

public interface PluginInterface extends Plugin<String> {
    public String process(String str);
}
