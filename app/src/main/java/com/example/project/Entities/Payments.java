package com.example.project.Entities;

import java.io.Serializable;
import java.sql.Date;

public class Payments implements Serializable {
    private Long payment_id;
    private Date date;
    private Films film;
    private User user;

    public Payments(Long payment_id, Date date, Films film, User user) {
        this.payment_id = payment_id;
        this.date = date;
        this.film = film;
        this.user = user;
    }

    public Long getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Long payment_id) {
        this.payment_id = payment_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Films getFilm() {
        return film;
    }

    public void setFilm(Films film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
