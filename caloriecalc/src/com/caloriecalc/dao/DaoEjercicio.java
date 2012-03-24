package com.caloriecalc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;

import android.content.Context;
import android.database.Cursor;

/**
 * @author Romina
 * 
 */
public class DaoEjercicio extends DataBaseHelper {

	/**
	 * @param context
	 * @throws IOException
	 */
	public DaoEjercicio(Context context) throws IOException {
		super(context);
	}

	
	/**
	 * @param fechaInicio
	 * @param tipoEjercicio
	 * @param peso
	 * @return
	 */
	public Ejercicio crearEjercicio(Date fechaInicio, TipoEjercicio tipoEjercicio,
			int peso) {

		Ejercicio ejercicio = new Ejercicio();

		this.openDataBase();
		String sql = "INSERT INTO Ejercicio (HoraInicio, TipoId, Peso) VALUES ("
				+ fechaInicio.getTime()
				+ ", "
				+ tipoEjercicio.ordinal()
				+ ", "
				+ peso
				+ ")";
		myDataBase.execSQL(sql);
		Cursor c = myDataBase.rawQuery("SELECT last_insert_rowid()", null);
		c.moveToNext();

		ejercicio.setId(c.getInt(0));
		ejercicio.setFechaInicio(fechaInicio);
		ejercicio.setTipoEjercicio(tipoEjercicio);
		ejercicio.setPeso(peso);

		this.close();

		return ejercicio;
		
	}


	/**
	 * @param idEjercicio
	 * @return
	 */
	public Ejercicio getEjercicio(int idEjercicio) {
		this.openDataBase();
		String sql = "SELECT * FROM Ejercicio WHERE _id = " + idEjercicio;
		Cursor c = myDataBase.rawQuery(sql, null);
		Ejercicio ej = null;
		if (c.moveToNext()) {
			ej = new Ejercicio();
			ej.setId(c.getInt(0));
			ej.setFechaInicio(new Date(c.getLong(1)));
			ej.setTipoEjercicio(TipoEjercicio.values()[c.getInt(2)]);
			ej.setPeso(c.getInt(3));
			ej.setDistancia(c.getInt(4));
			ej.setCalorias(c.getInt(5));
			ej.setFechaFin(new Date(c.getLong(6)));

		}
		this.close();
		return ej;
	}

	
	/**
	 * @return
	 */
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

	
	/**
	 * @param ejercicioId
	 * @param horaFin
	 * @param totalDistance
	 * @param totalCalories
	 */
	public void actualizarEjercicio(int ejercicioId, Date horaFin,
			double totalDistance, double totalCalories) {

		this.openDataBase();
		String sql = "UPDATE Ejercicio SET " + "Distancia = " + totalDistance
				+ ", " + "Calorias =  " + totalCalories + ", " + "HoraFin = "
				+ horaFin.getTime() + " WHERE _id = " + ejercicioId;

		myDataBase.execSQL(sql);
		this.close();
	}

}
