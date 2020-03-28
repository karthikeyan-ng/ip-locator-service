package com.techstack.iplocatorservice.service.external.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Response {

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private CountryInfo countryInfo;

    @JsonProperty("errors")
    private List<ErrorInfo> errors;

}
