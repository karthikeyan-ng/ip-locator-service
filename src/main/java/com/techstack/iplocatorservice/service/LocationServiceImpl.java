package com.techstack.iplocatorservice.service;

import com.techstack.iplocatorservice.service.external.IpVigilanteService;
import com.techstack.iplocatorservice.service.external.api.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final IpVigilanteService ipVigilanteService;

    @Override
    public Map<String, List<String>> getCountries(final List<String> ipAddresses) {

        List<Response> responses = processIpAddresses(ipAddresses);
        Map<String, List<String>> filteredCountries = validateAndGetCountries(responses);
        return filteredCountries;
    }

    public List<Response> processIpAddresses(final List<String> ipAddresses) {

        List<Response> responses = ipVigilanteService.getIpAddressDetails(ipAddresses);
        return responses;
    }

    public Map<String, List<String>> validateAndGetCountries(final List<Response> responses) {

        List<String> countries =
                responses.stream()
                        .filter(e -> e.getStatus().equals("success"))
                        .map(e -> e.getCountryInfo().getCountryName())
                        .distinct()
                        .collect(Collectors.toList());

        Map<String, List<String>> countriesMap = new HashMap<>();
        countriesMap.put("northcountries", countries);
        return countriesMap;
    }

}
