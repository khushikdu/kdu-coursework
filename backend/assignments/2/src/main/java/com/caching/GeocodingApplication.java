package com.caching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = "com.caching")
public class GeocodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeocodingApplication.class, args);
	}
}
