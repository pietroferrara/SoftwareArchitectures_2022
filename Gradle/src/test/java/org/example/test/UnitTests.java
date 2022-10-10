package org.example.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class UnitTests {

    private static final Logger logger = LogManager.getLogger("UnitTests");

    @Test
    public void testAdd() {
        if(Main.add(12, 34)!=46)
            throw new ArithmeticException("Wrong result");
    }


    @Test
    public void testOverflow() {
        Main.add(Integer.MAX_VALUE, 1);
    }

    @Test
    public void testMain() {
        String[] pars = {"12", "34"};
        Main.main(pars);
    }

    public static final int NUMBER_OF_EXECUTIONS = Integer.MAX_VALUE-1;

    @Test
    public void testExtensive() {
        Random randomNumberGenerator = new Random(98765);
        for(int i = 0; i < UnitTests.NUMBER_OF_EXECUTIONS; i++) {
            Main.add(randomNumberGenerator.nextInt( ), randomNumberGenerator.nextInt());
        }
    }

    @Test
    //It reports the number of add operations performed per second
    public void testScalability() {
        long begin = System.currentTimeMillis();
        testExtensive();
        long total_time = System.currentTimeMillis()-begin;
        double scalability_rate = UnitTests.NUMBER_OF_EXECUTIONS/total_time;
        System.out.println("We executed "+scalability_rate+" additions per millisecond");
    }

    @Test
    //It reports the number of add operations performed per second
    public void testScalability2() {
        long begin = System.nanoTime();
        for(int i = 0; i < UnitTests.NUMBER_OF_EXECUTIONS; i++) {
            Main.add(1, 2);
        }
        long total_time = System.nanoTime()-begin;
        double scalability_rate = UnitTests.NUMBER_OF_EXECUTIONS/total_time;
        logger.error("We executed "+scalability_rate+" additions per nanosecond");
    }
}
