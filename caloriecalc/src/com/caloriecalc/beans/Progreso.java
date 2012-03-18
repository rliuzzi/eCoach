package com.caloriecalc.beans;

import java.util.Date;

public class Progreso{
	
	private Date id;
	private int ejercicioId;
	private Double lat;
	private Double lon;	
	
	
	public Date getId(){
		return this.id;
	}
	
	public int getEjercicioId(){
		return this.ejercicioId;
	}	
	
	public Double getLat(){
		return this.lat;
	}
	
	public Double getLon(){
		return this.lon;
	}
	
	
	public void setId(Date value){
		this.id = value;
	}
	
	public void setEjercicioId(int value){
		this.ejercicioId = value;
	}
	
	public void setLat(Double value){
		this.lat = value;
	}
	
	public void setLon(Double value){
		this.lon = value;
	}
}
