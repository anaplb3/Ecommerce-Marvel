package com.example.ecommercemarvel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarvelData {

    private int total;
    private int count;
    private List<Comic> results;
    private int numberOfRareComics;
    private List<Integer> randomNumbers;

    public MarvelData(int total, int count, List<Comic> results) {
        this.total = total;
        this.count = count;
        this.results = results;
        this.numberOfRareComics = (12 * total) / 100;
    }

    /**
     * A partir do número de quadrinhos que serão raros, gera números aleatórios para serem usados como index na hora de escolher quadrinhos raros
     *
     * @return Um array com os números aleatórios gerados
     */
    private void creatingIndexOfRareComics() {
        Random random = new Random();
        randomNumbers = new ArrayList<>();
        int randomNumber;



        for (int i = 0; i < numberOfRareComics; i++) {
            randomNumber = random.nextInt(numberOfRareComics);


            while(randomNumbers.contains(randomNumber)) {

                randomNumber = random.nextInt(numberOfRareComics);
            }

            randomNumbers.add(randomNumber);

        }

    }

    public int getNumberOfRareComics() {
        return numberOfRareComics;
    }

    public void setNumberOfRareComics(int numberOfRareComics) {
        this.numberOfRareComics = numberOfRareComics;
    }

    /**
     * Marca os quadrinhos que tem index igual ao número aleatório da lista como 'Raro'
     */
    private void checkingRareComics() {
        creatingIndexOfRareComics();

        for (int i = 0; i < results.size(); i++) {

            for (int k = 0; k < randomNumbers.size(); k++) {

                if (i == randomNumbers.get(k)) {

                    results.get(i).setRare(true);
                }
            }
        }
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    public void setRandomNumbers(List<Integer> randomNumbers) {
        this.randomNumbers = randomNumbers;
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
        checkingRareComics();
        return results;
    }

    public void setComics(List<Comic> comics) {
        this.results = comics;
    }
}
