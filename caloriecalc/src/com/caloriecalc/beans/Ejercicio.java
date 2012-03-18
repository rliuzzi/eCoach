package com.caloriecalc.beans;

import java.util.Date;

public class Ejercicio {
	private int id;
	private Date fechaInicio;
	private Date fechaFin;
	private int tipoEjercicio;
	private int peso;
	private int dist;
	private int calorias;
	

	public Ejercicio() {
	}
	
	
	public Ejercicio(Date fechaInicio, int tipoEjercicio){
		this.fechaInicio = fechaInicio;
		this.tipoEjercicio = tipoEjercicio;
	}
	
	
	public Ejercicio(Date fechaInicio, Date fechaFin, int tipoEjercicio){
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoEjercicio = tipoEjercicio;
	}
	



	public int getTipoEjercicio(){
		return this.tipoEjercicio;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Date getFechaInicio() {
		return this.fechaInicio;
	}
	
	public Date getFechaFin() {
		return this.fechaFin;
	}
	
	
	public int getPeso(){
		return this.peso;
	}
	
	public int getDistancia (){
		return this.dist;
	}
	
	public int getCalorias (){
		return this.calorias;
	}
	
	public void setId(int value) {
		this.id = value;
	}
	
	public void setFechaInicio(Date value) {
		this.fechaInicio = value;
	}
	
	
	public void setFechaFin(Date value) {
		this.fechaFin = value;
	}
	
	public void setTipoEjercicio(int value){
		this.tipoEjercicio = value;
	}
	
	public void setPeso(int value){
		this.peso = value;
	}
	
	public void setDistancia (int value){
		this.dist = value;
	}
	
	public void setCalorias (int value){
		this.calorias = value;
	}
	
}
