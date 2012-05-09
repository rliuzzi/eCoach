package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;
import com.caloriecalc.beans.Progreso;
import com.caloriecalc.beans.UserSettings;
import com.caloriecalc.content.UserSettingsPreferencesTransformer;
import com.caloriecalc.lao.LaoEjercicio;
import com.caloriecalc.lao.LaoProgreso;
import com.caloriecalc.lao.Utilities;

/**
 * @author Romina
 * 
 */
public class EjercicioActualActivity extends Activity {

	private Button btnFinalizar;

	private TextView lblLatitud;
	private TextView lblLongitud;
	private TextView lblAltitude;
	private TextView lblEstado;
	private TextView lblCalories;
	private TextView lblDistance;

	private LaoProgreso laoProgreso;
	private LaoEjercicio laoEjercicio;
	
	private Ejercicio ejercicio;
	private Progreso progresoInit;
	private Progreso progresoEnd;
	
	//contador de localizaciones recibidas 
	private int i = 0;
	private double relTime;

	private LocationManager locationManager;

	/**
	 * Registrarmos un LocationListener para recibir actualizaciones de la
	 * posicion
	 */
	private LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			//Cada vez que recibo una actualizacion de la ubicacion incremento el contador
			i++;
			
			//Guardo el numero de posicion en la bbdd. Mejora: cache?
			progresoEnd = laoProgreso.guardarProgreso(ejercicio.getId(),
					location.getLatitude(), location.getLongitude(),
					location.getAltitude(), i);
			
			if (i>1){
				
				//get progreso i. inicializar
				progresoEnd = laoProgreso.getProgresoByEjIdAndLocId(ejercicio.getId(), i);
				
				//get progreso i-1. inicializar
				progresoInit = laoProgreso.getProgresoByEjIdAndLocId(ejercicio.getId(), i-1);
				
				//calcular tiempo
				relTime = laoProgreso.calculateTime(progresoInit.getId(), progresoEnd.getId());
				
				
				//calcular distancia
				progresoEnd.setDistance(laoProgreso.calculateDistance(progresoInit, progresoEnd));
				
				//calcular velocidad
				progresoEnd.setSpeed(laoProgreso.calculateSpeed(progresoEnd.getDistance(), relTime));
				
				//calcular calorias
				progresoEnd.setCalories(Utilities.calculateCaloriesBurned(ejercicio.getPeso(), ejercicio.getTipoEjercicio(), progresoEnd.getSpeed(), relTime));
				
			} else {
				
				progresoEnd.setDistance(0.00);
				
				progresoEnd.setCalories(0.00);
			
				progresoEnd.setSpeed(0.00);
				
				relTime = 0;
				
			}
			
			mostrarPosicion(progresoEnd);
			laoProgreso.updateProgress(progresoEnd);
			
		}

		public void onProviderDisabled(String provider) {
			lblEstado.setText("Provider OFF");
		}

		public void onProviderEnabled(String provider) {
			lblEstado.setText("Provider ON ");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

			/*
			 * STATUS VALUES 2 = AVAILABLE 1 = TEMPORARILY_UNAVAILABLE 0 =
			 * OUT_OF_SERVICE
			 */

			lblEstado.setText("Provider Status: " + status);

		}
	};

	private OnClickListener finalizar = new OnClickListener() {

		public void onClick(View v) {

			locationManager.removeUpdates(locationListener);

			laoProgreso.finalizarEjercicio(ejercicio);

			ejercicio = laoEjercicio.consultarEjercicio(ejercicio.getId());

			lblCalories.setText(ejercicio.getCalorias().toString());

			lblDistance.setText(ejercicio.getDistancia().toString());

		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ejercicio_actual);
		
		

		laoProgreso = new LaoProgreso(EjercicioActualActivity.this);
		laoEjercicio = new LaoEjercicio(EjercicioActualActivity.this);

		lblLatitud = (TextView) findViewById(R.id.LblPosLatitud);
		lblLongitud = (TextView) findViewById(R.id.LblPosLongitud);
		lblAltitude = (TextView) findViewById(R.id.LblPosPrecision);
		
		lblEstado = (TextView) findViewById(R.id.LblEstado);
		lblCalories = (TextView) findViewById(R.id.calories);
		lblDistance = (TextView) findViewById(R.id.distance);

		btnFinalizar = (Button) findViewById(R.id.BtnDesactivar);
		btnFinalizar.setOnClickListener(finalizar);

		// Leermos el intent que llamo a esta activdad para averiguar el valor
		// del tipoEjercicio
		// Devuvele -1 si no se ha inicializado en la llamada.

		Intent i = getIntent();
		TipoEjercicio tipoEjercicio = TipoEjercicio.values()[i.getIntExtra(
				"ejercicio", -1)];
		
		
		
		//Retrieve SharedPreferences to be transformed and stored
		SharedPreferences settings = getSharedPreferences(UserRegistrationActivity.PREFS_NAME, MODE_PRIVATE);
		String s = settings.getString(UserRegistrationActivity.USER_SEX, null);
		String d = settings.getString(UserRegistrationActivity.USER_DOB, null) ;
		int w = settings.getInt(UserRegistrationActivity.USER_WEIGHT, 0);
		int h = settings.getInt(UserRegistrationActivity.USER_HEIGHT, 0);
		
		UserSettings userData = UserSettingsPreferencesTransformer.getUserSettings(d, s, h, w); 
				
		ejercicio = laoEjercicio.crearEjercicio(tipoEjercicio, userData.getWeight());

		comenzarLocalizacion();

	}

	/**
	 * @author Romina
	 */
	private void comenzarLocalizacion() {
		// Obtenemos una referencia al LocationManager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Location loc = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		// Mostramos la última posición conocida
		//mostrarPosicion(loc);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				30000, 0, locationListener);
	}

	/**
	 * @param loc
	 * @author Romina
	 */
	private void mostrarPosicion(Progreso progreso) {
		if (progreso != null) {
			lblLatitud.setText("Latitud: " + progreso.getLatitude());
			lblLongitud.setText("Longitud: " + progreso.getLongitude());
			lblAltitude.setText("Altitud: " + progreso.getAltitude());
			lblCalories.setText(progreso.getCalories().toString() + " Kcal");
			lblDistance.setText(progreso.getDistance().toString() + " mts");
			

		} 
	}

	/*
	 * //TODO Implement onPause()
	 * 
	 * @Override protected void onPause(){
	 * 
	 * }
	 * 
	 * //TODO Implement onResume()
	 * 
	 * @Override protected void onResume(){
	 * 
	 * }
	 */

}
