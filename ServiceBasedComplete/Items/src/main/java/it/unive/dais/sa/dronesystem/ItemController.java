package it.unive.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ItemController {
    private static final Logger logger = LogManager.getLogger("Main");
    @Autowired
    public ItemRepository items;

    @GetMapping("/items/{id}")
    EntityModel<Item> one(@PathVariable Integer id) {
        Item drone = items.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        return EntityModel.of(drone, //
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).one(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ItemController.class).all()).withRel("restdrones"));
    }



    @GetMapping("/items")
    List<Item> all() {
        return StreamSupport.stream(items.findAll().spliterator(), false)
               .collect(Collectors.toList());
    }


    private class ItemNotFoundException extends RuntimeException {
        public ItemNotFoundException(Integer id) {
            super("Item "+id+" not found");
        }
    }

}
