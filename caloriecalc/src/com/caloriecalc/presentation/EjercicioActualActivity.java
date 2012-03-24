package com.caloriecalc.presentation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.caloriecalc.R;
import com.caloriecalc.beans.Progreso;
import com.caloriecalc.dao.DaoEjercicio;
import com.caloriecalc.dao.DaoProgreso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EjercicioActualActivity extends Activity {
	

	private Button btnDesactivar;
	
	private TextView lblLatitud;
	private TextView lblLongitud;
	private TextView lblPrecision;
	private TextView lblEstado;

	private LocationManager locManager;

	// Nos registramos para recibir actualizaciones de la posición
	private LocationListener locListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			mostrarPosicion(location);
			guardarProgreso(location);
		}

		public void onProviderDisabled(String provider) {
			lblEstado.setText("Provider OFF");
		}

		public void onProviderEnabled(String provider) {
			lblEstado.setText("Provider ON ");
		}

		public void onStatusChanged(String provider, int status,
				Bundle extras) {
			Log.i("", "Provider Status: " + status);
			lblEstado.setText("Provider Status: " + status);
		}
	};

	
	private int tipoEjercicio;
	private int ejercicioId;
	private int peso;


	private DaoEjercicio myDaoEjercicio;
	private DaoProgreso myDaoProgreso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ejercicio_actual);
		
		//Leermos el intent que llamo a esta activdad para averiguar el valor del tipoEjercicio
		//Devuvele -1 si no se ha inicializado en la llamada.
		

		
			
		
        btnDesactivar = (Button)findViewById(R.id.BtnDesactivar);
		
		lblLatitud = (TextView) findViewById(R.id.LblPosLatitud);
		lblLongitud = (TextView) findViewById(R.id.LblPosLongitud);
		lblPrecision = (TextView) findViewById(R.id.LblPosPrecision);
		lblEstado = (TextView) findViewById(R.id.LblEstado);
		       
		btnDesactivar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				locManager.removeUpdates(locListener);
				finalizarEjercicio();
				// Ir a VerEjercicioActivity(); // (send ejercicioId)
				Intent i = new Intent(EjercicioActualActivity.this,
						CalorieCalcResult.class);
				i.putExtra("ejercicioId", ejercicioId);
				startActivity(i);
			}

		});
		
		
		myDaoEjercicio = new DaoEjercicio(EjercicioActualActivity.this);
		myDaoProgreso = new DaoProgreso(EjercicioActualActivity.this);
		
		try {
			myDaoEjercicio.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}
		
		
		
		Intent i = getIntent();
		tipoEjercicio = i.getIntExtra("ejercicio", -1);
		//System.out.println("Tipo de Ejercicio: " + tipoEjercicio);
		
		Date fechaInicio = Calendar.getInstance().getTime();	
		
		//TO DO
		peso = 123;
		
		ejercicioId = myDaoEjercicio.crearEjercicio(fechaInicio, tipoEjercicio, peso );
		comenzarLocalizacion();

	}

	private void comenzarLocalizacion() {
		// Obtenemos una referencia al LocationManager
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		//List<String> listaProviders = locManager.getAllProviders();
		
		
		//LocationProvider provider = locManager.getProvider(listaProviders.get(0));
		//int precision0 = provider.getAccuracy();
		//int consumoRecursos0 = provider.getPowerRequirement();
		//boolean requiresNetwork0 = provider.requiresNetwork();
		
		//LocationProvider provider1 = locManager.getProvider(listaProviders.get(1));
		//int precision1 = provider.getAccuracy();
		//int consumoRecursos1 = provider.getPowerRequirement();
		//boolean requiresNetwork1 = provider.requiresNetwork();
		
		// Obtenemos la última posición conocida
		Location loc = locManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		

		// Mostramos la última posición conocida
		mostrarPosicion(loc);
		guardarProgreso(loc);

		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locListener);
	}

	private void mostrarPosicion(Location loc) {
		if (loc != null) {
			lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
			lblLongitud.setText("Longitud: "
					+ String.valueOf(loc.getLongitude()));
			lblPrecision.setText("Precision: "
					+ String.valueOf(loc.getAccuracy()));
			Log.i("",
					String.valueOf(loc.getLatitude() + " - "
							+ String.valueOf(loc.getLongitude())));
		} else {
			lblLatitud.setText("Latitud: (sin_datos)");
			lblLongitud.setText("Longitud: (sin_datos)");
			lblPrecision.setText("Precision: (sin_datos)");
		}
	}
	
	private void guardarProgreso(Location loc){
		if (loc != null) {
			myDaoProgreso.LogProgress(ejercicioId, loc.getLatitude(), loc.getLongitude());
		}
	}

	private void finalizarEjercicio() {
		
		double totalDistance = 0;	
		double totalCalories = 0;
		Date fechaUltimoProgreso = null;
		 
		
		 Progreso lastProgreso = null;

		 List<Progreso> listProgreso = myDaoProgreso.getProgresos(ejercicioId);
		 for (Progreso progreso : listProgreso) {
			 
			 if (lastProgreso != null) {
				
				float[] results = new float[2];
				Location.distanceBetween(
						lastProgreso.getLat(), 
						lastProgreso.getLon(), 
						progreso.getLat(),
						progreso.getLon(),
						results);
				
				double distance = results[0];	// in meters
				//double time = (progreso.getId().getTime() - lastProgreso.getId().getTime()) / 1000; // seconds
				//double speed = distance / time;	// in meters/second
				//double calories = peso * distance * ????;
				double calories = 555;
				
				totalDistance += distance;
				totalCalories += calories;
			 }
			 
			 lastProgreso = progreso;
			 fechaUltimoProgreso = progreso.getId(); 
			 
		 }
		 myDaoEjercicio.actualizarEjercicio(ejercicioId, fechaUltimoProgreso, totalDistance, totalCalories);
		 myDaoEjercicio.close();
	}
}
