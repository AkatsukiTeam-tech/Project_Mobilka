package com.example.project.Entities;

import java.sql.Date;
import java.sql.Time;

public class Purchases {

    private Date date;
    private int price;
    private String film;
    private Time time;
    private String image;

    public Purchases(Date date, int price, String film, Time time, String image) {
        this.date = date;
        this.price = price;
        this.film = film;
        this.time = time;
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
