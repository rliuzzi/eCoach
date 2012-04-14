package com.caloriecalc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.caloriecalc.beans.Progreso;

import android.content.Context;
import android.database.Cursor;

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
	public void LogProgress(int ejercicioId, Double latitude, Double longitude, Double altitude) {

		this.openDataBase();

		double timestamp = Calendar.getInstance().getTime().getTime();

		String sql = "INSERT INTO Progreso (_id, EjercicioId, Latitude, Longitude, Altitude) VALUES("
				+ timestamp
				+ ", "
				+ ejercicioId
				+ ", "
				+ latitude
				+ ", "
				+ longitude
				+ ", "
				+ altitude + ")";

		myDataBase.execSQL(sql);

		this.close();

	}

	/**
	 * Updates the speed field for the progress associated to the id provided.
	 * 
	 * @author Romina
	 * @param id
	 * @param speed
	 */
	public void updateProgressSpeeds(double id, double speed) {

		this.openDataBase();

		String sql = "UPDATE Progreso SET " + "speed = " + speed
				+ " WHERE _id = " + id;

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
			list.add(p);
		}

		this.close();

		return list;
	}

}
