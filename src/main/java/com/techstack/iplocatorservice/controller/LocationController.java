package com.techstack.iplocatorservice.controller;

import com.techstack.iplocatorservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Validated
@RestController
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/northcountries")
    public ResponseEntity<Map<String, List<String>>> getCountries(@Valid
                                                                  @RequestParam(name = "ip")
                                                                  @Size(min = 1, max = 5, message = "User can input minimum 1 and maximum 5 IP addresses at a time")
                                                                          List<String> ipAddresses) {

        Map<String, List<String>> country = this.locationService.getCountries(ipAddresses);

        return ResponseEntity.ok().body(country);
    }

}
