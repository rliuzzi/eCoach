package com.caloriecalc.beans;

import com.google.android.maps.GeoPoint;




/**
 * Bean to define an item to be displayed in a map.
 * @author Romina 
 *
 */
public class MapItem
{	
	private GeoPoint geoPoint;
	//private int latitude;
	//private int longitude;
	
	private double speed;
	private double distance;
	private double calories;
	

	
	/**
	 * @return the geoPoint
	 */
	public GeoPoint getGeoPoint() {
		return geoPoint;
	}
	/**
	 * @param geoPoint the geoPoint to set
	 */
	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	/**
	 * @return the calories
	 */
	public double getCalories() {
		return calories;
	}
	/**
	 * @param calories the calories to set
	 */
	public void setCalories(double calories) {
		this.calories = calories;
	}
	
	
	/**
	 * Returns the relative speed, distance and calories burned as a String.
	 * @return toast
	 */
	public String getToast(){
		
		String s = Math.round(distance) + " mts \n" + Math.round(speed) + " Km/h \n" + calories + " Kcal \n" ; 
		
		return s;
		
	}


}
