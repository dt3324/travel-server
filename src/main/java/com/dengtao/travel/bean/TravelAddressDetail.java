package com.dengtao.travel.bean;

import java.util.ArrayList;
import java.util.List;

public class TravelAddressDetail {
    private Long id;
    private String addressName;//地点名称
    private String synopsis;//简介
    private String text;
    private Long traveladdressId;
    private String images;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTraveladdressId() {
        return traveladdressId;
    }

    public void setTraveladdressId(Long traveladdressId) {
        this.traveladdressId = traveladdressId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "TravelAddressDetail{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", images=" + images +
                '}';
    }
}
