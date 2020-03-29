package com.techstack.iplocatorservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IpLocatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpLocatorServiceApplication.class, args);
	}

}

@Order(1)
@Slf4j
@Component
class ToTestIpVigilanteApiIsUpAndRunning implements ApplicationRunner {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void run(ApplicationArguments args) {

		try {
			String response = restTemplate.getForObject("https://ipvigilante.com/json/8.8.8.8", String.class);
			if (response.contains("country_name")) {
				log.info("Hi, you got the response from IP Vigilante API. Cool! You can run this application");
			}
		} catch (Exception e) {
			log.error("******* The IP Vigilante API is NOT UP and RUNNING ********* Hence, you may get the different " +
					"result for your input.");
			log.error(e.getMessage());
			log.error("******* External service responding with this error: 502 Bad Gateway and nginx/1.16.0");
			log.error("******* Try to hit this https://ipvigilante.com/json/8.8.8.8 URL on browser and see ");
		}
	}
}