package com.example.ecommercemarvel.test;


import com.example.ecommercemarvel.model.Comic;
import com.example.ecommercemarvel.model.MarvelData;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarvelDataTest {
    MarvelData marvelData;


    @Before
    public void setUp() {
        List<String> prices = new ArrayList<>();
        prices.add("printPrice");
        prices.add("3.99");

        List<String> thumbnail = new ArrayList<>();
        Comic c1 = new Comic(11111, "homem aranha", "hq do miranha", prices,thumbnail);
        Comic c2 = new Comic(22222, "Iron man", "hq do homem de ferro", prices,thumbnail);
        Comic c3 = new Comic(11111, "thor", "hq do thor", prices,thumbnail);
        Comic c4 = new Comic(11111, "Iron Man Extreme", "hq do homem de ferro de novo pq ele é o melhor personagem", prices,thumbnail);
        Comic c5 = new Comic(11111, "Capitã Marvel", "hq da capitã marvel", prices,thumbnail);

        List<Comic> comics = new ArrayList<>();
        comics.add(c1);
        comics.add(c2);
        comics.add(c3);
        comics.add(c4);
        comics.add(c5);

        marvelData = new MarvelData(5, 5,comics);
    }


    @Test
    public void testCheckingRareComics() {
        List<Integer> randomNumbers = new ArrayList<>();

        randomNumbers.add(3);
        randomNumbers.add(1);

        marvelData.setRandomNumbers(randomNumbers);

        List<Comic> comics = marvelData.getComics();
        marvelData.setNumberOfRareComics(2);

        Comic c = comics.get(3);

        assertTrue(comics.get(3).isRare());
        assertTrue(comics.get(1).isRare());



    }

}