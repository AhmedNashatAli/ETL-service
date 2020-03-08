package com.consume.rest.app.model.output;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Country {
    private int          attendeeCount;
    private List<String> attendees;
    private String       name;
    private LocalDate    startDate;

    public int getAttendeeCount() {
        return this.attendeeCount;
    }

    public void setAttendeeCount(final int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    public List<String> getAttendees() {
        return this.attendees;
    }

    public void setAttendees(final List<String> attendees) {
        this.attendees = attendees;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public Country() {
    }

    public Country(final String name) {
        this.attendeeCount = 0;
        this.name = name;
        this.attendees = new ArrayList<>();
        this.startDate = null;
    }

    public Country(final int attendeeCount,
                   final List<String> attendees,
                   final String name,
                   final LocalDate startDate) {
        this.attendeeCount = attendeeCount;
        this.attendees = attendees;
        this.name = name;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Country{" +
                "attendeeCount=" + this.attendeeCount +
                ", attendees=" + this.attendees +
                ", name='" + this.name + '\'' +
                ", startDate=" + this.startDate +
                '}';
    }
}
