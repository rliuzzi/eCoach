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
	private Double speed;

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
	 * @param speed
	 */
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
}
