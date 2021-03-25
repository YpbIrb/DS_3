package ru.nsu.smal.ds3.dao;

public class TagDTO {
    private String k;
    private String v;

    public TagDTO( String k, String v) {
        this.k = k;
        this.v = v;
    }

    public TagDTO() {
    }

    public String getK() {
        return k;
    }

    public String getV() {
        return v;
    }
}
