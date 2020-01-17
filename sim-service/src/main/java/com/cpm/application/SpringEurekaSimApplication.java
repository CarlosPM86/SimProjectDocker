package com.cpm.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

import com.cpm.sim.service.SimOrderSource;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.cpm"})

public class SpringEurekaSimApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaSimApplication.class, args);
	}
}
