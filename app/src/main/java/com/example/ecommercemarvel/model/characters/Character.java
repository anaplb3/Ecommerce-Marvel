package com.example.ecommercemarvel.model.characters;

import com.example.ecommercemarvel.model.Model;
import com.example.ecommercemarvel.model.Thumbnail;

public class Character implements Model {
    private int id;
    private String name;
    private Thumbnail thumbnail;

    public Character(int id, String name, Thumbnail thumbnail) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public String getUrlImage() {
        String url = this.getThumbnail().getPath();
        String extension = this.getThumbnail().getExtension();

        return url + "/portrait_medium." + extension;
    }
}
