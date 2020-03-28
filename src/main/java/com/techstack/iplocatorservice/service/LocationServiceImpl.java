package com.techstack.iplocatorservice.service;

import com.techstack.iplocatorservice.service.external.IpVigilanteService;
import com.techstack.iplocatorservice.service.external.api.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class LocationServiceImpl implements LocationService {

    private final IpVigilanteService ipVigilanteService;

    @Override
    public Map<String, List<String>> getCountries(List<String> ipAddresses) {

        List<Response> responses = ipVigilanteService.getIpAddressDetails(ipAddresses);

        //TODO: Further process

        Map<String, List<String>> countriesMap = new HashMap<>();
        List<String> countries = new ArrayList<>();
        countries.add("Colombia");
        countries.add("Japan");
        countries.add("United States");
        countriesMap.put("northcountries", countries);
        return countriesMap;
    }
}
