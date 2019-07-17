package com.example.ecommercemarvel.model;

public class MarvelDTO {

    private Integer code;

    private String etag;

    private DataDTO data;

    public MarvelDTO(Integer code, String etag, DataDTO data) {
        this.code = code;
        this.etag = etag;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
