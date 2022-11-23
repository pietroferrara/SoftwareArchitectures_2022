package it.unive.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class ViewStatusController {
    private static final Logger logger = LogManager.getLogger("Main");

   @RequestMapping("viewStatus")
    public String createDeliveryInput(
            Model model) {
        Collection<Drone> ds = getAllDrones();
        Collection<Item> is = getAllItems();
        model.addAttribute("drones", ds);
        model.addAttribute("items", is);
        return "viewStatus";
    }

    private Collection<Drone> getAllDrones() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8082/drones/";
        Drone[] drone = restTemplate.getForObject(
                restServiceURL, Drone[].class);
        return Arrays.asList(drone);
    }
    private Collection<Item> getAllItems() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8083/items/";
        Item[] items = restTemplate.getForObject(
                restServiceURL, Item[].class);
        return Arrays.asList(items);
    }
}
