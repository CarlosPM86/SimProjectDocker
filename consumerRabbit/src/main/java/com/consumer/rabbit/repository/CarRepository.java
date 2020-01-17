package com.consumer.rabbit.repository;

import com.consumer.rabbit.dto.CarTrackingInfo;

public interface CarRepository {
	
	public CarTrackingInfo saveCar(CarTrackingInfo car);
}
