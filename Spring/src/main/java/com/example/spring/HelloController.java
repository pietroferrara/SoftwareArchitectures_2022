package com.example.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String root() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/helloworld")
    public String helloworld(
            @RequestParam(name = "name", required = false,defaultValue = "World")
            String name) {
        return "Hi "+name;
    }

    @RequestMapping("/input")
    public String input() {
        return "<html><body>" +
                "<form action=\"helloworld\" method=\"GET\" >\n" +
                "  <p> Name <input type=\"text\" name=\"name\"></p>\n" +
                "  <p><input type=\"submit\" value=\"Submit\"></p>\n" +
                "</form>\n" +
                "</body></html>";
    }

}

