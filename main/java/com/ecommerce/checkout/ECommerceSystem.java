package com.ecommerce.checkout;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.*;
import com.ecommerce.service.ShippingService;

public class ECommerceSystem {
    private ShippingService shippingService;

    public ECommerceSystem() {
        this.shippingService = new ShippingService();
    }

    public void checkout(Customer customer, Cart cart) {
        // Verify cart is not empty
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot checkout with an empty cart");
        }

        double subtotal = cart.getSubtotal();
        System.out.println("** Checkout receipt **");
        
        List<Shippable> shippableItems = new ArrayList<>();
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            
            if (product.isExpired()) {
                throw new IllegalStateException("Product " + product.getName() + " is expired");
            }
            
            if (quantity > product.getQuantity()) {
                throw new IllegalStateException("Product " + product.getName() + " has insufficient stock");
            }
            
            System.out.println(quantity + "x " + product.getName() + " " + (quantity * product.getPrice()));
            
            if (product.isShippable() && product instanceof Shippable) {
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        
        double shippingCost = 0.0;
        if (!shippableItems.isEmpty()) {
            shippingCost = shippingService.shipItems(shippableItems);
        }
        
        double totalAmount = subtotal + shippingCost;
        
        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException("Insufficient balance");
        }
        
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        
        if (shippingCost > 0) {
            System.out.println("Shipping " + shippingCost);
        }
        
        System.out.println("Amount " + totalAmount);
        
        customer.deductBalance(totalAmount);
        System.out.println("Remaining balance: " + customer.getBalance());
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.reduceQuantity(item.getQuantity());
        }
        
        cart.clear();
    }
}
