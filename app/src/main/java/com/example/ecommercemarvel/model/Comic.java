package com.example.ecommercemarvel.model;

import java.util.List;

/**
 * Classe que representa um quadrinho
 */

public class Comic {
<<<<<<< HEAD

    private Long id;
    private String title;
    private String description;

    public Comic(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
=======
    private Long id;
    private String title;
    private String description;
    private List<Object> prices;
    private String[] thumbnail;


    private static double REAL_BRASILERO = 3.76;
    private double price;
    private String urlImage;

    public Comic(Long id, String title, String description, List<Object> prices, String[] thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.prices = prices;
        this.thumbnail = thumbnail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
    }

    public Long getId() {
        return id;
    }

<<<<<<< HEAD
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
=======
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getPrices() {
        return prices;
    }

    public void setPrices(List<Object> prices) {
        this.prices = prices;
    }

    public String[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String[] thumbnail) {

        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        setPrice();
        return price;
    }

    public void setPrice() {
        double preco = (double) this.getPrices().get(1);
        this.price = preco * REAL_BRASILERO;
    }

    public String getUrlImage() {
        setUrlImage();
        return urlImage;
    }

    public void setUrlImage() {
        String url = this.thumbnail[0];
        String extension = this.thumbnail[1];

        this.urlImage = url + "/portrait_fantastic." + extension;
>>>>>>> 881c2a1ecd195d4718fc7eb03df35e7ffdc191d4
    }
}
