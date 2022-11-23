package it.unive.dais.sa.dronesystem.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class DroneController {
    private static final Logger logger = LogManager.getLogger("Main");

    @Autowired
    public DroneRepository drones;

    @GetMapping("/drones")
    Iterable<Drone> all() {
        return drones.findAll();
    }


    @GetMapping("/drones/{id}")
    Drone one(@PathVariable Integer id) throws DNFException {
        return drones.findById(id)
                .orElseThrow(() -> new DNFException(id));
    }

    private class DNFException extends RuntimeException {
        public DNFException(Integer id) {
            super("Drone "+id+" not found");
        }
    }
}
