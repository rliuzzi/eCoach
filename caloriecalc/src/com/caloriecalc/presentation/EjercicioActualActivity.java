package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
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
	private TextView lblTipo;
	private TextView lblCalories;
	private TextView lblDistance;
	private TextView lblSpeed;
	private Chronometer timer;

	private LaoProgreso laoProgreso;
	private LaoEjercicio laoEjercicio;
	
	private Ejercicio ejercicio;
	private Progreso progresoInit;
	private Progreso progresoEnd;
	
	private int i = 0;
	private double calories = 0;
	private double distance = 0;
	private double speed = 0;
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
				
				//calcular tiempo seconds
				relTime = laoProgreso.calculateTime(progresoInit.getId(), progresoEnd.getId());
				
				//calcular distancia meters
				progresoEnd.setDistance(laoProgreso.calculateDistance(progresoInit, progresoEnd));
				
				//calcular velocidad Km/h
				progresoEnd.setSpeed(laoProgreso.calculateSpeed(Utilities.metersToKm(progresoEnd.getDistance()), Utilities.secondsToHours(relTime)));
				
				//calcular calorias
				progresoEnd.setCalories(Utilities.calculateCaloriesBurned(ejercicio.getPeso(), ejercicio.getTipoEjercicio(), progresoEnd.getSpeed(), relTime));
				
				distance += progresoEnd.getDistance();
				
				calories += progresoEnd.getCalories();
				
				speed = progresoEnd.getSpeed();
				
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
			lblEstado.setText("Estado de la conexion: Red movil no activa");
		}

		public void onProviderEnabled(String provider) {
			lblEstado.setText("Estado de la conexion: Red movil activa ");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

			/*
			 * STATUS VALUES 
			 * 2 = AVAILABLE 
			 * 1 = TEMPORARILY_UNAVAILABLE 
			 * 0 = OUT_OF_SERVICE
			 */
			
			String s = "";
			
			switch (status){
			
			case 0 : 
				s = "Fuera de servicio";
				break;
			case 1 : 
				s = "Temporalmente no disponible";
				break;
			case 2:
				s = "Disponible";
				break;
			}

			lblEstado.setText("Estado de la conexion: " + s);

		}
	};

	private OnClickListener finalizar = new OnClickListener() {

		public void onClick(View v) {
			
			timer.stop();

			locationManager.removeUpdates(locationListener);

			laoProgreso.finalizarEjercicio(ejercicio.getId());

			ejercicio = laoEjercicio.consultarEjercicio(ejercicio.getId());

			lblCalories.setText(ejercicio.getCalorias().toString()  + " Kcal");

			lblDistance.setText(ejercicio.getDistancia().toString() + " mts");
			
			lblSpeed.setText(speed + " Km/h");

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
		lblAltitude = (TextView) findViewById(R.id.LblPosAltitude);
		lblEstado = (TextView) findViewById(R.id.lbl_provider_status);
		
		lblTipo = (TextView) findViewById(R.id.LblTipoEjercicio);
		lblCalories = (TextView) findViewById(R.id.calories);
		lblDistance = (TextView) findViewById(R.id.distance);
		lblSpeed = (TextView) findViewById(R.id.txt_speed);
		
		timer = (Chronometer) findViewById(R.id.chronometer);

		btnFinalizar = (Button) findViewById(R.id.BtnDesactivar);
		btnFinalizar.setOnClickListener(finalizar);

		// Leermos el intent que llamo a esta activdad para averiguar el valor
		// del tipoEjercicio
		// Devuvele -1 si no se ha inicializado en la llamada.

		Intent i = getIntent();
		TipoEjercicio tipoEjercicio = TipoEjercicio.values()[i.getIntExtra(
				"ejercicio", -1)];
		
		lblTipo.setText("Tipo de ejercicio: 	  " + tipoEjercicio.name());
		
		//Retrieve SharedPreferences to be transformed and stored
		SharedPreferences settings = getSharedPreferences(UserRegistrationActivity.PREFS_NAME, MODE_PRIVATE);
		String s = settings.getString(UserRegistrationActivity.USER_SEX, null);
		String d = settings.getString(UserRegistrationActivity.USER_DOB, null) ;
		int w = settings.getInt(UserRegistrationActivity.USER_WEIGHT, 0);
		int h = settings.getInt(UserRegistrationActivity.USER_HEIGHT, 0);
		
		UserSettings userData = UserSettingsPreferencesTransformer.getUserSettings(d, s, h, w); 
				
		ejercicio = laoEjercicio.crearEjercicio(tipoEjercicio, userData.getWeight());

		comenzarLocalizacion();
		timer.start();

	}

	/**
	 * @author Romina
	 */
	private void comenzarLocalizacion() {
		// Obtenemos una referencia al LocationManager
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Location loc = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		//Mostramos la última posición conocida
		mostrarPosicion(loc);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				30000, 0, locationListener);
	}

	/**
	 * @param loc
	 * @author Romina
	 */
	private void mostrarPosicion(Progreso progreso) {
		if (progreso != null) {
			lblLatitud.setText( progreso.getLatitude()+"");
			lblLongitud.setText(progreso.getLongitude()+"");
			lblAltitude.setText(progreso.getAltitude()+"");
			lblCalories.setText(calories + " Kcal");
			lblDistance.setText(distance + " mts");
			lblSpeed.setText(speed + " Km/h");
			

		} 
	}
	
	private void mostrarPosicion(Location loc){
		
		if (loc != null) {
			lblLatitud.setText( loc.getLatitude()+"");
			lblLongitud.setText(loc.getLongitude()+"");
			lblAltitude.setText(loc.getAltitude()+"");
		}
		
		lblCalories.setText(0 + " Kcal");
		lblDistance.setText(0 + " mts");
		lblSpeed.setText(0 + " Km/h");
		
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
