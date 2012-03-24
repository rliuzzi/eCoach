package com.caloriecalc.lao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.location.Location;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Progreso;
import com.caloriecalc.dao.DaoEjercicio;
import com.caloriecalc.dao.DaoProgreso;

/**
 * @author Romina
 * 
 */
public class LaoProgreso {

	private DaoEjercicio daoEjercicio;
	private DaoProgreso daoProgreso;

	/**
	 * @param context
	 */
	public LaoProgreso(Context context) {
		try {
			daoEjercicio = new DaoEjercicio(context);
			daoProgreso = new DaoProgreso(context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param ejercicio
	 */
	public void finalizarEjercicio(Ejercicio ejercicio) {

		double totalDistance = 0;
		double totalCalories = 0;
		Date fechaUltimoProgreso = null;

		Progreso lastProgreso = null;

		List<Progreso> listProgreso = this.getProgresos(ejercicio);
		for (Progreso progreso : listProgreso) {

			if (lastProgreso != null) {

				float[] results = new float[2];
				Location.distanceBetween(lastProgreso.getLat(),
						lastProgreso.getLon(), progreso.getLat(),
						progreso.getLon(), results);

				double distance = results[0]; // in meters
				// double time = (progreso.getId().getTime() -
				// lastProgreso.getId().getTime()) / 1000; // seconds
				// double speed = distance / time; // in meters/second
				// double calories = peso * distance * ????;
				double calories = 555;

				totalDistance += distance;
				totalCalories += calories;
			}

			lastProgreso = progreso;
			fechaUltimoProgreso = progreso.getId();

		}
		daoEjercicio.actualizarEjercicio(ejercicio.getId(),
				fechaUltimoProgreso, totalDistance, totalCalories);
	}

	/**
	 * @param ejercicio
	 * @return
	 */
	public List<Progreso> getProgresos(Ejercicio ejercicio) {
		return this.daoProgreso.getProgresos(ejercicio.getId());
	}

	/**
	 * @param ejercicioId
	 * @param latitude
	 * @param longitude
	 */
	public void guardarProgreso(int ejercicioId, Double latitude,
			Double longitude) {

		daoProgreso.LogProgress(ejercicioId, latitude, longitude);

	}

}
