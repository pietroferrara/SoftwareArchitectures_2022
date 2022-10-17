package com.example.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

@SpringBootTest
class ApplicationTests {

    private static final Logger logger = LogManager.getLogger("ApplicationTests");


    @Test
    void contextLoads() {
    }

    @Test
    void extensiveTest() {
        for (int i = 0; i < 1000; i++) {
            try {
                URL myURL = new URL("http://localhost:8080/adder?left=1&right=2");
                URLConnection myURLConnection = myURL.openConnection();
                myURLConnection.getContent();
            } catch (MalformedURLException e) {
                logger.error("Malformed URL");// new URL() failed
                // ...
            } catch (IOException e) {
                logger.error("IO error");
                // openConnection() failed
                // ...
            }
        }
    }

        @Test
        void randomTest() {
        Random randomgenerator = new Random(543654);
            for(int i = 0; i < 1000; i++) {
                try {

                    URL myURL = new URL("http://localhost:8080/adder?" +
                            "left="+randomgenerator.nextInt(Integer.MAX_VALUE/2-1)+
                            "&right="+randomgenerator.nextInt(Integer.MAX_VALUE/2-1));
                    URLConnection myURLConnection = myURL.openConnection();
                    myURLConnection.getContent();
                } catch (MalformedURLException e) {
                    logger.error("Malformed URL");// new URL() failed
                    // ...
                } catch (IOException e) {
                    logger.error("IO error");
                    // openConnection() failed
                    // ...
                }
            }


        }



}
