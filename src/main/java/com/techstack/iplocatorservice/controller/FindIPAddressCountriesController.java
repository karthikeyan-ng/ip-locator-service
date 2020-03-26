package com.techstack.iplocatorservice.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
public class FindIPAddressCountriesController {

    @GetMapping("/northcountries")
    public void getCountries(@Valid
                            @RequestParam(name = "ip")
                            @Size(min = 1, max = 5, message = "User can input minimum 1 and maximum 5 IP addresses at a time")
                                         List<String> ipAddresses) {
    }

}
