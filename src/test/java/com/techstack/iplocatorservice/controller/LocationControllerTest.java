package com.techstack.iplocatorservice.controller;

import com.techstack.iplocatorservice.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller Test
 */
@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @Test
    void getCountriesShouldReturnMessageFromService() throws Exception {
        Map<String, List<String>> response = new HashMap<>();
        List<String> countries = new ArrayList<>();
        countries.add("United States");
        response.put("northcountries", countries);

        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("ip", "8.8.8.8");

        when(locationService.getCountries(anyList())).thenReturn(response);
        this.mockMvc.perform(get("/northcountries").queryParams(multiValueMap))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("United States")));
    }

    @Test
    void getCountriesShouldReturnMessageFromService_ErrorResponse() throws Exception {
        Map<String, List<String>> response = new HashMap<>();
        List<String> countries = new ArrayList<>();
        countries.add("United States");
        response.put("northcountries", countries);

        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("ip", "");

        when(locationService.getCountries(anyList())).thenReturn(response);
        this.mockMvc.perform(get("/northcountries").queryParams(multiValueMap))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        containsString("Accepted input is minimum 1 and maximum 5 IP addresses at a time")));
    }
}