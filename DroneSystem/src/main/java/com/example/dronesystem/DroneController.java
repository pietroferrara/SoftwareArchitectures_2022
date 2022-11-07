package com.example.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class DroneController {
    private static final Logger logger = LogManager.getLogger("Main");


    @RequestMapping("createDrone")
    public String adder(
            @RequestParam(name = "x", required = true)
            String x,
            @RequestParam(name = "y", required = true)
            String y,
            @RequestParam(name = "weightLimit", required = true)
            String weightLimit,
            @RequestParam(name = "battery", required = true)
            String battery,
            Model model) {
        logger.info("Creating a drone");
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        model.addAttribute("weightLimit", weightLimit);
        model.addAttribute("battery", battery);

        recordDB(x, y, weightLimit, battery);

        return "createDrone";
    }

    @Autowired
    private DroneRepository drone_repo;
    private void recordDB(String x, String y, String weightLimit, String battery) {
        Drone drone = new Drone(Integer.valueOf(x), Integer.valueOf(y), Double.valueOf(weightLimit), Double.valueOf(battery));
        drone_repo.save(drone);
    }

}
