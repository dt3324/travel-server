package com.dengtao.travel.bean;

public class TravelAddress {
    private Long id;
    private String addressName;//地点名称
    private String synopsis;//简介

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "TravelAddress{" +
                "id=" + id +
                ", addressName='" + addressName + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
