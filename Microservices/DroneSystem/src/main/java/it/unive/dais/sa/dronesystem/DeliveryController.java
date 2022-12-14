package it.unive.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class DeliveryController {
    private static final Logger logger = LogManager.getLogger("Main");

    @Autowired
    public ItemRepository items;
    @Autowired
    public DroneRepository drones;

    @RequestMapping("createDeliveryInput")
    public String createDeliveryInput(
            Model model) {
        Collection<Drone> drones = getAvailableDrones();
        Collection<Item> items = getAvailableItems();
        model.addAttribute("drones", drones);
        model.addAttribute("items", items);
        return "createDeliveryInput";
    }

    private Collection<Item> getAvailableItems() {
        List<Item> is = new ArrayList<>();
        for(Item item : items.findAll()) {
            boolean found = false;
            //for(Delivery delivery : deliveries.findAll())
            //    if(delivery.getItem().getId().equals(item.getId()))
            //        found = true;
            if(! found)
                is.add(item);
        }
        return is;
    }

    private Collection<Drone> getAvailableDrones() {
        List<Drone> ds = new ArrayList<>();
        for(Drone drone : drones.findAll()) {
            boolean found = false;
            //for(Delivery delivery : deliveries.findAll())
            //    if(delivery.getDrone().getId().equals(drone.getId()))
            //        found = true;
            if(! found)
                ds.add(drone);
        }
        return ds;
    }



}
