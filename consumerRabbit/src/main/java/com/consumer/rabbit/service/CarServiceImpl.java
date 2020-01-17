package com.consumer.rabbit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consumer.rabbit.dto.CarTrackingInfo;
import com.consumer.rabbit.repository.CarRepository;
import com.consumer.rabbit.repository.CarRepositoryImpl;
@Service("carService")
@Transactional
public class CarServiceImpl implements CarService {
@Autowired
private CarRepository carRepository;

static Logger logger = LoggerFactory.getLogger(CarRepositoryImpl.class);
	@Override
	public CarTrackingInfo saveCar(CarTrackingInfo car) {
		// TODO Auto-generated method stub
		logger.info("Se guarda la siguiente informacion en Mongo {}",car);
		return carRepository.saveCar(car);
	}

}
