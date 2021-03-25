package ru.nsu.smal.ds3.dao;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class NodeDTO {
    private long id;
    private double lat;
    private double lon;
    private String user_name;
    private long uid;
    private int version;
    private long changeset;
    private Date timestamp;
    private List<TagDTO> tags;


    public NodeDTO() {
        tags = new ArrayList<>();
    }



    public NodeDTO(long id, double lat, double lon, String user_name, long uid, int version, long changeset, Date timestamp) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.user_name = user_name;
        this.uid = uid;
        this.version = version;
        this.changeset = changeset;
        this.timestamp = timestamp;
        tags = new ArrayList<>();
    }

    public NodeDTO(long id, double lat, double lon, String user_name, long uid, int version, long changeset, Date timestamp, List<TagDTO> tags) {
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

    public long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getUser_name() {
        return user_name;
    }

    public long getUid() {
        return uid;
    }


    public int getVersion() {
        return version;
    }

    public long getChangeset() {
        return changeset;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public void setId(long id) {
        this.id = id;
    }
}
