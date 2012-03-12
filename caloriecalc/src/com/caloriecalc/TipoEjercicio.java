package com.caloriecalc;

public enum TipoEjercicio {
	
	ANDAR(0),
	CORRER(1),
	PATINAR(2),
	BICICLETA(3);
	
	
	private int id;

	private TipoEjercicio(int id){
			this.id=id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int value){
		this.id=value;
	}

}

