package com.ecommerce.model;

import java.time.LocalDate;

public class ShippableExpirableProduct extends ExpirableProduct implements Shippable {
    private double weight; // weight in kg

    public ShippableExpirableProduct(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}
