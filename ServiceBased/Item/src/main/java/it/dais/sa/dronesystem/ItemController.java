package it.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {
    private static final Logger logger = LogManager.getLogger("Main");
    @Autowired
    public ItemRepository items;


    @GetMapping("/items")
    Iterable<Item> all() {
        return items.findAll();
    }


    @GetMapping("/items/{id}")
    Item one(@PathVariable Integer id) throws INFException {
        return items.findById(id)
                .orElseThrow(() -> new INFException(id));
    }
    private class INFException extends RuntimeException {
        public INFException(Integer id) {
            super("Item "+id+" not found");
        }
    }

}
