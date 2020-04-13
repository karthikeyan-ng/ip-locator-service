package com.techstack.iplocatorservice;

import com.techstack.iplocatorservice.controller.LocationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IpLocatorServiceApplicationTests {

	@Autowired
	LocationController locationController;

	@Test
	void contextLoads() {

		assertThat(locationController).isNotNull();
	}

}
