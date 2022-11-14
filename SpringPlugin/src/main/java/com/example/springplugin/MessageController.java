package com.example.springplugin;

import org.springframework.plugin.core.Plugin;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    PluginRegistry<PluginInterface, String> registry =
            SimplePluginRegistry.of(new HappyPlugin(), new SadPlugin());

    @RequestMapping("/hello")
    public String hello(
            @RequestParam(name="status")
            String status,
            @RequestParam(name="message")
            String message)
    {
        return registry.getPluginFor(status).get().process(message);
    }
}
