package com.cpm.sim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SimService {
	@Autowired
	RestTemplate restTemplate;
	
	
	public String getSimDetails (String idSim) {
		return "ACTIVO";
		
	}
	
	
	
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
