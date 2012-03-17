package com.caloriecalc.beans;



/**
 * Representa un punto GPS, contiene latitud, longitud, altitud y fecha
 * @author 
 *
 */
public class PuntoGps
{
	private String latitud;
	private String longitud;
	private String altitud;
	private String fecha;
	

	public String getLatitud(){
		return latitud;
	}

	public String getLongitud(){
		return longitud;
	}
	
	public String getAltitud(){
		return altitud;
	}

	public String getFecha(){
		return fecha;
	}

	public void setLatitud (String value){
		latitud = value;
	}

	public void setLongitud(String value){
		longitud = value;
	}
	
	public void setAltitud(String value){
		longitud = value;
	}

	public void setFecha(String value){
		fecha = value;
	}



}
