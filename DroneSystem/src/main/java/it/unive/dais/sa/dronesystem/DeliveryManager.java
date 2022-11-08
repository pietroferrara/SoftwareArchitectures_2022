package it.unive.dais.sa.dronesystem;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryManager {


    public DeliveryRepository deliveries;

    public CompletedDeliveryRepository completedDeliveries;

    public DroneRepository drones;

    private CamelContext ctx;

    private Delivery delivery;

    public DeliveryManager(DeliveryRepository deliveries, CompletedDeliveryRepository completedDeliveries, DroneRepository drones, Delivery delivery) {
        this.deliveries = deliveries;
        this.completedDeliveries = completedDeliveries;
        this.drones = drones;
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
            Position destination = delivery.getDestination();
            Position actualPosition = delivery.getDrone().getPosition();
            Position new_position = approach(actualPosition, destination);
            delivery.getDrone().setPosition(new_position);
            drones.save(delivery.getDrone());
            if(destination.equals(new_position)) {
                completedDeliveries.save(new CompletedDelivery(delivery));
                deliveries.deleteById(delivery.getId());
                delivery = null;
                ctx.stop();
            }
        }

        private Position approach(Position from, Position to) {
            int x = from.getX() < to.getX() ?
                    Math.min(to.getX(), from.getX()+1) :
                    Math.max(to.getX(), from.getX()-1);
            int y = from.getY() < to.getY() ?
                    Math.min(to.getY(), from.getY()+1) :
                    Math.max(to.getY(), from.getY()-1);
            return new Position(x, y);
        }
    }
}
