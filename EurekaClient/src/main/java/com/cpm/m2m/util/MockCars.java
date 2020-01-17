package com.cpm.m2m.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cpm.m2m.dto.CarTrackingInfo;

/**
 * The Interface MockCards.
 */
public interface MockCars {

	static final Logger logger = LoggerFactory.getLogger(MockCars.class);

	/**
	 * Gets the car list.
	 *
	 * @param gates
	 *            the gates
	 * @param cars
	 *            the cars
	 * @return the car list
	 */
	default List<CarTrackingInfo> getCarList(int gates, int cars) {
		List<CarTrackingInfo> informacion = new ArrayList<>();
		// Recorremos los puertas
		for (int i = 1; i <= gates; i++) {
			// por cada una de ellas le asignamos los coches
			for (int i1 = 1; i1 < cars; i1++) {
				CarTrackingInfo track = new CarTrackingInfo();
				track.setState("ENTERED-HIGHWAY");
				track.setTrackingDate(getDate(i1));
				track.setTrackingPoint(i);
				int numMatricula = getNumeroRandom();
				String numeroM = String.valueOf(numMatricula);
				track.setVrp(numeroM + "FHT");
				informacion.add(track);
			}
		}

		return informacion;

	}

	default Date getDate(int i1) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		Date tempDate = cal.getTime();
		// Le cambiamos la hora y minutos
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + i1);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) - 5);
		tempDate = cal.getTime();
		return tempDate;
	}

	/**
	 * Adds the car list.
	 *
	 * @param listado
	 *            the listado
	 * @param id
	 *            the id
	 * @return the list
	 */
	default List<CarTrackingInfo> addCarList(CarTrackingInfo listado, String id) {
		List<CarTrackingInfo> listadoBase = getCarList(3, 10);

		listadoBase.add(listado);
		return listadoBase;

	}

	/**
	 * Gets the numero random.
	 *
	 * @return the numero random
	 */
	default int getNumeroRandom() {
		return ThreadLocalRandom.current().nextInt(1, 9000);
	}

}
