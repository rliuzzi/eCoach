package com.caloriecalc.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.caloriecalc.beans.Progreso;

import android.content.Context;
import android.database.Cursor;

public class DaoProgreso extends DataBaseHelper {

	Context myContext;
	
	public DaoProgreso(Context context){
		super(context);
		this.myContext = context;
	}
	
	
	public void LogProgress(int ejercicioId, Double lat, Double lon) {

		long timestamp = Calendar.getInstance().getTime().getTime();
		// String sql =
		// "INSERT INTO Progreso (Id, EjercicioId, Lat, Lon) VALUES(?, ?, ?, ?)";
		// myDataBase.execSQL(sql, new Object[] {
		// timestamp,
		// ejercicioId,
		// lat,
		// lon
		// });
		String sql = "INSERT INTO Progreso (_id, EjercicioId, Lat, Long) VALUES("
				+ timestamp
				+ ", "
				+ ejercicioId
				+ ", "
				+ lat
				+ ", "
				+ lon
				+ ")";
		myDataBase.execSQL(sql);
	}

	public List<Progreso> getProgresos(int ejercicioId) {
		
		List<Progreso> list = new ArrayList<Progreso>();
		
		String sql = "SELECT * FROM Progreso WHERE EjercicioId = " + ejercicioId;
		Cursor c = myDataBase.rawQuery(sql, null);
		while (c.moveToNext()) {
			Progreso p = new Progreso();
			p.setId(new Date(c.getLong(0)));
			p.setEjercicioId(c.getInt(1));
			p.setLat(c.getDouble(2));
			p.setLon(c.getDouble(3));	
			list.add(p);
		}
		
		return list;		
	}

	
}
