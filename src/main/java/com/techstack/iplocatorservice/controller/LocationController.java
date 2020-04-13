package com.techstack.iplocatorservice.controller;

import com.techstack.iplocatorservice.controller.validator.IpAddressFormatCheck;
import com.techstack.iplocatorservice.controller.validator.IpAddressSizeCheck;
import com.techstack.iplocatorservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * This is a Rest Controller for Locations related.
 *
 * @author Karthikeyan
 */
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
public class LocationController {

    private final LocationService locationService;

    /**
     * This method is responsible to validate the given input and call the respective service to
     * prepare the response.
     *
     * @param ipAddresses
     * @return
     */
    @GetMapping("/northcountries")
    public ResponseEntity<Map<String, List<String>>> getCountries(@RequestParam(name = "ip")
                                                                  @IpAddressSizeCheck @IpAddressFormatCheck List<String> ipAddresses) {

        log.debug("Given IP addresses are {}", ipAddresses);

        Map<String, List<String>> country = this.locationService.getCountries(ipAddresses);

        return ResponseEntity.ok().body(country);
    }

}
