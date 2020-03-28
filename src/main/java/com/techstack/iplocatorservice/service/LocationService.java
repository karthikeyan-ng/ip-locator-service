package com.techstack.iplocatorservice.service;

import java.util.List;
import java.util.Map;

/**
 * This interface is responsible to get the countries associated for the given
 * IP addresses
 */
public interface LocationService {

    /**
     * This method takes the input IP address and get the response
     *
     * @param ipAddresses
     * @return Map which contains key and value as collection of Country names
     */
    Map<String, List<String>> getCountries(List<String> ipAddresses);
}
