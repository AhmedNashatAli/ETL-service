package com.consume.rest.app.controller;

import com.consume.rest.app.model.input.RowData;
import com.consume.rest.app.model.output.Result;
import com.consume.rest.app.service.ETLService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("/etl")
public class ETLRestController {
    @Value("${endpoint.get}")
    String     getDataEndpoint;
    @Value("${endpoint.post}")
    String     postDataEndpoint;
    @Autowired
    ETLService etlService;

    private Result result = new Result();

    @GetMapping("/extract")
    public Result extractAttendees() throws IOException {
        final RestTemplate getRestTemplate = new RestTemplate();
        final ResponseEntity<RowData> responseEntity = getRestTemplate.getForEntity(
                this.getDataEndpoint, RowData.class);

        final RowData rowData = responseEntity.getBody();
        this.result = this.etlService.processRowData(rowData);

        return this.result;
    }

    @GetMapping("/extract2")
    public Result extractAttendees2() throws IOException {
        final RestTemplate getRestTemplate = new RestTemplate();
        final ResponseEntity<RowData> responseEntity = getRestTemplate.getForEntity(
                this.getDataEndpoint, RowData.class);

        final RowData rowData = responseEntity.getBody();
        this.result = this.etlService.processRowData2(rowData);

        return this.result;
    }

    @GetMapping("/load")
    public String loadAttendees() throws IOException {
        final String answer = postTheResult(this.result);

        return answer;
    }

    private String postTheResult(final Result result) throws JsonProcessingException {
        final RestTemplate postRestTemplate = new RestTemplate();
        postRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        final ObjectMapper mapper = new ObjectMapper();

        final String requestJson = mapper.writeValueAsString(result);
        final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");
        headers.add("Host", "candidate.hubteam.com");
        headers.add("Accept", "application/json");
        final HttpEntity<?> entity = new HttpEntity<>(requestJson, headers);
        System.out.println(requestJson);
        return postRestTemplate.postForObject(this.postDataEndpoint, entity, String.class);
    }


}
