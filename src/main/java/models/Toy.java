package models;

import java.io.Serializable;

public class Toy implements Serializable {
    private String name;
    private TypeToy type;
    private double price;
    private int quantity;

    public Toy(String name, TypeToy type, double price, int quantity) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeToy getType() {
        return type;
    }

    public void setType(TypeToy type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
