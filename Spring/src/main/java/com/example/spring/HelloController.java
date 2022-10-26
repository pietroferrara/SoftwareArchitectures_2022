package com.example.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping("/helloworld")
    public String helloworld(
            @RequestParam(name = "name", required = false, defaultValue = "World")
            String name,
            Model model) {
        model.addAttribute("name", name);
        return "helloworld";
    }


}

