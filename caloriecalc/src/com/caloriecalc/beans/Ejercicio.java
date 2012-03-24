package com.caloriecalc.beans;

import java.util.Date;

public class Ejercicio {
	private int id;
	private Date fechaInicio;
	private Date fechaFin;
	private TipoEjercicio tipoEjercicio;
	private int peso;
	private int distancia;
	private int calorias;
	
	
	public enum TipoEjercicio {
		CAMINAR(0),
		CORRER(1), 
		PATINAR(2), 
		BICICLETA(3);
		
		private final int tipo;
		
		TipoEjercicio(int tipo){
			this.tipo = tipo;
		}

		/**
		 * @return the tipo
		 */
		public int getTipo() {
			return tipo;
		}
		
		

	};
	
	public Ejercicio(){
		
	}
	
	public Ejercicio(Date fechaInicio, TipoEjercicio tipoEjercicio){
		this.fechaInicio = fechaInicio;
		this.tipoEjercicio = tipoEjercicio;
	}
	
	
	public Ejercicio(Date fechaInicio, Date fechaFin, TipoEjercicio tipoEjercicio){
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoEjercicio = tipoEjercicio;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}


	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}


	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	/**
	 * @return the tipoEjercicio
	 */
	public TipoEjercicio getTipoEjercicio() {
		return tipoEjercicio;
	}


	/**
	 * @param tipoEjercicio the tipoEjercicio to set
	 */
	public void setTipoEjercicio(TipoEjercicio tipoEjercicio) {
		this.tipoEjercicio = tipoEjercicio;
	}


	/**
	 * @return the peso
	 */
	public int getPeso() {
		return peso;
	}


	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}


	/**
	 * @return the dist
	 */
	public int getDistancia() {
		return distancia;
	}


	/**
	 * @param dist the dist to set
	 */
	public void setDistancia(int dist) {
		this.distancia = dist;
	}


	/**
	 * @return the calorias
	 */
	public int getCalorias() {
		return calorias;
	}


	/**
	 * @param calorias the calorias to set
	 */
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
}
