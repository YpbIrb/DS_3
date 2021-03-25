package ru.nsu.smal.ds3.entity;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;


@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "nodes")
public class NodeEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private BigInteger id;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "uid")
    private BigInteger uid;

    @Column(name = "version")
    private BigInteger version;

    @Column(name = "changeset")
    private BigInteger changeset;

    @Column(name = "timestamp")
    private Date timestamp;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TagEntity> tags;


    public NodeEntity() {
    }

    public NodeEntity(BigInteger id, Double lat, Double lon, String user_name, BigInteger uid, BigInteger version, BigInteger changeset, Date timestamp, List<TagEntity> tags) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.user_name = user_name;
        this.uid = uid;
        this.version = version;
        this.changeset = changeset;
        this.timestamp = timestamp;
        this.tags = tags;
    }



    public NodeEntity(Double lat, Double lon, String user_name, BigInteger uid, BigInteger version, BigInteger changeset, Date timestamp, List<TagEntity> tags) {
        this.lat = lat;
        this.lon = lon;
        this.user_name = user_name;
        this.uid = uid;
        this.version = version;
        this.changeset = changeset;
        this.timestamp = timestamp;
        this.tags = tags;
    }




    public BigInteger getId() {
        return id;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public BigInteger getUid() {
        return uid;
    }

    public void setUid(BigInteger uid) {
        this.uid = uid;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public BigInteger getChangeset() {
        return changeset;
    }

    public void setChangeset(BigInteger changeset) {
        this.changeset = changeset;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestomp) {
        this.timestamp = timestomp;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }





}
