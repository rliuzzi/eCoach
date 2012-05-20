package com.caloriecalc.lao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;
import com.caloriecalc.beans.Serie;
import com.caloriecalc.dao.DaoEjercicio;


/**
 * @author Romina
 *
 */
public class LaoEjercicio {
	
	private DaoEjercicio daoEjercicio;
	
	
	/**
	 * @param context
	 */
	public LaoEjercicio(Context context){
		try {
			daoEjercicio = new DaoEjercicio(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param tipoEjercicio
	 * @param peso
	 * @return ejercicio
	 */
	public Ejercicio crearEjercicio(TipoEjercicio tipoEjercicio, Double peso){
		
		Date fechaInicio = Calendar.getInstance().getTime();
		return daoEjercicio.crearEjercicio(fechaInicio, tipoEjercicio, peso);
	}
	
	/**
	 * Retrieves the exercise identified by the exercise id provided.
	 * 
	 * @author Romina
	 * 
	 * @param ejercicioId
	 * @return ejercicio
	 */
	public Ejercicio consultarEjercicio(int ejercicioId){
		
		return daoEjercicio.getEjercicio(ejercicioId);
		
	}
	
	/**
	 * Retrieves the full list of stored exercises
	 * 
	 * @author Romina
	 * 
	 * @return List of exercises
	 */
	public ArrayList<Ejercicio> getExercises (){
		
		return daoEjercicio.getExerciseList();
		
	}
	
	
	/**
	 * Retrieves the amount of exercises of each type performed in ascending order.
	 * 
	 * @author Romina
	 * 
	 * @return list with the amount of occurences of each exercise type.
	 */
	public ArrayList<Integer> getCountExercisesById (){
		
		return daoEjercicio.getCountExercisesById();
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
		
		return daoEjercicio.getCountExercises(typeId);
		
	}
	
	
	
	/**
	 * Deletes the exercise identified by the ejercicioId provided.
	 * 
	 * @param ejercicioId
	 */
	public void deleteEjercicio(int ejercicioId){
		
		daoEjercicio.deleteEjercicio(ejercicioId);
	
	}
	
	/**
	 * Retrieves the maximum value for a column queried.
	 * 
	 * @author Romina
	 * 
	 * @param  the name of the column to query 
	 * @return the max value
	 */
	public Double getMaxValueInColumn(String columnName){
		
		return daoEjercicio.getMaxValueInColumn(columnName);
		
	}
	
	
	/**
	 * Retrieves the amount of calories spent by date for all workouts saved
	 * 
	 * @author Romina
	 * 
	 * @return A list with date, calories values
	 */
	
	public List<Serie> getCaloriesByDate(){
		
		return daoEjercicio.getCaloriesByDate();
		
	}
	
	/**
	 * Retrieves the weight by date for all workouts saved
	 * 
	 * @author Romina
	 * 
	 * @return A list with date, weight values
	 */
	
	public List<Serie> getWeightByDate(){
		
		return daoEjercicio.getWeightByDate();
		
	}

}
