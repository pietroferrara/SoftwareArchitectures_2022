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
    @Autowired
    public DeliveryRepository deliveries;
    @Autowired
    public CompletedDeliveryRepository completedDeliveries;
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
            for(Delivery delivery : deliveries.findAll())
                if(delivery.getItem().getId().equals(item.getId()))
                    found = true;
            if(! found)
                is.add(item);
        }
        return is;
    }

    private Collection<Drone> getAvailableDrones() {
        List<Drone> ds = new ArrayList<>();
        for(Drone drone : drones.findAll()) {
            boolean found = false;
            for(Delivery delivery : deliveries.findAll())
                if(delivery.getDrone().getId().equals(drone.getId()))
                    found = true;
            if(! found)
                ds.add(drone);
        }
        return ds;
    }


    @RequestMapping("createDelivery")
    public String createDelivery(
            @RequestParam(name = "drone", required = true)
            String drone,
            @RequestParam(name = "item", required = true)
            String item,
            @RequestParam(name = "x", required = true)
            String x,
            @RequestParam(name = "y", required = true)
            String y,
            Model model) {
        logger.info("Creating a delivery");
        model.addAttribute("drone", drone);
        model.addAttribute("item", item);
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        recordDB(drone, item, x, y);
        return "createDelivery";
    }

    private void recordDB(String drone, String item, String x, String y) {
        Delivery delivery = new Delivery(drones.findById(Integer.valueOf(drone)).get(), items.findById(Integer.valueOf(item)).get(), new Position(Integer.parseInt(x), Integer.parseInt(y)));
        deliveries.save(delivery);
    }

    @RequestMapping("startDelivery")
    public String startDelivery(
            @RequestParam(name = "id", required = true)
            String id,
            Model model) throws Exception {
        logger.info("Starting a delivery");
        model.addAttribute("id", id);
        new DeliveryManager(deliveries, completedDeliveries, drones, deliveries.findById(Integer.valueOf(id)).get()).start();
        return "startDelivery";
    }

}
