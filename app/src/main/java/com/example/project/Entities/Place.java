package com.example.project.Entities;

import java.io.Serializable;

public class Place implements Serializable {
    private Long place_id;
    private Integer placeX;
    private Integer placeY;
    private Payments payments;

    public Place(Long place_id, Integer placeX, Integer placeY, Payments payments) {
        this.place_id = place_id;
        this.placeX = placeX;
        this.placeY = placeY;
        this.payments = payments;
    }

    public Long getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Long place_id) {
        this.place_id = place_id;
    }

    public Integer getPlaceX() {
        return placeX;
    }

    public void setPlaceX(Integer placeX) {
        this.placeX = placeX;
    }

    public Integer getPlaceY() {
        return placeY;
    }

    public void setPlaceY(Integer placeY) {
        this.placeY = placeY;
    }

    public Payments getPayments() {
        return payments;
    }

    public void setPayments(Payments payments) {
        this.payments = payments;
    }
}
