package com.example.ecommercemarvel.model;

import java.util.List;

public class DataDTO {

    private Integer total;

    private Integer count;

    private List<Comic> results;

    public DataDTO(Integer total, Integer count, List<Comic> results) {
        this.total = total;
        this.count = count;
        this.results = results;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Comic> getResults() {
        return results;
    }

    public void setResults(List<Comic> results) {
        this.results = results;
    }
}
