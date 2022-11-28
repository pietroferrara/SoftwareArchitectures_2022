package it.unive.dais.sa.drones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DBController {
    private static final Logger logger = LogManager.getLogger("Main");

    @Autowired
    public DroneRepository drones;

    @GetMapping("/drones/{id}")
    EntityModel<Drone> one(@PathVariable Integer id) {
        Drone drone = drones.findById(id)
                .orElseThrow(() -> new DroneNotFoundException(id));
        return EntityModel.of(drone, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).one(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).all()).withRel("restdrones"));
    }



    @GetMapping("/drones")
    List<Drone> all() {
        return StreamSupport.stream(drones.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }
    private class DroneNotFoundException extends RuntimeException {
        public DroneNotFoundException(Integer id) {
            super("Drone "+id+" not found");
        }
    }
}
