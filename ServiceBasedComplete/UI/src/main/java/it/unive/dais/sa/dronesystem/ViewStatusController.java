package it.unive.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class ViewStatusController {

    @RequestMapping("viewStatus")
    public String createDeliveryInput(
            Model model) {
        Collection<Drone> ds = new ArrayList<>();
        Collection<Item> is = new ArrayList<>();
        Collection<Delivery> des = new ArrayList<>();
        Collection<CompletedDelivery> cdes = new ArrayList<>();
        ds.addAll(Arrays.asList(DataProvider.findAllDrones()));
        is.addAll(Arrays.asList(DataProvider.findAllItems()));
        des.addAll(Arrays.asList(DataProvider.findAllDeliveries()));
        cdes.addAll(Arrays.asList(DataProvider.findAllCompletedDeliveries()));
        model.addAttribute("drones", ds);
        model.addAttribute("items", is);
        model.addAttribute("deliveries", des);
        model.addAttribute("completeddeliveries", cdes);
        return "viewStatus";
    }
}
