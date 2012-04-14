package com.caloriecalc.lao;

import java.util.Date;

public class Utilils {
	

	/**
	 * Given a value in meters returns the value converted to Km.
	 * 
	 * @author Romina
	 * @param double meters
	 * @return double km
	 */
	public double metersToKm(double meters)
	{
		return meters / 1000;
	}

	/**
	 * Given a value in Km returns the value converted to meters.
	 * 
	 * @author Romina
	 * @param double km
	 * @return double meters
	 */
	public double kmToMeters (double km)
	{
		return km * 1000;
	}
	
	/**
	 * Given a value in seconds returns the value converted to hours.
	 * 
	 * @author Romina
	 * @param double seconds
	 * @return double hours
	 */
	public double secondsToHours (double seconds)
	{
		return seconds / (60 * 60);
		
	}


	
}
