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
    public DeliveryRepository deliveries;
    @Autowired
    public CompletedDeliveryRepository completedDeliveries;

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
        Delivery delivery = new Delivery(Integer.valueOf(drone), Integer.valueOf(item), Integer.parseInt(x), Integer.parseInt(y));
        deliveries.save(delivery);
    }

    @RequestMapping("startDelivery")
    public String startDelivery(
            @RequestParam(name = "id", required = true)
            String id,
            Model model) throws Exception {
        logger.info("Starting a delivery");
        model.addAttribute("id", id);
        new DeliveryManager(deliveries, completedDeliveries, deliveries.findById(Integer.valueOf(id)).get()).start();
        return "startDelivery";
    }

}
