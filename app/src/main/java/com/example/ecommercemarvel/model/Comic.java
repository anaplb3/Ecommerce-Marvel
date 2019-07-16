package com.example.ecommercemarvel.model;

/**
 * Classe que representa um quadrinho
 */

public class Comic {
    private String title;
    private String description;
    private double price;
    private String url_image;
    private static double REAL_BRASILERO = 3.76;

    public Comic(String title, String description, double price, String url_image) {
        this.title = title;
        this.description = description;
        this.price = REAL_BRASILERO * price;
        this.url_image = url_image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getUrl_image() {
        return url_image;
    }

    public static double getRealBrasilero() {
        return REAL_BRASILERO;
    }
}
