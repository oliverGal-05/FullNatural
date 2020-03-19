package com.codecool.shop.model;

import java.util.ArrayList;

public class Order {

    private ArrayList<Product> productsInCart;
    private User user;

    public Order() {
        this.user = new User();
        this.productsInCart = new ArrayList<>();
    }

//    Temporary test methods

    public void addToCart(Product product) {
        productsInCart.add(product);
    }

    public void removeFromCart() {
        productsInCart.clear();
    }


}
