package com.techstack.iplocatorservice.service;

import java.util.List;
import java.util.Map;

public interface LocationService {

    Map<String, List<String>> getCountries(List<String> ipAddresses);
}
