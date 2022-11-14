package com.example.springplugin;

public class SadPlugin implements PluginInterface {
    @Override
    public String process(String str) {
        return "I'm sad: "+str;
    }

    @Override
    public boolean supports(String delimiter) {
        return delimiter.startsWith("sad");
    }
}
