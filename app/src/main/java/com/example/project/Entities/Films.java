package com.example.project.Entities;

import java.io.Serializable;
import java.util.List;

public class Films implements Serializable {
    private Long film_id;
    private String film_ru_name;
    private String film_orig_name;
    private String image_url;
    private String description;
    private Integer restriction;
    private String duration;
    private String date;
    private List<Countries> countries;
    private List<Directors> directors;
    private List<Genres> genres;
    private List<Cinemas> cinema;
    private boolean announce;

    public Films(Long film_id, String film_ru_name, String film_orig_name, String image_url, String description,
                 int restriction, String duration, String date, List<Countries> countries,
                 List<Directors> directors, List<Genres> genres, List<Cinemas> cinemas,boolean announce) {
        this.film_id = film_id;
        this.film_ru_name = film_ru_name;
        this.film_orig_name = film_orig_name;
        this.image_url = image_url;
        this.description = description;
        this.restriction = restriction;
        this.duration = duration;
        this.date = date;
        this.countries = countries;
        this.directors = directors;
        this.genres = genres;
        this.cinema = cinemas;
        this.announce = announce;
    }

    public Films() {
    }

    public boolean isAnnounce() {
        return announce;
    }

    public void setAnnounce(boolean announce) {
        this.announce = announce;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public String getFilm_ru_name() {
        return film_ru_name;
    }

    public void setFilm_ru_name(String film_ru_name) {
        this.film_ru_name = film_ru_name;
    }

    public String getFilm_orig_name() {
        return film_orig_name;
    }

    public void setFilm_orig_name(String film_orig_name) {
        this.film_orig_name = film_orig_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRestriction() {
        return restriction;
    }

    public void setRestriction(int restriction) {
        this.restriction = restriction;
    }

    public String getFilm_duration() {
        return duration;
    }

    public void setFilm_duration(String duration) {
        this.duration = duration;
    }

    public String getFilm_date() {
        return date;
    }

    public void setFilm_date(String date) {
        this.date = date;
    }

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

    public List<Cinemas> getCinemas() {
        return cinema;
    }

    public void setCinemas(List<Cinemas> cinemas) {
        this.cinema = cinemas;
    }
}
