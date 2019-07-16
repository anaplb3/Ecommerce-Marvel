package com.example.ecommercemarvel.model;

import java.util.List;

class MarvelData {

    private int total;
    private int count;
    private List<Comic> comics;

    public MarvelData(int total, int count, List<Comic> comics) {
        this.total = total;
        this.count = count;
        this.comics = comics;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }
}
