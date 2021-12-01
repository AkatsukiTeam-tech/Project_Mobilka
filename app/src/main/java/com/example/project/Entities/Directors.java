package com.example.project.Entities;
import java.io.Serializable;

public class Directors implements Serializable {

    private Long director_id;
    private String full_name;

    public Directors(Long director_id, String full_name) {
        this.director_id = director_id;
        this.full_name = full_name;
    }

    public Directors() {
    }

    public Long getDirector_id() {
        return director_id;
    }

    public void setDirector_id(Long director_id) {
        this.director_id = director_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
