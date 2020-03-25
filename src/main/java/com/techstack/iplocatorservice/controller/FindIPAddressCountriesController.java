package com.techstack.iplocatorservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FindIPAddressCountriesController {

    @GetMapping("/northcountries")
    public void getCountries(@RequestParam(name = "ip") List<String> ipAddresses) {
    }
}
