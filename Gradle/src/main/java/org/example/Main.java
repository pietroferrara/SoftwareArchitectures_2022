package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger("Main");

    /**
     *
     * @param a
     * @param b
     * @return a+b, or Integer.MIN_VALUE if a+b overflows
     */
    public static int add(int a, int b) {
        logger.trace("Adding "+a+" and "+b);
        try {
            return Math.addExact(a, b);
        }
        catch(ArithmeticException e) {
            logger.error("Overflow when adding "+a+" and "+b);
            return Integer.MIN_VALUE;
        }
    }

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int result = Main.add(a, b);

        System.out.println("The result is "+result);
    }
}