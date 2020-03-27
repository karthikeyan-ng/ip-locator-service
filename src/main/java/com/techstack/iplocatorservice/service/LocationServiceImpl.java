package com.techstack.iplocatorservice.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LocationServiceImpl implements LocationService {

    @Override
    public Map<String, List<String>> getCountries(List<String> ipAddresses) {

        Map<String, List<String>> countriesMap = new HashMap<>();
        List<String> countries = new ArrayList<>();
        countries.add("Colombia");
        countries.add("Japan");
        countries.add("United States");
        countriesMap.put("northcountries", countries);
        return countriesMap;
    }
}
