package com.techstack.iplocatorservice.service;

import com.techstack.iplocatorservice.service.external.IpVigilanteService;
import com.techstack.iplocatorservice.service.external.api.CountryInfo;
import com.techstack.iplocatorservice.service.external.api.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @InjectMocks
    LocationServiceImpl locationService;

    @Mock
    IpVigilanteService ipVigilanteService;

    @Test
    void getCountriesShouldReturnSuccessResponse() throws Exception {

        List<String> ipAddresses = new ArrayList<>();
        ipAddresses.add("8.8.8.8");

        when(ipVigilanteService.getIpAddressDetails(anyList())).thenReturn(createMockResponses());
        Map<String, List<String>> response = locationService.getCountries(ipAddresses);
        assertEquals(1, response.size());
    }

    private List<Response> createMockResponses() {

        List<Response> responses = new ArrayList<>();
        Response response = new Response();
        response.setStatus("success");
        CountryInfo countryInfo = new CountryInfo();
        countryInfo.setIpAddress("8.8.8.8");
        countryInfo.setContinentName("North America");
        countryInfo.setCountryName("United States");
        countryInfo.setSubDivision1Name("California");
        countryInfo.setSubDivision2Name(null);
        countryInfo.setCityName("Mountain View");
        countryInfo.setLatitude("37.38600");
        countryInfo.setLongitude("-122.08380");
        response.setCountryInfo(countryInfo);
        responses.add(response);
        return responses;
    }
}