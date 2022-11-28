package it.unive.dais.sa.dronesystem;

import it.unive.dais.sa.dronesystem.rest.DBController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class DeliveryManagerController {
    private static final Logger logger = LogManager.getLogger("Main");

    @Autowired
    public ItemRepository items;
    @Autowired
    public DroneRepository drones;
    @Autowired
    public DeliveryRepository deliveries;
    @Autowired
    public CompletedDeliveryRepository completedDeliveries;

    @RequestMapping("startDelivery/{id}")
    public EntityModel<Delivery> startDelivery(
            @PathVariable Integer id) throws Exception {
        logger.info("Starting a delivery");
        Delivery del = deliveries.findById(Integer.valueOf(id)).get();
        new DeliveryManager(deliveries, completedDeliveries, drones, del).start();

        return EntityModel.of(del, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryManagerController.class).startDelivery(id)).withSelfRel());
    }

}
