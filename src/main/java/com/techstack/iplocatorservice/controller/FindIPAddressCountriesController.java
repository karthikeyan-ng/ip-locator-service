package com.techstack.iplocatorservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindIPAddressCountriesController {

    @GetMapping("/northcountries")
    public void getCountries() {
    }
}
