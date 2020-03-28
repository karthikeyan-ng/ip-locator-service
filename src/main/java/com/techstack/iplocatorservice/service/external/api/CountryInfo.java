package com.techstack.iplocatorservice.service.external.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CountryInfo {

    @JsonProperty("ipv4")
    private String ipAddress;

    @JsonProperty("continent_name")
    private String continentName;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("subdivision_1_name")
    private String subDivision1Name;

    @JsonProperty("subdivision_2_name")
    private String subDivision2Name;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;
}
