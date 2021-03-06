package com.techstack.iplocatorservice.service.external;

import com.techstack.iplocatorservice.service.external.api.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class IpVigilanteServiceImpl implements IpVigilanteService {

    private final RestTemplate restTemplate;

    @Value("${ip.vigilante.json.default}")
    private String defaultUrl;

    @Override
    public List<Response> getIpAddressDetails(List<String> ipAddresses) {

        List<Response> responses = new ArrayList<>();

        for (String ipAddress : ipAddresses) {

            Map<String, String> uriParams = new HashMap<>();
            uriParams.put("IP", ipAddress);
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(defaultUrl).path("{IP}");

            Response response;
            try {
                ResponseEntity<Response> responseEntity =
                        this.restTemplate.getForEntity(builder.buildAndExpand(uriParams).toUri(), Response.class);

                response = responseEntity.getBody();
            } catch (RuntimeException e) {
                //Developer comments: Sometimes facing error response for the given request.
                //Even though passing the correct request. Hence, if any RuntimeException go to
                //next IP address for processing.
                log.error("Error while processing {}  - {}", ipAddress, e.getMessage());
                continue;
            }
            responses.add(response);
        }
        return responses;
    }
}
