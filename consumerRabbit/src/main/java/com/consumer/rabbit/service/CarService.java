package com.consumer.rabbit.service;

import org.springframework.stereotype.Service;

import com.consumer.rabbit.dto.CarTrackingInfo;

@Service
public interface CarService {
	
	public CarTrackingInfo saveCar(CarTrackingInfo car);
}
