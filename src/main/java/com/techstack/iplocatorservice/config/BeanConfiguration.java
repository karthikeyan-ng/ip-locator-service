package com.techstack.iplocatorservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This configuration class creates various Beans at application startup
 *
 * @author Karthikeyan
 */
@Configuration
public class BeanConfiguration {

    /**
     * Bean to create a RestTemplate at startup
     *
     * @return RestTemplate preconfigured Rest Template reference
     */
    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
