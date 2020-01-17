package com.cpm.m2m.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cpm.m2m.dto.CarTrackingInfo;
import com.cpm.m2m.util.MockCars;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ManagementService implements MockCars {

	@Autowired
    RestTemplate restTemplate;
	static Logger logger = LoggerFactory.getLogger(ManagementService.class);
	
	@HystrixCommand(commandKey = "order-Sim",fallbackMethod = "statusDefault")
	public String sendInfoSim (int gates , int cars) {
		logger.info("Entra al servicio sendInfoSim ");
		String resp = "Informacion enviada con exito";
		ManagementService man = new ManagementService();
		List<CarTrackingInfo> carsInfo = man.getCarList(gates, cars);
		for (CarTrackingInfo simInfo :carsInfo ) {
			logger.info("Objeto entrada {} ",simInfo);
		HttpEntity<CarTrackingInfo> entity = new HttpEntity<>(simInfo);
		String response = "";
		try {
			//"http://producer-to-rabbit-simInformation/order"
		  response = restTemplate.exchange("http://zuul-proxy/order",
                 HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {}).getBody();
		} catch (Exception e) {
			logger.error("Error al llamar al servicio producer-to-rabbit-simInformation ",e);
			resp = "Error al llamar producer-to-rabbit-simInformation ";
		}

		 logger.info("Response Received as {}", response);
		}
		return resp ;
	}
	
	
	public String statusDefault (int gates , int cars) {
		 logger.info("No seha podido comunicar con el servicio de sim");
		return "No se ha podido almacenar la informacion";
	}
	
	
	 @Bean
	 @LoadBalanced
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

	
}
