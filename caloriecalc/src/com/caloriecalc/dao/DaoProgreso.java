package com.caloriecalc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.caloriecalc.beans.Progreso;

/**
 * @author Romina
 * 
 */
public class DaoProgreso extends DataBaseHelper {

	/**
	 * @param context
	 * @throws IOException
	 */
	public DaoProgreso(Context context) throws IOException {
		super(context);
	}

	/**
	 * Persists basic progress information.
	 * 
	 * @author Romina
	 * @param ejercicioId
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 */
	public Progreso LogProgress(int ejercicioId, Double latitude, Double longitude, Double altitude, int locId) {
		
		Progreso progreso = new Progreso();

		this.openDataBase();

		double timestamp = Calendar.getInstance().getTime().getTime();

		String sql = "INSERT INTO Progreso (_id, EjercicioId, Latitude, Longitude, Altitude, LocId) VALUES("
				+ timestamp
				+ ", "
				+ ejercicioId
				+ ", "
				+ latitude
				+ ", "
				+ longitude
				+ ", "
				+ altitude
				+ ", "
				+ locId
				+ ")";

		myDataBase.execSQL(sql);
		
		this.close();
		
		progreso.setId(timestamp);
		progreso.setEjercicioId(ejercicioId);
		progreso.setLatitude(latitude);
		progreso.setLongitude(longitude);
		progreso.setAltitude(altitude);
		progreso.setLocId(locId);
		
		return progreso;

	}

	/**
	 * Updates the speed, distance and calories fields for the progress associated to the id provided.
	 * 
	 * @author Romina
	 * @param id
	 * @param speed
	 * @param distance
	 * @param calories
	 */
	public void updateProgress(double id, double speed, double distance, double calories) {

		this.openDataBase();

		String sql = "UPDATE Progreso SET " + "speed = " + speed + " ,distance = " + distance + " ,calories = " + calories
				+ " WHERE _id = " + id;

		myDataBase.execSQL(sql);

		this.close();

	}
	
	
	/**
	 * Deletes all the progresses associated to the ejercicioId provided.
	 * 
	 * @param ejercicioId
	 */
	public void deleteProgreso (int ejercicioId){
		
		this.openDataBase();
		
		String sql = "DELETE FROM Progreso WHERE EjercicioId = " + ejercicioId;
		
		myDataBase.execSQL(sql);
		
		this.close();
		
	}

	/**
	 * Retrieves a list of progresses associated the the exerciseId provided.
	 * 
	 * @author Romina
	 * @param ejercicioId
	 * @return list
	 */
	public List<Progreso> getProgresos(int ejercicioId) {

		List<Progreso> list = new ArrayList<Progreso>();

		this.openDataBase();

		String sql = "SELECT * FROM Progreso WHERE EjercicioId = "
				+ ejercicioId;

		Cursor c = myDataBase.rawQuery(sql, null);

		while (c.moveToNext()) {
			Progreso p = new Progreso();
			p.setId(c.getDouble(0));
			p.setEjercicioId(c.getInt(1));
			p.setLatitude(c.getDouble(2));
			p.setLongitude(c.getDouble(3));
			p.setAltitude(c.getDouble(4));
			p.setSpeed(c.getDouble(5));
			p.setDistance(c.getDouble(6));
			p.setCalories(c.getDouble(7));
			p.setLocId(c.getInt(8));
			list.add(p);
		}

		this.close();

		return list;
	}
	
	/**
	 * @param idProgreso
	 * @return progreso
	 */
	public Progreso getProgreso(double idProgreso) {
		this.openDataBase();
		String sql = "SELECT * FROM Progreso WHERE _id = " + idProgreso;
		Cursor c = myDataBase.rawQuery(sql, null);
		Progreso progreso = null;
		if (c.moveToNext()) {
			progreso = new Progreso();
			progreso.setId(c.getDouble(0));
			progreso.setEjercicioId(c.getInt(1));
			progreso.setLatitude(c.getDouble(2));
			progreso.setLongitude(c.getDouble(3));
			progreso.setAltitude(c.getDouble(4));
			progreso.setSpeed(c.getDouble(5));
			progreso.setDistance(c.getDouble(6));
			progreso.setCalories(c.getDouble(7));
			progreso.setLocId(c.getInt(8));
		}
		this.close();
		return progreso;
	}
	
	/**
	 * 
	 * Retrieve the progress log identified by the exerciseId and loacalization counter
	 * 
	 * @author Romina
	 * 
	 * @param ejercicioId
	 * @param LocId
	 * @return
	 */
	public Progreso getProgresoByEjIdAndLocId(int ejercicioId, int locId) {
		this.openDataBase();
		String sql = "SELECT * FROM Progreso WHERE EjercicioId = " + ejercicioId + " AND LocId = " + locId;
		Cursor c = myDataBase.rawQuery(sql, null);
		Progreso progreso = null;
		if (c.moveToNext()) {
			progreso = new Progreso();
			progreso.setId(c.getDouble(0));
			progreso.setEjercicioId(c.getInt(1));
			progreso.setLatitude(c.getDouble(2));
			progreso.setLongitude(c.getDouble(3));
			progreso.setAltitude(c.getDouble(4));
			progreso.setSpeed(c.getDouble(5));
			progreso.setDistance(c.getDouble(6));
			progreso.setCalories(c.getDouble(7));
			progreso.setLocId(c.getInt(8));
		}
		this.close();
		return progreso;
	}
	

}
