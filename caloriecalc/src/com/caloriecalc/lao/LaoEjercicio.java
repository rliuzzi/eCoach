package com.caloriecalc.lao;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;

import com.caloriecalc.beans.Ejercicio;
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
	 * @return
	 */
	public Ejercicio crearEjercicio(int tipoEjercicio, int peso){
		
		Date fechaInicio = Calendar.getInstance().getTime();
		return daoEjercicio.crearEjercicio(fechaInicio, tipoEjercicio, peso);
	}

}
