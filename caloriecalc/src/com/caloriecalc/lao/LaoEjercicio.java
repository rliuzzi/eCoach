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
	 * @return List of exercises
	 */
	public ArrayList<Ejercicio> getExercises (){
		
		return daoEjercicio.getExerciseList();
		
	}

}
