package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Cannot add more than available quantity");
        }
        
        // Check if product already exists in cart
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                throw new IllegalArgumentException("Product already in cart. Remove it first to change quantity.");
            }
        }
        
        items.add(new CartItem(product, quantity));
    }
    
    public boolean remove(Product product) {
        return items.removeIf(item -> item.getProduct() == product);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    

    public void clear() {
        items.clear();
    }
}
