package it.unive.dais.sa.dronesystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RestDroneController {

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

    @GetMapping("/restdrones/{id}")
    EntityModel<Drone> restone(@PathVariable Integer id) throws DNFException {
        Drone drone = drones.findById(id)
                .orElseThrow(() -> new DNFException(id));
        return EntityModel.of(drone, WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestDroneController.class).restone(id)).withSelfRel(), WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestDroneController.class).restall()).withRel("restdrones"));

    }


    @GetMapping("/restdrones")
    CollectionModel<EntityModel<Drone>> restall() {
        List<EntityModel<Drone>> d = StreamSupport.stream(drones.findAll().spliterator(),
                false).map(drone -> EntityModel.of(drone,WebMvcLinkBuilder.
                linkTo(WebMvcLinkBuilder.methodOn(RestDroneController.class).restone(drone.getId())).withSelfRel(), WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(RestDroneController.class).restall()).withRel("restdrones"))).collect(Collectors.toList());
        return CollectionModel.of(d, WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(RestDroneController.class).restall())
                .withSelfRel());
    }



    @PostMapping("/drones")
    Drone newDrone(@RequestBody Drone drone) {
        return drones.save(drone);
    }
    private class DNFException extends RuntimeException {
        public DNFException(Integer id) {
            super("Drone "+id+" not found");
        }
    }

    @GetMapping("/consumedrones/{id}")
    Drone consumerestone(@PathVariable Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8081/drones/"
                +id;
        Drone drone = restTemplate.getForObject(
                restServiceURL, Drone.class);
        return drone;
    }


}
