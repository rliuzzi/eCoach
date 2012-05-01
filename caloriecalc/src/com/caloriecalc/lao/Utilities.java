package com.caloriecalc.lao;

import java.lang.Math;

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
		double i = value / 1000;
		return i;
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
		int i = value / 100;
		return i;
	}

}
