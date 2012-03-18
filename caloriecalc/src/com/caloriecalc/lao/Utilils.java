package com.caloriecalc.lao;

import java.util.Date;

public class Utilils {
	

	/**
	 * Convierte metros a pies.
	 * 
	 * @param int metros
	 * @return int
	 */
	public static int MetrosPies(int metros)
	{
		return (int) Math.round(metros * 3.2808399);
	}

	/**
	 * Convierte pies a metros.
	 * 
	 * @param int pies
	 * @return int
	 */
	public static int PiesMetros(int pies)
	{
		return (int) Math.round(pies / 3.2808399);
	}

	/**
	 * Convierte de metros a pies y redondea.
	 * 
	 * @param double metros
	 * @return int
	 */
	public static int MetrosPies(double metros)
	{
		return MetrosPies((int) metros);
	}

	/**
	 * Convierte de pies a metros y redondea.
	 * 
	 * @param double pies
	 * @return
	 */
	public static int PiesMetros(double pies)
	{
		return PiesMetros((int) pies);
	}
	
	
	/**
	 * Calcula edad a partir de fecha de nacimiento.
	 * 
	 * @param date dob
	 * @return int
	 */
	public static int dobEdad(Date dob)
	{
		//calcular edad
		return 0;
	}
	
	/**
	 * Devuelve el valor de la constante MET en funcion de la velocidad y el tipo de actividad.
	 * 
	 * @param int tipoActividad	Tipo de actividad realizada
	 * @param int kmH			Velocidad en km/h
	 * @return int MET
	 */
	public int determinarMet(int tipoActividad, int kmH){
		//TO DO
		return 0;
	}


	
}
