package com.caloriecalc.lao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;
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

}
