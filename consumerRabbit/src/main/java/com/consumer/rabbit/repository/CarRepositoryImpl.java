package com.consumer.rabbit.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.consumer.rabbit.dto.CarTrackingInfo;
@Repository
public class CarRepositoryImpl implements CarRepository {
	@Autowired
	private final MongoOperations mongoOperations;
	
	static Logger logger = LoggerFactory.getLogger(CarRepositoryImpl.class);
	
	@Override
	public CarTrackingInfo saveCar(CarTrackingInfo car) {
		logger.info("Se procede a guardar en mongo el siguiente objeto {}",car);
		 this.mongoOperations.save(car);

	        return findOne(car.getVrp()).get();
	}
	
	@Autowired
	public CarRepositoryImpl(MongoOperations mongoOperations) {
	    this.mongoOperations = mongoOperations;
	}
	
	
	public Optional<CarTrackingInfo> findOne(String vrp) {

		CarTrackingInfo d = this.mongoOperations.findOne(new Query(Criteria.where("vrp").is(vrp)), CarTrackingInfo.class);

        Optional<CarTrackingInfo> car = Optional.ofNullable(d);
        logger.info("Coleccion de mongo obtenida" ,d);
        return car;

    }

}
