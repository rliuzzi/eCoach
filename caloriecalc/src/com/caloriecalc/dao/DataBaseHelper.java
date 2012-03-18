package com.caloriecalc.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Progreso;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.caloriecalc/databases/";

	private static String DB_NAME = "CalorieCalc.sqlite";

	//private static String DB_TABLE = "Ejercicio";

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		//(condition) ? (value if condition true) : (value if condition false)
		return checkDB != null ? true : false;
	
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// Add your public helper methods to access and get content from the
	// database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd
	// be easy
	// to you to create adapters for your views.

	// public Cursor getEjercicios() {
	// String sql = "SELECT * FROM EjercicioProgreso";
	// return myDataBase.rawQuery(sql, null);
	// }

	public List<Ejercicio> getEjercicios() {
		String sql = "SELECT * FROM EjercicioProgreso";
		Cursor c = myDataBase.rawQuery(sql, null);

		List<Ejercicio> list = new ArrayList<Ejercicio>();

		while (c.moveToNext()) {
			Ejercicio ej = new Ejercicio();
			ej.setId(c.getInt(0));
			ej.setFechaInicio(new Date(c.getLong(1)));
			ej.setFechaFin(new Date(c.getLong(2)));
			list.add(ej);
		}

		return list;

	}

	public int crearEjercicio(Date fechaInicio, int tipoEjercicio, int peso) {
		String sql = "INSERT INTO Ejercicio (HoraInicio, TipoId, Peso) VALUES (" 
			+ fechaInicio.getTime() + ", "
			+ tipoEjercicio + ", "
			+ peso + ")";
		myDataBase.execSQL(sql);
		Cursor c = myDataBase.rawQuery("SELECT last_insert_rowid()", null);
		c.moveToNext();
		return c.getInt(0);
		//devuelve el primer campo de la BBDD (ejercicioId)
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

	public void actualizarEjercicio(int ejercicioId, Date horaFin, double totalDistance, double totalCalories) {
		
		String sql = "UPDATE Ejercicio SET " 
			+ "Distancia = " + totalDistance + ", " 
			+ "Calorias =  " + totalCalories + ", "
			+ "HoraFin = " + horaFin.getTime()
			+ " WHERE _id = " + ejercicioId;
		
		myDataBase.execSQL(sql);		
	}
	
	public Ejercicio getEjercicio(int idEjercicio) {
		String sql = "SELECT * FROM Ejercicio WHERE _id = " + idEjercicio; 
		Cursor c = myDataBase.rawQuery(sql, null);
		Ejercicio ej = null;
		if (c.moveToNext()) {
			ej = new Ejercicio();
			ej.setId(c.getInt(0));
			ej.setFechaInicio(new Date(c.getLong(1)));
			ej.setTipoEjercicio(c.getInt(2));
			ej.setPeso(c.getInt(3));
			ej.setDistancia(c.getInt(4));
			ej.setCalorias(c.getInt(5));
			ej.setFechaFin(new Date(c.getLong(6)));
			
		}
		return ej;
	}

}