package com.example.ecommercemarvel.model;

import java.util.List;

/**
 * Classe que representa um quadrinho
 */

public class Comic {

    private int id;
    private String title;
    private String description;
    private List<Price> prices;
    private Thumbnail thumbnail;


    private double price;
    private String urlImage;
    private boolean isRare;

    public Comic(int id, String title, String description, List<Price> prices, Thumbnail thumbnail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.prices = prices;
        this.thumbnail = thumbnail;
        this.isRare = false;
    }

    public boolean isRare() {
        return isRare;
    }

    public void setRare(boolean rare) {
        isRare = rare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {

        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        setPrice();
        return price;
    }

    public void setPrice() {
        double preco =  this.getPrices().get(0).getPrice();

        double REAL_BRASILERO = 3.76;
        this.price = preco  * REAL_BRASILERO;
    }

    public String getUrlImage() {
        setUrlImage();
        return urlImage;
    }

    public void setUrlImage() {
        String url = this.thumbnail.getPath();
        String extension = this.thumbnail.getExtension();

        this.urlImage = url + "/portrait_fantastic." + extension;

    }
}
