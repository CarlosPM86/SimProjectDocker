package com.cpm.m2m.dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


/**
 * The Class CarTrackingInfo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarTrackingInfo implements Serializable {

	/** The logger. */
	private static Logger logger = LoggerFactory
			.getLogger(CarTrackingInfo.class);
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id gate. */
	private int trackingPoint;

	/** The registration. */
	private String vrp;

	/** The state. */
	private String state;

	/** The fecha. */
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date trackingDate;

	/**
	 * Instantiates a new car tracking info.
	 */
	public CarTrackingInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new car tracking.
	 *
	 * @param trackingPoint the tracking point
	 * @param vrp            the vrp
	 * @param state            the state
	 * @param trackingDate the tracking date
	 */
	public CarTrackingInfo(int trackingPoint, String vrp, String state,
			Date trackingDate) {
		this.trackingPoint = trackingPoint;
		this.vrp = vrp;
		this.state = state;
		this.trackingDate = trackingDate;
	}

	/**
	 * Instantiates a new car tracking.
	 *
	 * @param json
	 *            the json
	 */
	public CarTrackingInfo(String json) {

		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		CarTrackingInfo car = null;
		try {
			car = mapper.readValue(json, CarTrackingInfo.class);
			this.trackingPoint = car.getTrackingPoint();
			this.vrp = car.getVrp();
			this.state = car.getState();
			this.trackingDate = car.getTrackingDate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in deserialization", e);

			this.trackingPoint = 1;
			this.vrp = "PLATE1 - ERROR";
			this.state = "ERROR in DESERIALIZATION";
			this.trackingDate = new Date();
		}

	}

	/**
	 * Builds the.
	 *
	 * @param json the json
	 * @return the car tracking info
	 */
	public static CarTrackingInfo build(String json) {

		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		try {
			return mapper.readValue(json, CarTrackingInfo.class);
		} catch (Exception e) {
			logger.error("Error in deserialization", e);
			return null;
		}

	}

	/**
	 * Gets the registration.
	 *
	 * @return the registration
	 */
	public String getVrp() {
		return vrp;
	}

	/**
	 * Sets the registration.
	 *
	 * @param registration
	 *            the new registration
	 */
	public void setVrp(String registration) {
		this.vrp = registration;
	}

	/**
	 * Gets the tracking point.
	 *
	 * @return the tracking point
	 */
	public int getTrackingPoint() {
		return trackingPoint;
	}

	/**
	 * Sets the tracking point.
	 *
	 * @param trackingPoint the new tracking point
	 */
	public void setTrackingPoint(int trackingPoint) {
		this.trackingPoint = trackingPoint;
	}

	/**
	 * Gets the tracking date.
	 *
	 * @return the tracking date
	 */
	public Date getTrackingDate() {
		return trackingDate;
	}

	/**
	 * Sets the tracking date.
	 *
	 * @param trackingDate the new tracking date
	 */
	public void setTrackingDate(Date trackingDate) {
		this.trackingDate = trackingDate;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param estado
	 *            the new state
	 */
	public void setState(String estado) {
		this.state = estado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

		try {
			return ow.writeValueAsString(this);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
