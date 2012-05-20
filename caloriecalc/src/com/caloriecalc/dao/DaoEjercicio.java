package com.caloriecalc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;
import com.caloriecalc.beans.Serie;

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
			Double peso) {

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
			ej.setPeso(c.getDouble(3));
			ej.setDistancia(c.getDouble(4));
			ej.setCalorias(c.getDouble(5));
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
	public void actualizarEjercicio(int ejercicioId, double horaFin, double totalDistance, double totalCalories) {
		
		this.openDataBase();
		
		String sql = "UPDATE Ejercicio SET " + "Distancia = " + totalDistance
				+ ", " + "Calorias =  " + totalCalories + ", " + "HoraFin = "
				+ horaFin + " WHERE _id = " + ejercicioId;

		
		myDataBase.execSQL(sql);
		
		this.close();
	}
	
	
	
	
	/**
	 * Deletes the exercise identified by the ejercicioId provided.
	 * 
	 * @param ejercicioId
	 */
	public void deleteEjercicio (int ejercicioId){
		
		this.openDataBase();
		
		String sql = "DELETE FROM Ejercicio WHERE _id = " + ejercicioId;
		
		myDataBase.execSQL(sql);
		
		this.close();
		
	}
	
	
	/**
	 * Retrieves the full list of stored exercises
	 * 
	 * @author Romina
	 * @return List of exercises
	 */
	public ArrayList<Ejercicio> getExerciseList() {
		
		this.openDataBase();
		String sql = "SELECT * FROM Ejercicio";
		
		Cursor c = myDataBase.rawQuery(sql, null);

		ArrayList<Ejercicio> list = new ArrayList<Ejercicio>();

		while (c.moveToNext()) {
			Ejercicio ej = new Ejercicio();
			ej.setId(c.getInt(0));
			ej.setFechaInicio(new Date(c.getLong(1)));
			ej.setTipoEjercicio(TipoEjercicio.values()[c.getInt(2)]);
			ej.setDistancia(c.getDouble(4));
			ej.setCalorias(c.getDouble(5));
			list.add(ej);
			
		}

		this.close();
		
		return list;

	}
	
	
	/**
	 * Retrieves the amount of exercises of each type performed in ascending order.
	 * 
	 * @author Romina
	 * @return list with the amount of occurences of each exercise type.
	 */
	public ArrayList<Integer> getCountExercisesById(){
		this.openDataBase();
		
		String sql = "SELECT COUNT(*)  FROM Ejercicio GROUP BY TipoId ORDER BY TipoId ASC";
		
		Cursor c = myDataBase.rawQuery(sql, null);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while (c.moveToNext()){
			Integer i = new Integer(0);
			i = (c.getInt(2));
			list.add(i);
		}
		
		this.close();
		
		return list;
	}
	
	
	/**
	 * Retrieves the amount of exercises of the typeId provided.
	 * 
	 * @author Romina
	 * 
	 * @param  exercise typeId
	 * @return the amount of occurrences of the exercise typeId provided.
	 */
	public Integer getCountExercises(int typeId){
		
		this.openDataBase();
		
		String sql = "SELECT * FROM Ejercicio WHERE tipoId = " + typeId; 
		//SELECT COUNT(*) FROM Ejercicio WHERE tipoId = 1;
		
		Cursor c = myDataBase.rawQuery(sql, null);
		
		Integer i = c.getCount();
		
		this.close();
		
		return i;
	}
	
	
	/**
	 * Retrieves the amount of calories spent by date for all workouts saved
	 * 
	 * @author Romina
	 * 
	 * @return A list with date, calories values
	 */
	
	public List<Serie> getCaloriesByDate (){
		
		this.openDataBase();
		
		String sql = "SELECT horaInicio, Calorias FROM Ejercicio";
		
		Cursor c = myDataBase.rawQuery(sql, null);
		
		List<Serie> serie = new ArrayList<Serie>();
		
		while (c.moveToNext()){
			Serie s = new Serie();
			
			s.setX(new Date(c.getLong(0)));
			s.setY(c.getDouble(1));
			serie.add(s);
			
			
		}
		
		this.close();
		
		return serie;
		
		
		
	}
	
	/**
	 * Retrieves the weight by date for all workouts saved
	 * 
	 * @author Romina
	 * 
	 * @return A list with date, weight values
	 */
	
	public List<Serie> getWeightByDate (){
		
		this.openDataBase();
		
		String sql = "SELECT horaInicio, Peso FROM Ejercicio";
		
		Cursor c = myDataBase.rawQuery(sql, null);
		
		List<Serie> serie = new ArrayList<Serie>();
		
		while (c.moveToNext()){
			Serie s = new Serie();
			
			s.setX(new Date(c.getLong(0)));
			s.setY(c.getDouble(1));
			serie.add(s);
			
			
		}
		
		this.close();
		
		return serie;
	}
	
	/**
	 * Gets the maximum value of a column specified.
	 * 
	 * @author Romina
	 * @param columnName to evaluate (i.e: Calories, Distance, Speed)
	 * @return the result of the operation
	 */
	public double getMaxValueInColumn(String columnName) {

		this.openDataBase();

		String sql = "SELECT MAX (" + columnName +") FROM Ejercicio";

		Cursor c = myDataBase.rawQuery(sql, null);
		
		c.moveToFirst();
		
		double result = c.getDouble(0);
		
		this.close();
		
		return result;

	}
	

}
