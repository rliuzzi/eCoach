package com.caloriecalc.beans;



/**
 * @author Romina
 * 
 */
public class Progreso {

	private Double id;
	private int ejercicioId;
	private Double latitude;
	private Double longitude;
	private Double altitude;
	private Double speed;
	private Double distance;
	private Double calories;
	private int locId;

	/**
	 * @return Double id
	 */
	public Double getId() {
		return this.id;
	}

	/**
	 * @return int ejercicioId
	 */
	public int getEjercicioId() {
		return this.ejercicioId;
	}

	/**
	 * @return Double latitude
	 */
	public Double getLatitude() {
		return this.latitude;
	}

	/**
	 * @return Double longitude
	 */
	public Double getLongitude() {
		return this.longitude;
	}

	/**
	 * @return Double altitude
	 */
	public Double getAltitude() {
		return this.altitude;
	}
	
	/**
	 * @return Double speed
	 */
	public Double getSpeed() {
		return this.speed;
	}

	/**
	 * @param progressId
	 */
	public void setId(Double progressId) {
		this.id = progressId;
	}

	/**
	 * @param ejercicioId
	 */
	public void setEjercicioId(int ejercicioId) {
		this.ejercicioId = ejercicioId;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * @param altitude
	 */
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	/**
	 * @param speed
	 */
	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	/**
	 * @return the locId
	 */
	public int getLocId() {
		return locId;
	}

	/**
	 * @param locId the locId to set
	 */
	public void setLocId(int locId) {
		this.locId = locId;
	}

	/**
	 * @return the calories
	 */
	public Double getCalories() {
		return calories;
	}

	/**
	 * @param calories the calories to set
	 */
	public void setCalories(Double calories) {
		this.calories = calories;
	}
}
