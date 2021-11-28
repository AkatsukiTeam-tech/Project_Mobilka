package com.example.project.Entities;

import java.util.List;

public class Cadres {
    private Long cadr_id;
    private String cadr_url;
    private Films film;

    public Cadres(Long cadr_id, String cadr_url, Films film) {
        this.cadr_id = cadr_id;
        this.cadr_url = cadr_url;
        this.film = film;
    }

    public Long getCadr_id() {
        return cadr_id;
    }

    public void setCadr_id(Long cadr_id) {
        this.cadr_id = cadr_id;
    }

    public String getCadr_url() {
        return cadr_url;
    }

    public void setCadr_url(String cadr_url) {
        this.cadr_url = cadr_url;
    }

    public Films getFilm() {
        return film;
    }

    public void setFilm(Films film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "Cadres{" +
                "cadr_id=" + cadr_id +
                ", cadr_url='" + cadr_url + '\'' +
                ", film=" + film +
                '}';
    }
}
