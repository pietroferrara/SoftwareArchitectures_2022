package it.unive.dais.sa.dronesystem;

import it.unive.dais.sa.dronesystem.droneMovers.FastMover;
import it.unive.dais.sa.dronesystem.droneMovers.MoverPluginRegistry;
import it.unive.dais.sa.dronesystem.droneMovers.SlowMover;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class DeliveryManager {


    public DeliveryRepository deliveries;

    public CompletedDeliveryRepository completedDeliveries;

    private CamelContext ctx;

    private Delivery delivery;

    public DeliveryManager(DeliveryRepository deliveries, CompletedDeliveryRepository completedDeliveries, Delivery delivery) {
        this.deliveries = deliveries;
        this.completedDeliveries = completedDeliveries;
        this.delivery = delivery;
    }

    public void start() throws Exception {
        ctx = new DefaultCamelContext();
        RouteBuilder route = new DeliveryRouteBuilder();
        ctx.addRoutes(route);
        ctx.start();
    }

    public void stop() {
        ctx.stop();
    }

    class DeliveryRouteBuilder extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("timer:simple?period=1000")
                    .process(this::progress);
        }

        private void progress(Exchange exchange) {
            Position destination = new Position(delivery.getX(), delivery.getY());
            int drone_id = delivery.getDrone();
            RestTemplate restTemplate = new RestTemplate();
            String restServiceURL = "http://localhost:8081/drones/position/"+drone_id;
            Position actualPosition = restTemplate.getForObject(restServiceURL, Position.class);
            Position new_position = new SlowMover().approach(actualPosition, destination, delivery.getDrone());
            restTemplate.postForObject(restServiceURL, new_position, Position.class);

            if(destination.equals(new_position)) {
                completedDeliveries.save(new CompletedDelivery(delivery));
                deliveries.deleteById(delivery.getId());
                delivery = null;
                ctx.stop();
            }
        }
    }
}
