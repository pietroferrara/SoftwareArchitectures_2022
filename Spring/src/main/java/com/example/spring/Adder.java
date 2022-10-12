package com.example.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Adder {
        private static final Logger logger = LogManager.getLogger("Main");

        @RequestMapping("/adderinput")
        public String input() {
            return "<html><body>" +
                    "<form action=\"adder\" method=\"GET\" >\n" +
                    "  <p> Left value <input type=\"text\" name=\"left\"></p>\n" +
                    "  <p> Right value <input type=\"text\" name=\"right\"></p>\n" +
                    "  <p><input type=\"submit\" value=\"Submit\"></p>\n" +
                    "</form>\n" +
                    "</body></html>";
        }

        @RequestMapping("adder")
        public String adder(
                @RequestParam(name = "left", required = true)
                String left,
                @RequestParam(name = "right", required = true)
                String right) {
            logger.info("Received a valid request with parameters "+left+" and "+right);
            try {
                return "The result of " + left + "+" + right + " is " +
                        Adder.add(Integer.parseInt(left), Integer.parseInt(right));
            }
            catch(NumberFormatException e) {
                return "Error when transforming your input values to integer numbers";
            }
            catch(ArithmeticException e) {
                logger.error("Overflow when adding "+left+" and "+right);
                return "Overflow when adding "+left+" and "+right;
            }
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
}
