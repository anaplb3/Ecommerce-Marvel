package com.example.ecommercemarvel.model;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

/**
 * Classe que representa um quadrinho
 */

public class Comic implements Serializable {

    private int id;
    private String title;
    private String description;
    private List<Price> prices;
    private Thumbnail thumbnail;
    private CharacterList characters;
    private List<Image> images;


    private double price;
    private String urlImage;
    private boolean isRare;
    private int qtd;

    public Comic(int id, String title, String description, List<Price> prices, Thumbnail thumbnail, CharacterList characters, List<Image> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.prices = prices;
        this.thumbnail = thumbnail;
        this.isRare = false;
        this.qtd = 1;
        this.characters = characters;
        this.images = images;
    }

    public Comic(int id, String title, double price, boolean isRare) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isRare = isRare;
        this.qtd = 1;
    }

    public Comic(int id, String title, double price, boolean isRare, String urlImage) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isRare = isRare;
        this.urlImage = urlImage;
        this.qtd = 1;
    }

    public List<Image> getImage() {
        return images;
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


    public void addQtd() {
        this.qtd+=1;
    }

    public void lowerQtd() {
        if(this.qtd > 0) {
            this.qtd-=1;
        }

    }

    public double getPrice() {
        try {
            setPrice();
            Log.i("Status price", "Coming from API");
        } catch (Exception e) {
            Log.i("Status price", "Coming from BD");
        }

        Log.i("status price", ""+price);
        Log.i("status qtd", ""+qtd);

        if(this.qtd == 0) {
            return price;
        } else {
            return price * this.qtd;
        }

    }

    public void setPrice() {
        double preco =  this.getPrices().get(0).getPrice();

        double REAL_BRASILERO = 3.76;
        this.price = preco  * REAL_BRASILERO;
    }

    public String getUrlImage() {
        try {
            setUrlImage();
        } catch (Exception e) {
            Log.i("Url status", "Coming from BD");
        }
        return urlImage;
    }

    public void setUrlImage() {
        String url = this.getThumbnail().getPath();
        String extension = this.getThumbnail().getExtension();

        this.urlImage = url + "/portrait_fantastic." + extension;

    }

    public List<CharacterSummary> getCharacters() {
        return this.characters.getItems();
    }

    public int getQtd() {
        return this.qtd;
    }
}
