package it.unive.dais.sa.dronesystem;

import org.springframework.web.client.RestTemplate;

public class DataProvider {

    public static Drone[] findAllDrones() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://host.docker.internal:8082/drones";
        return  restTemplate.getForObject(restServiceURL, Drone[].class);
    }
    public static Item[] findAllItems() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://host.docker.internal:8083/items";
        return restTemplate.getForObject(restServiceURL, Item[].class);
    }
    public static Delivery[] findAllDeliveries() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://host.docker.internal:8084/deliveries";
        return restTemplate.getForObject(restServiceURL, Delivery[].class);
    }
    public static CompletedDelivery[] findAllCompletedDeliveries() {
        RestTemplate restTemplate = new RestTemplate();
        String restServiceURL = "http://host.docker.internal:8084/completeddeliveries";
        return restTemplate.getForObject(restServiceURL, CompletedDelivery[].class);
    }
}
