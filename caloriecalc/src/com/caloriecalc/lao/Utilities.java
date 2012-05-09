package com.caloriecalc.lao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;

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
	 * Given a value in seconds returns the value converted to minutes.
	 * 
	 * @author Romina
	 * @param double seconds
	 * @return double minutes
	 */
	public static double secondsToMins(double seconds) {
		return seconds / 60.00;

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
	
	
	
	/**
	 *  Returns the MET coefficient based on the exercise type performed and its speed.
	 *  
	 *  @author Romina 
	 *  
	 *  @param  exerciseType
	 *  @param  speed
	 *  @return coefficient
	 *  
	 *  Caminata 

		Práctica moderada (5 km por hora): 0,029 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		Práctica vigorosa (7 km por hora): 0,048 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		
		Carrera/Jogging 
		
		Trote lento (8 km por hora): 0,06 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		Trote rápido (11 km por hora): 0,092 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		Carrera lenta (13 km por hora): 0,104 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		Carrera rápida (16 km por hora): 0,129 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		
		Patinaje (sobre hielo o con ruedas) 
		
		Principiante (9 km por hora): 0,032 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		Práctica moderada (12 km por hora): 0,049 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		Práctica vigorosa (17 km por hora): 0,065 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		
		Bicicleta 
		
		Velocidad baja (16 km por hora): 0,049 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 
		
		Velocidad moderada (20 km por hora): 0,071 x (su peso x 2,2) X total de minutos de práctica = calorías aproximadas quemadas 

	 */
	
	private static double returnMetCoefficient (TipoEjercicio tipo, double speed){
		switch (tipo.ordinal()){
		case 0:
			if(speed < 5)
				return 0.029;
			return 0.048;
		case 1: 
			if(speed < 8)  {return 0.06;}
			if(speed < 11) {return 0.092;}
			if(speed < 13) {return 0.104;}
			if(speed < 16) {return 0.129;}
		case 2: 
			if (speed < 9) {return 0.032;}
			if (speed < 12) {return 0.049;}
			if (speed < 17) {return 0.065;}
		case 3: 
			if (speed < 16) {return 0.049;}
			return 0.071;
		}
		
		return 0.00;
	
	}
	
	
	/**Calculate the calories burned by applying the formula:
	 *  k x (weight x 2.2) x minutes of activity
	 *  where k is a constant calculated based on exercise type and its intensity
	 *  
	 *  @author Romina
	 *  
	 *  @param peso
	 *  @param exercise type
	 *  @param speed
	 *  @param length of the workout in minutes
	 *  
	 *  @return caloriesBurned*/
	
	public static double calculateCaloriesBurned(double peso, TipoEjercicio tipo, double speed, double time){
		
		double minutes = secondsToMins(time);
		
		double coef = returnMetCoefficient(tipo, speed);
		
		return coef * (peso * 2.2) * minutes;
		
	}
	
	
	

}
