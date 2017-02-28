package com.example.user.TourTalking.domain.mian;

import java.util.List;

/**
 * Created by user on 2017-02-20.
 */

public class Country {
    private int continent_id;
    private String country_name;
    private List<City> city;

    public int getContinent_id() {
        return continent_id;
    }

    public void setContinent_id(int continent_id) {
        this.continent_id = continent_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }
}
