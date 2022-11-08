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
public class ViewStatusController {
    private static final Logger logger = LogManager.getLogger("Main");

    @Autowired
    public ItemRepository items;
    @Autowired
    public DroneRepository drones;
    @Autowired
    public DeliveryRepository deliveries;
    @Autowired
    public CompletedDeliveryRepository copmletedDeliveries;
    @RequestMapping("viewStatus")
    public String createDeliveryInput(
            Model model) {
        Collection<Drone> ds = new ArrayList<>();
        Collection<Item> is = new ArrayList<>();
        Collection<Delivery> des = new ArrayList<>();
        Collection<CompletedDelivery> cdes = new ArrayList<>();
        drones.findAll().forEach(ds::add);
        items.findAll().forEach(is::add);
        deliveries.findAll().forEach(des::add);
        copmletedDeliveries.findAll().forEach(cdes::add);
        model.addAttribute("drones", ds);
        model.addAttribute("items", is);
        model.addAttribute("deliveries", des);
        model.addAttribute("completeddeliveries", cdes);
        return "viewStatus";
    }
}
