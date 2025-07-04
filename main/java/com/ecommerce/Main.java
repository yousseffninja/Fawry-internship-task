package com.ecommerce;

import java.time.LocalDate;

import com.ecommerce.checkout.ECommerceSystem;
import com.ecommerce.model.*;
import com.ecommerce.service.ShippingService;

public class Main {
    public static void main(String[] args) {
        ShippableExpirableProduct cheese = new ShippableExpirableProduct(
                "Cheese", 100.0, 10, LocalDate.now().plusDays(30), 0.2);

        ShippableExpirableProduct biscuits = new ShippableExpirableProduct(
                "Biscuits", 150.0, 20, LocalDate.now().plusDays(90), 0.7);

        ShippableProduct tv = new ShippableProduct(
                "TV", 5000.0, 5, 15.0);

        Product scratchCard = new Product(
                "Mobile Scratch Card", 50.0, 100);

        Customer customer = new Customer("John Doe", 10000.0);
        Cart cart = new Cart();
        ECommerceSystem ecommerceSystem = new ECommerceSystem();
        System.out.println("Initial customer balance: " + customer.getBalance());

        try {
            System.out.println("\n=== Scenario 1: Successful Checkout ===");
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);

            ecommerceSystem.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        cart = new Cart();

        try {
            System.out.println("\n=== Scenario 2: Expired Product ===");
            ShippableExpirableProduct expiredMilk = new ShippableExpirableProduct(
                    "Expired Milk", 80.0, 5, LocalDate.now().minusDays(1), 1.0);

            cart.add(expiredMilk, 1);

            ecommerceSystem.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        cart = new Cart();

        try {
            System.out.println("\n=== Scenario 3: Insufficient Balance ===");
            Customer poorCustomer = new Customer("Poor Customer", 100.0);

            cart.add(tv, 1); // TV costs more than balance

            ecommerceSystem.checkout(poorCustomer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        cart = new Cart();

        try {
            System.out.println("\n=== Scenario 4: Empty Cart ===");

            ecommerceSystem.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        cart = new Cart();

        try {
            System.out.println("\n=== Scenario 5: No Shipping ===");
            Customer poorCustomer = new Customer("Poor Customer", 10000.0);

            cart.add(scratchCard, 1); // TV costs more than balance

            ecommerceSystem.checkout(poorCustomer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
