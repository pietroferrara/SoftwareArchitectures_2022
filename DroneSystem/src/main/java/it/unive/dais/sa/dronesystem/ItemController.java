package it.unive.dais.sa.dronesystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {
    private static final Logger logger = LogManager.getLogger("Main");
    @Autowired
    public ItemRepository items;

    @RequestMapping("createItem")
    public String createItem(
            @RequestParam(name = "weight", required = true)
            String weight,
            @RequestParam(name = "description", required = true)
            String description,
            Model model) {
        logger.info("Creating an item");
        model.addAttribute("weight", weight);
        model.addAttribute("description", description);

        recordDB(weight, description);

        return "createItem";
    }

    private void recordDB(String weight, String description) {
        Item item = new Item(Double.valueOf(weight), description);
        items.save(item);
    }

}
