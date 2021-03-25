package ru.nsu.smal.ds3.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "tags")
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "k")
    private String k;

    @Column(name = "v")
    private String v;



    public TagEntity() {
    }

    public TagEntity(Long id, String k, String v) {
        this.id = id;
        this.k = k;
        this.v = v;
    }


    public TagEntity(String k, String v) {
        this.k = k;
        this.v = v;
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }



}
