package com.example.project.Entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;


public class Cinemas implements Serializable {

    private Long cinema_id;
    private String cinema_name;
    private String cinema_address;
    private String cinema_start_time;
    private String cinema_end_time;
    private int cinema_price;
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

    public String getCinema_start_time() {
        return cinema_start_time;
    }

    public void setCinema_start_time(String cinema_start_time) {
        this.cinema_start_time = cinema_start_time;
    }

    public String getCinema_end_time() {
        return cinema_end_time;
    }

    public void setCinema_end_time(String cinema_end_time) {
        this.cinema_end_time = cinema_end_time;
    }

    public int getCinema_price() {
        return cinema_price;
    }

    public void setCinema_price(int cinema_price) {
        this.cinema_price = cinema_price;
    }

    public List<Cities> getCities() {
        return cities;
    }

    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

    public Cinemas(Long cinema_id, String cinema_name, String cinema_address, String cinema_start_time, String cinema_end_time, int cinema_price, List<Cities> cities) {
        this.cinema_id = cinema_id;
        this.cinema_name = cinema_name;
        this.cinema_address = cinema_address;
        this.cinema_start_time = cinema_start_time;
        this.cinema_end_time = cinema_end_time;
        this.cinema_price = cinema_price;
        this.cities = cities;
    }

    public Cinemas() {
    }


}
