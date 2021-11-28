package com.example.project.Entities;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Films {
    private Long film_id;
    private String film_name;
    private Time film_duration;
    private Date film_date;
    private List<Countries> countries;
    private List<Directors> directors;
    private List<Genres> genres;
    private List<Cinemas> cinemas;

    public Films() {
    }

    public Films(Long film_id, String film_name, Time film_duration, Date film_date) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_duration = film_duration;
        this.film_date = film_date;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public Time getFilm_duration() {
        return film_duration;
    }

    public void setFilm_duration(Time film_duration) {
        this.film_duration = film_duration;
    }

    public Date getFilm_date() {
        return film_date;
    }

    public void setFilm_date(Date film_date) {
        this.film_date = film_date;
    }
}
