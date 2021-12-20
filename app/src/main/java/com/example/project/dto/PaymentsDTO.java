package com.example.project.dto;

import com.example.project.Entities.Cinemas;
import com.example.project.Entities.Films;
import com.example.project.Entities.Place;
import com.example.project.Entities.User;

import java.util.List;

public class PaymentsDTO {
    private List<Place> places;
    private Films films;
    private Cinemas cinemas;
    private User user;
    private Integer price;


    public PaymentsDTO(List<Place> places, Films films, Cinemas cinemas, User user, Integer price) {
        this.places = places;
        this.films = films;
        this.cinemas = cinemas;
        this.user = user;
        this.price = price;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
