package com.example.ecommercemarvel.model;


import com.example.ecommercemarvel.controller.Controller;

import java.util.List;

public class ComicFacade {
    Controller comicController;
    ResponseDTO responseDTO;
    MarvelData marvelData;

    public ComicFacade() {
        this.comicController = new Controller();
        this.comicController.create();

        this.responseDTO = comicController.getResponseDTO();
    }

    public List<Comic> getComics() {

        /*while(comicController.isLoading()) {
            Log.i("loop", "infinito");
        }*/

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        marvelData = responseDTO.getData();
        return marvelData.getComics();
    }
}
