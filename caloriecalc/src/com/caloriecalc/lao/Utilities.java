package com.caloriecalc.lao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utilities {

	/**
	 * Given a value in meters returns the value converted to Km.
	 * 
	 * @author Romina
	 * @param double meters
	 * @return double km
	 */
	public static double metersToKm(double meters) {
		return meters / 1000;
	}

	/**
	 * Given a value in Km returns the value converted to meters.
	 * 
	 * @author Romina
	 * @param double km
	 * @return double meters
	 */
	public static double kmToMeters(double km) {
		return km * 1000;
	}

	/**
	 * Given a value in seconds returns the value converted to hours.
	 * 
	 * @author Romina
	 * @param double seconds
	 * @return double hours
	 */
	public static double secondsToHours(double seconds) {
		return seconds / (60 * 60);

	}

	/**
	 * Given a value in Kg returns the value converted to grams.
	 * 
	 * @author Romina
	 * @param float value
	 * @return int i
	 */
	public static int kgToGrams(float value) {

		int i = Math.round(value * 1000);
		return i;
	}

	/**
	 * Given a value in grams returns the value converted to Kgs.
	 * 
	 * @author Romina
	 * @param int value
	 * @return double i
	 */
	public static double gramsToKg(int value) {
		//NOTE: When dividing integer types, the result is an integer type. 
		//This means it returns the whole number part of the result; it does not perform any rounding
		return value/1000.00;
	}

	/**
	 * Given a value in meters returns the value converted to cms.
	 * 
	 * @author Romina
	 * @param float value
	 * @return int i
	 */

	public static int metersToCm(float value) {
		int i = Math.round(value * 100);
		return i;
	}

	/**
	 * Given a value in cms returns the value converted to meters.
	 * 
	 * @author Romina
	 * @param int value
	 * @return double i
	 */

	public static double cmToMeters(int value) {
		return value/100.00;
	}
	
	/**
	 * Calculates the age given a Date of Birth
	 * 
	 * @author Romina
	 * @param date of birth
	 * @return the age
	 */
	public static int calculateAge(Date dob) {
		
		Date now = new Date();
		int age = now.getYear() - dob.getYear(); 
		return age;
		
	}

	
	/**
	 * Parses a date out of String in a predefined format.
	 * 
	 * @author Romina
	 * @param dob
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String dob) throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if (dob != null){
			Date date = formatter.parse(dob);
			return date;
		}
		
		return null;
	}
	
	

}
