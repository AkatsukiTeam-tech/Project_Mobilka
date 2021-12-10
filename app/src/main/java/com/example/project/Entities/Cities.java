package com.example.project.Entities;

import java.io.Serializable;

public class Cities implements Serializable {
    private Long city_id;
    private String city_name;

    public Cities(Long city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public Cities() {
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
