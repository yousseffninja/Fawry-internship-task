package com.ecommerce.service;

import java.util.List;
import com.ecommerce.model.Shippable;


public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 30.0;
    
    public double shipItems(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        
        for (Shippable item : items) {
            System.out.println("1x " + item.getName() + " " + (int)(item.getWeight() * 1000) + "g");
            totalWeight += item.getWeight();
        }
        
        System.out.println("Total package weight " + String.format("%.1f", totalWeight) + "kg");
        
        return calculateShippingCost(totalWeight);
    }

    private double calculateShippingCost(double totalWeight) {
        return Math.ceil(totalWeight) * SHIPPING_RATE_PER_KG;
    }
}
