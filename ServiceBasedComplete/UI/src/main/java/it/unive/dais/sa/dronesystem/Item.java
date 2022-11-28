package it.unive.dais.sa.dronesystem;

public class Item {

    private double weight;
    private Integer id;
    private String description;

    public Item() {}
    public Item(double weight, String description) {
        this.weight = weight;
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
