package com.example.ecommercemarvel.model;

import java.io.Serializable;
import java.util.List;

public class CharacterList implements Serializable {
    private int available;
    private int returned;
    private String collectionURI;
    private List<CharacterSummary> items;

    public CharacterList(int available, int returned, String collectionURI, List<CharacterSummary> items) {
        this.available = available;
        this.returned = returned;
        this.collectionURI = collectionURI;
        this.items = items;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<CharacterSummary> getItems() {
        return items;
    }

    public void setItems(List<CharacterSummary> items) {
        this.items = items;
    }
}
