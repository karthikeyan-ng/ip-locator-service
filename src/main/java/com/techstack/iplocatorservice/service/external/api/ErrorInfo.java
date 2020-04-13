package com.techstack.iplocatorservice.service.external.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorInfo {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("numberErrors")
    private String numberErrors;

}
