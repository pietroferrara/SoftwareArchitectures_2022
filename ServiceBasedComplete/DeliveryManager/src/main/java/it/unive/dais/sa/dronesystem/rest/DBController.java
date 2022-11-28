package it.unive.dais.sa.dronesystem.rest;

import it.unive.dais.sa.dronesystem.Drone;
import it.unive.dais.sa.dronesystem.DroneRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
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


    @GetMapping("/drones")
    Iterable<Drone> all() {
        return drones.findAll();
    }

    @PostMapping("/drones")
    Drone newDrone(@RequestBody Drone drone) {
        return drones.save(drone);
    }

    @GetMapping("/drones/{id}")
    Drone one(@PathVariable Integer id) throws DroneNotFoundException {
        return drones.findById(id)
                .orElseThrow(() -> new DroneNotFoundException(id));
    }

    @GetMapping("/restdrones/{id}")
    EntityModel<Drone> restone(@PathVariable Integer id) {
        Drone drone = drones.findById(id)
                .orElseThrow(() -> new DroneNotFoundException(id));
        return EntityModel.of(drone, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restone(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restall()).withRel("restdrones"));
    }



    @GetMapping("/restdrones")
    CollectionModel<EntityModel<Drone>> restall() {
        List<EntityModel<Drone>> d = StreamSupport.stream(drones.findAll().spliterator(), false)
                .map(drone -> EntityModel.of(drone,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restone(drone.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restall()).withRel("drones")))
                .collect(Collectors.toList());

        return CollectionModel.of(d, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).all()).withSelfRel());

    }


    @GetMapping("/consumerestdrones/{id}")
    EntityModel<Drone> consumerestone(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8081/drones/"+id;
        Drone drone = restTemplate.getForObject(restServiceURL, Drone.class);
        return EntityModel.of(drone, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restone(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restall()).withRel("restdrones"));
    }

    @GetMapping("/consumerestdrones")
    CollectionModel<EntityModel<Drone>> consumerestall() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8081/drones";
        Drone[] drones = restTemplate.getForObject(restServiceURL, Drone[].class);
        List<EntityModel<Drone>> d = Arrays.stream(drones)
                .map(drone -> EntityModel.of(drone,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restone(drone.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).restall()).withRel("drones")))
                .collect(Collectors.toList());

        return CollectionModel.of(d, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DBController.class).all()).withSelfRel());

    }


    private class DroneNotFoundException extends RuntimeException {
        public DroneNotFoundException(Integer id) {
            super("Drone "+id+" not found");
        }
    }
}
