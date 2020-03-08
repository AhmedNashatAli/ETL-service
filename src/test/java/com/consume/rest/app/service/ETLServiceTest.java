package com.consume.rest.app.service;

import com.consume.rest.app.model.input.RowData;
import com.consume.rest.app.model.output.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ETLServiceTest {

    static ETLService etlService;

    @BeforeClass
    public static void before() {
        etlService = new ETLServiceImpl();
    }

    @Test
    public void processRowDataTest() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        final RowData rowData = objectMapper.readValue(new File("src/test/resources/sampleRowData.json"),
                                                       RowData.class);
        final Result expectedResult = objectMapper.readValue(new File("src/test/resources/sampleResult.json"),
                                                             Result.class);
        final Result result = etlService.processRowData(rowData);
        Assert.assertEquals(expectedResult.getCountries().get(0).getAttendeeCount(),
                            result.getCountries().get(0).getAttendeeCount());
        Assert.assertEquals(expectedResult.getCountries().get(1).getAttendeeCount(),
                            result.getCountries().get(1).getAttendeeCount());
        Assert.assertEquals(expectedResult.getCountries().get(2).getAttendeeCount(),
                            result.getCountries().get(2).getAttendeeCount());
        Assert.assertEquals(expectedResult.getCountries().get(0).getName(), result.getCountries().get(0).getName());
        Assert.assertEquals(expectedResult.getCountries().get(1).getName(), result.getCountries().get(1).getName());
        Assert.assertEquals(expectedResult.getCountries().get(2).getName(), result.getCountries().get(2).getName());
        Assert.assertEquals(expectedResult.getCountries().get(1).getAttendees().get(0),
                            result.getCountries().get(1).getAttendees().get(0));
        Assert.assertEquals(expectedResult.getCountries().get(2).getAttendees().get(0),
                            result.getCountries().get(2).getAttendees().get(0));
        Assert.assertEquals(expectedResult.getCountries().size(), result.getCountries().size());
    }
}
