package com.example.ecommercemarvel.model;


public class ResponseDTO {

    private String code;
    private String status;
    private MarvelData data;

    public ResponseDTO(String code, String status, MarvelData data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MarvelData getData() {
        return data;
    }

    public void setData(MarvelData data) {
        this.data = data;
    }
}
