package com.caloriecalc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	 * @param ejercicioId
	 * @param latitude
	 * @param longitude
	 */
	public void LogProgress(int ejercicioId, Double latitude, Double longitude) {

		this.openDataBase();

		long timestamp = Calendar.getInstance().getTime().getTime();

		String sql = "INSERT INTO Progreso (_id, EjercicioId, Lat, Long) VALUES("
				+ timestamp
				+ ", "
				+ ejercicioId
				+ ", "
				+ latitude
				+ ", "
				+ longitude + ")";

		myDataBase.execSQL(sql);

		this.close();

	}

	/**
	 * @param ejercicioId
	 * @return
	 */
	public List<Progreso> getProgresos(int ejercicioId) {

		List<Progreso> list = new ArrayList<Progreso>();
		this.openDataBase();

		String sql = "SELECT * FROM Progreso WHERE EjercicioId = "
				+ ejercicioId;
		Cursor c = myDataBase.rawQuery(sql, null);
		while (c.moveToNext()) {
			Progreso p = new Progreso();
			p.setId(new Date(c.getLong(0)));
			p.setEjercicioId(c.getInt(1));
			p.setLat(c.getDouble(2));
			p.setLon(c.getDouble(3));
			list.add(p);
		}

		this.close();
		return list;
	}

}
