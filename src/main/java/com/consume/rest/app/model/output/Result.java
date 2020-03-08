package com.consume.rest.app.model.output;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private List<Country> countries;

    public Result(final List<Country> countries) {
        this.countries = countries;
    }

    public Result() {
        this.countries = new ArrayList<>();
    }

    public List<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(final List<Country> countries) {
        this.countries = countries;
    }
}