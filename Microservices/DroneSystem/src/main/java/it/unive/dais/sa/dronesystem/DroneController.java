package it.unive.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@Controller
public class DroneController {
    private static final Logger logger = LogManager.getLogger("Main");

    @Autowired
    public DroneRepository drones;

    @RequestMapping("createDrone")
    public String createDrone(
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

    private void recordDB(String x, String y, String weightLimit, String battery) {
        Drone drone = new Drone(new Position(Integer.valueOf(x), Integer.valueOf(y)), Double.valueOf(weightLimit), Double.valueOf(battery));
        drones.save(drone);
    }

}
