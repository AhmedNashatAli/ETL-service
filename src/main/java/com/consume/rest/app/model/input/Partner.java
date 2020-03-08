package com.consume.rest.app.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.joda.time.LocalDate;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner {
    private String      firstName;
    private String      lastName;
    private String      email;
    private String      country;
    private LocalDate[] availableDates;

    public Partner() {
    }

    public Partner(final String firstName,
                   final String lastName,
                   final String email,
                   final String country,
                   final LocalDate[] availableDates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.availableDates = availableDates;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public LocalDate[] getAvailableDates() {
        return this.availableDates;
    }

    public void setAvailableDates(final LocalDate[] availableDates) {
        this.availableDates = availableDates;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "firstName='" + this.firstName + '\'' +
                ", lastName='" + this.lastName + '\'' +
                ", email='" + this.email + '\'' +
                ", country='" + this.country + '\'' +
                ", availableDates=" + Arrays.toString(this.availableDates) + '}';
    }
}