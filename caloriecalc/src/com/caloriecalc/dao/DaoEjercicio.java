package com.caloriecalc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.caloriecalc.beans.Ejercicio;

import android.content.Context;
import android.database.Cursor;


public class DaoEjercicio extends DataBaseHelper {
	
	
	
	public DaoEjercicio(Context context) throws IOException{
		super(context);		
	}

	
	//create a new exercise
	public int crearEjercicio(Date fechaInicio, int tipoEjercicio, int peso) {
		
		this.openDataBase();
		String sql = "INSERT INTO Ejercicio (HoraInicio, TipoId, Peso) VALUES (" 
			+ fechaInicio.getTime() + ", "
			+ tipoEjercicio + ", "
			+ peso + ")";
		myDataBase.execSQL(sql);
		Cursor c = myDataBase.rawQuery("SELECT last_insert_rowid()", null);
		c.moveToNext();
		int result =  c.getInt(0);
		this.close();
		return result;
		//returns the first database field (ejercicioId)
	}
	

	//get a single exercise
	public Ejercicio getEjercicio(int idEjercicio) {
		this.openDataBase();
		String sql = "SELECT * FROM Ejercicio WHERE _id = " + idEjercicio; 
		Cursor c = myDataBase.rawQuery(sql, null);
		Ejercicio ej = null;
		if (c.moveToNext()) {
			ej = new Ejercicio();
			ej.setId(c.getInt(0));
			ej.setFechaInicio(new Date(c.getLong(1)));
			ej.setTipoEjercicio(c.getInt(2));
			ej.setPeso(c.getInt(3));
			ej.setDistancia(c.getInt(4));
			ej.setCalorias(c.getInt(5));
			ej.setFechaFin(new Date(c.getLong(6)));
			
		}
		this.close();
		return ej;
	}
	
	//get list of exercises
	public List<Ejercicio> getEjercicios() {
		this.openDataBase();
		String sql = "SELECT * FROM EjercicioProgreso";
		Cursor c = myDataBase.rawQuery(sql, null);

		List<Ejercicio> list = new ArrayList<Ejercicio>();

		while (c.moveToNext()) {
			Ejercicio ej = new Ejercicio();
			ej.setId(c.getInt(0));
			ej.setFechaInicio(new Date(c.getLong(1)));
			ej.setFechaFin(new Date(c.getLong(2)));
			list.add(ej);
		}

		this.close();
		return list;

	}
	
	
	//update an exercise with the input values
	public void actualizarEjercicio(int ejercicioId, Date horaFin, double totalDistance, double totalCalories) {
		
		this.openDataBase();
		String sql = "UPDATE Ejercicio SET " 
			+ "Distancia = " + totalDistance + ", " 
			+ "Calorias =  " + totalCalories + ", "
			+ "HoraFin = " + horaFin.getTime()
			+ " WHERE _id = " + ejercicioId;
		
		myDataBase.execSQL(sql);	
		this.close();
	}
	


	
}
