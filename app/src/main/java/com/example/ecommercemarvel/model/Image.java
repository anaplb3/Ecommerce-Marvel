package com.example.ecommercemarvel.model;

import android.util.Log;

import java.io.Serializable;

public class Image implements Serializable {
    String path;
    String extension;

    public Image(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    private String getPath() {
        return path;
    }

    public String getUrlImage() {
       return path + "/portrait_fantastic." + extension;

    }

    public String getExtension() {
        return extension;
    }
}
