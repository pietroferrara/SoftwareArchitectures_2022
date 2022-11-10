package org.example.standalone;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

import java.util.Date;

public class StandaloneMain {
    public static void main(String[] args) throws Exception {
        Main main = new Main(StandaloneMain.class);
        main.run();
    }

    public static class MyRouteBuilder extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("timer:simple?period=503")
                    .transform()
                    .exchange(this::dateToTime)
                    .process()
                    .message(this::log)
                    .process()
                    .body(this::log);
        }
        private Long dateToTime(Exchange e) {
            return e.getProperty(
                    Exchange.TIMER_FIRED_TIME, Date.class).getTime();
        }
        private void log(Object b) {
            System.out.println("body is " + b.toString());
        }

    }


}
