package it.unive.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DeliveryController {
    private static final Logger logger = LogManager.getLogger("Main");

    @Autowired
    public ItemRepository items;
    @Autowired
    public DroneRepository drones;
    @Autowired
    public DeliveryRepository deliveries;
    @Autowired
    public CompletedDeliveryRepository completeddeliveries;

    @GetMapping("/deliveries")
    List<Delivery> all() {
        return StreamSupport.stream(deliveries.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    @GetMapping("/deliveries/{id}")
    EntityModel<Delivery> one(@PathVariable Integer id) {
        Delivery d = deliveries.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException(id));
        return EntityModel.of(d, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryController.class).one(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryController.class).all()).withRel("restdrones"));
    }

    @GetMapping("/completeddeliveries")
    List<CompletedDelivery> allCompleted() {
        return  StreamSupport.stream(completeddeliveries.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    private class DeliveryNotFoundException extends RuntimeException {
        public DeliveryNotFoundException(Integer id) {
            super("Delivery "+id+" not found");
        }
    }
}
