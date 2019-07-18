package com.example.ecommercemarvel.model;

import java.io.Serializable;

public class Price implements Serializable {
    private String type;
    private double price;

    public Price(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
