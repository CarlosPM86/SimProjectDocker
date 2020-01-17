package com.consumer.rabbit.consumerRabbit;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;

import com.consumer.rabbit.dto.CarTrackingInfo;
import com.consumer.rabbit.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@EnableBinding(Sink.class)
@ComponentScan(basePackages = {"com.consumer"})
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class ConsumerRabbitApplication {
	
	@Autowired
	private CarService carService;
	
	static Logger logger = LoggerFactory.getLogger(ConsumerRabbitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRabbitApplication.class, args);
	}
	
	
	@StreamListener(Sink.INPUT)
	@HystrixCommand(commandKey = "get-data-rabbit",fallbackMethod = "errorGetInfo")
	public void processCarInfo(String jsonCarInfo) {
		logger.info("processCarInfo - Received: " , jsonCarInfo);
		
		CarTrackingInfo carInfo = castStringJSONTOObject(jsonCarInfo, CarTrackingInfo.class);
		carService.saveCar(carInfo);
	}
	
	public void errorGetInfo (String jsonCarInfo) {
		//Guardar en mongo los datos de fallo de lectura y enviar notificacion
		logger.info("No se ha podido almacenar la informacion");
	}

	private  static <E> E castStringJSONTOObject(String jsonString, Class<?> clase) {
        E result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(jsonString, mapper.getTypeFactory().constructType(clase));
        } catch (IOException e) {
            
        }
        return result;
    }

}
