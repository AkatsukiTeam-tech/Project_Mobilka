package com.example.project.Entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;


public class Cinemas implements Serializable {

    private Long cinema_id;
    private String cinema_name;
    private String cinema_address;
    private List<Cities> cities;

    public Long getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Long cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getCinema_address() {
        return cinema_address;
    }

    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }

    public List<Cities> getCities() {
        return cities;
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

    public Cinemas(Long cinema_id, String cinema_name, String cinema_address, List<Cities> cities) {
        this.cinema_id = cinema_id;
        this.cinema_name = cinema_name;
        this.cinema_address = cinema_address;
        this.cities = cities;
    }

    public Cinemas() {
    }


}
