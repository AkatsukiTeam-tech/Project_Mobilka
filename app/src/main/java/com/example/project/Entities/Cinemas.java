package com.example.project.Entities;

import java.io.Serializable;
import java.util.List;


public class Cinemas implements Serializable {

    private Long cinema_id;
    private String ciname_name;
    private String ciname_address;
    private Cities cities;
    private List<Films> films;

    public Cinemas(Long cinema_id, String ciname_name, String ciname_address, Cities cities) {
        this.cinema_id = cinema_id;
        this.ciname_name = ciname_name;
        this.ciname_address = ciname_address;
        this.cities = cities;
    }

    public Cinemas() {
    }

    public Long getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Long cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getCiname_name() {
        return ciname_name;
    }

    public void setCiname_name(String ciname_name) {
        this.ciname_name = ciname_name;
    }

    public String getCiname_address() {
        return ciname_address;
    }

    public void setCiname_address(String ciname_address) {
        this.ciname_address = ciname_address;
    }

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }

    public List<Films> getFilms() {
        return films;
    }

    public void setFilms(List<Films> films) {
        this.films = films;
    }
}
