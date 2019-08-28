package com.example.ecommercemarvel.test;


import com.example.ecommercemarvel.model.Comic;
import com.example.ecommercemarvel.model.MarvelDataComics;
import com.example.ecommercemarvel.model.Price;
import com.example.ecommercemarvel.model.Thumbnail;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MarvelDataComicsTest {
    MarvelDataComics marvelData;


    @Before
    public void setUp() {

        List<Comic> comics = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            comics.add(generatingComics(i*12));
        }


        marvelData = new MarvelDataComics(comics.size(), 5,comics);
    }

    private Comic generatingComics(int id) {
        Price price = new Price("printPrice", 3.99);
        Thumbnail thumbnail = new Thumbnail("bla bla", "jpg");

        List<Price> prices = new ArrayList<>();
        prices.add(price);

        return new Comic(id,"Iron Man", "Melhor herói da Marvel, é isto", prices, thumbnail);
    }


    @Test
    public void testCheckingRareComics() {

        List<Comic> comics = marvelData.getComics();
        List<Integer> randomNumbers = marvelData.getRandomNumbers();

        for(int i = 0; i < randomNumbers.size(); i++) {
            assertTrue(comics.get(randomNumbers.get(i)).isRare());
        }

    }

    @Test
    public void testGetting12Percent() {

        int twelvePercent = (12 * marvelData.getComics().size()) / 100;
        assertEquals(twelvePercent, marvelData.getNumberOfRareComics());


    }

}