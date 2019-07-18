package com.example.ecommercemarvel.dbHelper;

import com.example.ecommercemarvel.model.Comic;

import java.util.List;

public interface ComicsDAO {

    void addingToTheCart(int idComic, String title, double price, int isRare);
    List<Comic> getComicsInTheCart();
}
