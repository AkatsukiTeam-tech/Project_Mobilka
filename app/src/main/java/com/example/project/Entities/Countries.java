package com.example.project.Entities;

import java.io.Serializable;


public class Countries implements Serializable{

    private Long country_id;

    private String country_name;

    public Countries(Long country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }

    public Countries() {
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
