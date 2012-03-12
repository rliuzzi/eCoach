package com.caloriecalc.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*By creating an adapter to handle the DB communication,
 * we can communicate with the class via programming language
 * of Java while this adapter class does the translation and 
 * adapts certain Java requests into SQLLite-specific commands.
*/


public class DBAdapter {
	
	//Constraints, Fields and Constructors of the tagDbAdapter class

	//*Nombre de la BBDD existente en sistema Android
	private static final String DATABASE_NAME = "datos_gps";
	//Nombre de tabla que contendra el log de las coordenadas gps
	private static final String DATABASE_TABLE = "coordenadas_tiempo";
	//Al actualizar la version de la BBDD se incrementa este valor mediante el metodo onUpgrade() de la clase DatabaseHelper
	private static final int DATABASE_VERSION = 1;
	
	//Nombres de las columnas de la tabla
	private static final String TAG = "DBAdapter";
	public static final String 	KEY_ROWID = "_id";
	public static final String 	KEY_FECHA = "fecha";
	public static final String 	KEY_LATITUD = "latitud";
	public static final String 	KEY_LONGITUD = "longitud";
	public static final String 	KEY_ALTITUD = "altitud";
	
	//classwide Daatabase helper instance variable
	private DatabaseHelper mDbHelper;
	//class-level instance of the SQLite DB object that allows as to create, delete, etc
	private SQLiteDatabase mDb;
	//Context object that will be associated with the SQLite DB object
	private final Context mCtx;	
	
	//Create Script
	private static final String DATABASE_CREATE = 
										"create table " + DATABASE_TABLE + " (" + 
										KEY_ROWID + " integer primary key autoincrement," + 
										KEY_FECHA + " long not null," +
										KEY_LATITUD + " numeric not null," +
										KEY_LONGITUD + " numeric not null," +
										KEY_ALTITUD + " numeric not null);";
	

	//Constructor
	public DBAdapter (Context ctx){
		this.mCtx = ctx;
		mDbHelper = new DatabaseHelper(mCtx);
	}
	
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override 
		public void onCreate(SQLiteDatabase db){
			db.execSQL(DATABASE_CREATE);
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			
			Log.w(TAG, "Actualizando BD de version " + oldVersion 
	                  + " a "
	                  + newVersion + ", se destruiran todos los datos");
	            db.execSQL("DROP TABLE IF EXISTS coordenadas_tiempo");
	            onCreate(db);
			
		}
		
	}
	
	//---abre la bbdd---
    public DBAdapter open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    //---cierra la bbdd---    
    public void close() 
    {
        mDbHelper.close();
    }
    
    //---inserta un dato en la bbdd---
    public long insertPunto(long fecha, long latitud, long longitud, long altitud) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_FECHA, fecha);
        initialValues.put(KEY_LATITUD, latitud);
        initialValues.put(KEY_LONGITUD, longitud);
        initialValues.put(KEY_ALTITUD, altitud);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    //---borra un dato---
    public boolean deleteDato(long rowId) 
    {
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + 
        		"=" + rowId, null) > 0;
    }

    //---devuelve todos los datos---
    public Cursor getTodosDatos() 
    {
        return mDb.query(DATABASE_TABLE, new String[] {
        		KEY_ROWID, 
        		KEY_FECHA,
        		KEY_LATITUD,
        		KEY_LONGITUD,
                KEY_ALTITUD}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //---devuelve un dato---
    public Cursor getDato(long rowId) throws SQLException 
    {
        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] {
                		KEY_ROWID, 
                		KEY_FECHA,
                		KEY_LATITUD,
                		KEY_LONGITUD,
                        KEY_ALTITUD}, 
                		KEY_ROWID + "=" + rowId, 
                		null,
                		null,
                		null, 
                		null,  
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---actualiza un dato---
    public boolean updateDato(long rowId, long fecha, long latitud, long longitud, long altitud){
        ContentValues args = new ContentValues();
        args.put(KEY_FECHA, fecha);
        args.put(KEY_LATITUD, latitud);
        args.put(KEY_LONGITUD, longitud);
        args.put(KEY_ALTITUD, altitud);
        return mDb.update(DATABASE_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }
	
	
}

