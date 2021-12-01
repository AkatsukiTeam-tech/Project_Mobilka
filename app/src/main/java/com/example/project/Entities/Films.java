package com.example.project.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Films implements Serializable {
    private Long film_id;
    private String film_name;
    private Time film_duration;
    private Date film_date;
    private List<Countries> countries;
    private List<Directors> directors;
    private List<Genres> genres;
    private Cinemas cinemas;

    public List<Countries> getCountries() {
        return countries;
    }

    public void setCountries(List<Countries> countries) {
        this.countries = countries;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public Cinemas getCinemas() {
        return cinemas;
    }

    public void setCinemas(Cinemas cinemas) {
        this.cinemas = cinemas;
    }

    public Films(Long film_id, String film_name, Time film_duration, Date film_date, List<Countries> countries, List<Directors> directors, List<Genres> genres, Cinemas cinemas) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_duration = film_duration;
        this.film_date = film_date;
        this.countries = countries;
        this.directors = directors;
        this.genres = genres;
        this.cinemas = cinemas;
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
