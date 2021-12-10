package com.example.project.Entities;

import java.io.Serializable;
import java.sql.Time;

public class Sessions implements Serializable {
    private Long session_id;
    private Time session_start_time;
    private Time session_end_time;
    private Integer session_price;
    private Films films;
    private Cinemas cinemas;

    public Sessions(Long session_id, Time session_start_time, Time session_end_time, Integer session_price, Films films, Cinemas cinemas) {
        this.session_id = session_id;
        this.session_start_time = session_start_time;
        this.session_end_time = session_end_time;
        this.session_price = session_price;
        this.films = films;
        this.cinemas = cinemas;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public Time getSession_start_time() {
        return session_start_time;
    }

    public void setSession_start_time(Time session_start_time) {
        this.session_start_time = session_start_time;
    }

    public Time getSession_end_time() {
        return session_end_time;
    }

    public void setSession_end_time(Time session_end_time) {
        this.session_end_time = session_end_time;
    }

    public Integer getSession_price() {
        return session_price;
    }

    public void setSession_price(Integer session_price) {
        this.session_price = session_price;
    }

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

    public Cinemas getCinemas() {
        return cinemas;
    }

    public void setCinemas(Cinemas cinemas) {
        this.cinemas = cinemas;
    }
}
