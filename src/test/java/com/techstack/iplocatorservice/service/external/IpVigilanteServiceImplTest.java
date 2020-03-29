package com.techstack.iplocatorservice.service.external;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@AutoConfigureWireMock(port = 8081)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class IpVigilanteServiceImplTest {

    @Test
    public void using_WireMock_GetTheSuccessStub() {
        String json = "{\n" +
                "\"status\": \"success\",\n" +
                "\"data\": {\n" +
                "\"ipv4\": \"8.8.8.8\",\n" +
                "\"continent_name\": \"North America\",\n" +
                "\"country_name\": \"United States\",\n" +
                "\"subdivision_1_name\": \"California\",\n" +
                "\"subdivision_2_name\": null,\n" +
                "\"city_name\": \"Mountain View\",\n" +
                "\"latitude\": \"37.38600\",\n" +
                "\"longitude\": \"-122.08380\"\n" +
                "}\n" +
                "}";

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/json/8.8.8.8"))
                .willReturn(WireMock.aResponse().withBody(json).withStatus(201)));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/json/8.8.8.8", String.class);

        BDDAssertions.then(response.getStatusCode().value()).isEqualTo(201);
        BDDAssertions.then(response.getBody()).isEqualTo(json);
    }

    @Test
    public void using_WireMock_GetTheFailureStub() {
        String json = "{\n" +
                "    \"status\": \"error\",\n" +
                "    \"errors\": [\n" +
                "        {\n" +
                "            \"code\": \"400.1\",\n" +
                "            \"message\": \"First parameter is incorrect\",\n" +
                "            \"numberErrors\": 1\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/json/"))
                .willReturn(WireMock.aResponse().withBody(json).withStatus(201)));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/json/", String.class);

        BDDAssertions.then(response.getBody()).isEqualTo(json);
    }

}