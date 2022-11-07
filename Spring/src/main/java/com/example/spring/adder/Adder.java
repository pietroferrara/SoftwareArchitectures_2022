package com.example.spring.adder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class Adder {
        private static final Logger logger = LogManager.getLogger("Main");


        @RequestMapping("adder")
        public String adder(
                @RequestParam(name = "left", required = true)
                String left,
                @RequestParam(name = "right", required = true)
                String right,
                Model model,
                HttpServletRequest request) {
            logger.info("Received a valid request with parameters "+left+" and "+right);
            model.addAttribute("left", left);
            model.addAttribute("right", right);
            String result;
            try {
                result = String.valueOf(Adder.add(Integer.parseInt(left), Integer.parseInt(right)));
            }
            catch(NumberFormatException e) {
                result = "Error when transforming your input values to integer numbers";
            }
            catch(ArithmeticException e) {
                logger.error("Overflow when adding "+left+" and "+right);
                result = "Overflow";
            }
            model.addAttribute("result", result);

            recordDB(request.getRemoteAddr(), left, right, result);

            return "adder";
        }

    @Autowired
    private AdditionRepository repo;
    private void recordDB(String remoteAddr, String left, String right, String result) {
            LoggedSum sum = new LoggedSum(remoteAddr, new Date(), left, right, result);
            repo.save(sum);
    }

    /**
         *
         * @param a
         * @param b
         * @return a+b, or Integer.MIN_VALUE if a+b overflows
         */
        public static int add(int a, int b) {
            logger.trace("Adding "+a+" and "+b);
            return Math.addExact(a, b);
        }



    @RequestMapping("showLoggedSums")
    public String showLoggedSums(Model model) {
        logger.info("Showing logged sums");
        model.addAttribute("loggedSums", repo.findAll());
        return "showLoggedSums";
    }
}
