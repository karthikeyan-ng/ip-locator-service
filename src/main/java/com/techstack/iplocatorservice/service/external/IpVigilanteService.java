package com.techstack.iplocatorservice.service.external;

import com.techstack.iplocatorservice.service.external.api.Response;

import java.util.List;

/**
 * This Interface is responsible to call Ip Vigilante API (Third Party external)
 *
 * @author Karthikeyan
 */
public interface IpVigilanteService {

    /**
     * This method takes the List of IP address and call the 3rd Party service
     * and prepares response
     *
     * @param ipAddresses
     * @return List of Response type
     */
    List<Response> getIpAddressDetails(List<String> ipAddresses);

}
