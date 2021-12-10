package com.example.project.Entities;

import java.io.Serializable;

public class Trailers implements Serializable {
    private Long trailer_id;
    private String trailer_url;
    private Films films;

    public Trailers(Long trailer_id, String trailer_url, Films films) {
        this.trailer_id = trailer_id;
        this.trailer_url = trailer_url;
        this.films = films;
    }

    public Long getTrailer_id() {
        return trailer_id;
    }

    public void setTrailer_id(Long trailer_id) {
        this.trailer_id = trailer_id;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }
}
