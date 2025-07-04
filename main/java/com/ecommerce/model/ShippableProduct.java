package com.ecommerce.model;

public class ShippableProduct extends Product implements Shippable {
    private double weight; 

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}
