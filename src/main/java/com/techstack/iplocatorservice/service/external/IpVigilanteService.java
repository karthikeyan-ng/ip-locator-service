package com.techstack.iplocatorservice.service.external;

import com.techstack.iplocatorservice.service.external.api.Response;

import java.util.List;

public interface IpVigilanteService {

    List<Response> getIpAddressDetails(List<String> ipAddresses);

}
