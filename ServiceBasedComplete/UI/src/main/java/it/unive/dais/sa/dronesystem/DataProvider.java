package it.unive.dais.sa.dronesystem;

import org.springframework.web.client.RestTemplate;

public class DataProvider {

    public static Drone[] findAllDrones() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8082/drones";
        return  restTemplate.getForObject(restServiceURL, Drone[].class);
    }
    public static Item[] findAllItems() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8083/items";
        return restTemplate.getForObject(restServiceURL, Item[].class);
    }
    public static Delivery[] findAllDeliveries() {
        return new Delivery[0];
/*        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8084/deliveries";
        return restTemplate.getForObject(restServiceURL, Delivery[].class);
*/    }
    public static CompletedDelivery[] findAllCompletedDeliveries() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://localhost:8084/completeddeliveries";
        return restTemplate.getForObject(restServiceURL, CompletedDelivery[].class);
    }
}
