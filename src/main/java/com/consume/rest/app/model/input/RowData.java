package com.consume.rest.app.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RowData {
    private Partner[] partners;

    public RowData() {
    }

    public RowData(final Partner[] partners) {
        this.partners = partners;
    }

    public Partner[] getPartners() {
        return this.partners;
    }

    public void setPartners(final Partner[] partners) {
        this.partners = partners;
    }

    @Override
    public String toString() {
        return "Input{" +
                "partners=" + Arrays.toString(this.partners) +
                '}';
    }
}
