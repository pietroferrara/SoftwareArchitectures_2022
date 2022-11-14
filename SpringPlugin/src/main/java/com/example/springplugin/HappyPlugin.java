package com.example.springplugin;

public class HappyPlugin implements PluginInterface {
    @Override
    public String process(String str) {
        return "I'm happy: "+str;
    }

    @Override
    public boolean supports(String delimiter) {
        return delimiter.startsWith("happy");
    }
}
