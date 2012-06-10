package com.caloriecalc.lao;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Location;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.MapItem;
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
	 * Deletes all the progresses associated to the ejercicioId provided.
	 * 
	 * @param ejercicioId
	 */
	public void deleteProgreso (int ejercicioId){
		
		daoProgreso.deleteProgreso(ejercicioId);
		
	}


	/**
	 * Performs all operations necessary in order to finalize the exercise.
	 * Calculations and updates are handled for both exercises and progresses.
	 * 
	 * @author Romina
	 * @param ejercicio
	 */
	public void finalizarEjercicio(int exerciseId) {

	 daoEjercicio.actualizarEjercicio(exerciseId, daoProgreso.maxColumn("_id", exerciseId),
				daoProgreso.sumColumn("Distance", exerciseId), daoProgreso.sumColumn("Calories", exerciseId));
	 
	}
	
	
	/**
	 * Given a progressId, calls on to the data layer to update the calculated fields
	 * distance, speed and calories burned.
	 * 
	 * @author Romina
	 * @param  progressId
	 * @param  speed
	 * @param  distance
	 * @param  calories
	 */
	public void updateProgress (Progreso p) {

		daoProgreso.updateProgress(p.getId(), p.getSpeed(), p.getDistance(), p.getCalories());

	}	
	
	
	

	/**
	 * Given an exercise, calls on to the data layer to retrieve the list of
	 * progresses associated to the exercise.
	 * 
	 * @author Romina
	 * @param ejercicio
	 * @return progresos
	 */
	public List<Progreso> getProgresos(Ejercicio ejercicio) {

		return this.daoProgreso.getProgresos(ejercicio.getId());

	}

	/**
	 * Calls on to the data layer to save basic progress information.
	 * 
	 * @author Romina
	 * @param ejercicioId
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 */

	public Progreso guardarProgreso(int ejercicioId, Double latitude,
			Double longitude, Double altitude, int locId) {

		return daoProgreso.LogProgress(ejercicioId, latitude, longitude, altitude, locId);

	} 
	
	
	/**
	 * Retrieves the progress identified by the progress id provided.
	 * 
	 * @author Romina
	 * 
	 * @param progresoId
	 * @return progress
	 */
	public Progreso getProgreso(double progresoId){
		
		return daoProgreso.getProgreso(progresoId);

	}
	
	/**
	 * Retrieves the progress identified by the exercise id and location counter provided
	 * 
	 * @author Romina
	 * 
	 * @param ejercicioId
	 * @param location count
	 * @return progress
	 */
	public Progreso getProgresoByEjIdAndLocId(int ejercicioId, int locId){
		
		return daoProgreso.getProgresoByEjIdAndLocId(ejercicioId, locId);

	}
	
	/**
	 * Given two progresses, the distance in meters between their locations is
	 * returned.
	 * 
	 * @author Romina
	 * @param progress
	 * @param nextProgress
	 * @return distance
	 */

	public double calculateDistance(Progreso progress, Progreso nextProgress) {

		float[] results = new float[2];

		Location.distanceBetween(progress.getLatitude(),
				progress.getLongitude(), nextProgress.getLatitude(),
				nextProgress.getLongitude(), results);

		double distance = results[0];

		return distance;

	}
	
	

	/**
	 * Given an initial time and an ending time in milliseconds, elapsed
	 * time in seconds is returned.
	 * 
	 * @author Romina
	 * @param initTime
	 * @param endTime
	 * @return time
	 */
	public double calculateTime(double initTime, double endTime) {

		double time;

		time = (endTime - initTime) / 1000;

		return time;

	}

	/**
	 * Given a distance and a time, the speed is returned maintaining the units
	 * provided.
	 * 
	 * @author Romina
	 * @param distance
	 * @param time
	 * @return speed
	 */
	public double calculateSpeed(Double distance, Double time) {

		double speed;

		speed = distance / time;

		return speed;

	}
	
	/**
	 * Deletes an exercise and all of its associated progresses.
	 * 
	 * @param ejercicioId
	 */
	public void cancelEjercicioProgreso(int ejercicioId){
		
		daoProgreso.deleteProgreso(ejercicioId);
		
		daoEjercicio.deleteEjercicio(ejercicioId);
		
	}
	
	/**
	 * Retrieves a list of MapItems associated to an exercise.
	 * 
	 * @author Romina
	 * 
	 * @param ejercicioId
	 * @return list of MapItems
	 */
	
	public List<MapItem> getMapItems(int ejercicioId) {

		return this.daoProgreso.getMapItems(ejercicioId);

	}

	

}
