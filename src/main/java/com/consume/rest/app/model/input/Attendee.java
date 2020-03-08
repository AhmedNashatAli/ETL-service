package com.consume.rest.app.model.input;


import org.joda.time.LocalDate;

public class Attendee {
    private String    email;
    private String    country;
    private LocalDate startDate;
    private LocalDate endDate;

    public Attendee() {
    }

    public Attendee(final String email,
                    final String country,
                    final LocalDate startDate,
                    final LocalDate endDate) {
        this.email = email;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ExplodedPartner{" +
                "email='" + this.email + '\'' +
                ", country='" + this.country + '\'' +
                ", startDate=" + this.startDate +
                ", endDate=" + this.endDate +
                '}';
    }
}
