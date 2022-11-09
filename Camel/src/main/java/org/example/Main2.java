package org.example;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import java.io.File;
import java.util.Date;


public class Main2 {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
                              public void configure() {
                                  from("file://C:/in/?fileName=numbers.txt")
                                          .convertBodyTo(String.class)
                                          .transform().exchange(this::computeSum)
                                          .to("file://C:/in/?fileName=result.txt");
                              }
                              private Object computeSum(Exchange exchange) {

                                      String[] splitted = exchange.getIn().
                                              getBody().toString().split(" ");
                                      Integer sum = Integer.valueOf(splitted[0])+
                                              Integer.valueOf(splitted[1]);
                                      exchange.getIn().setBody("Result: "+sum);
                                      return exchange.getIn().getBody();

                              }
                              private boolean notError(Exchange exchange) {
                                  return ! exchange.getIn().getBody()
                                          .toString().startsWith("Error");
                              }


                          }
        );
        context.start();
        while(! new File("C:\\in\\result.txt").exists()) {
            Thread.sleep(500);
        }
        context.close();
    }
}
