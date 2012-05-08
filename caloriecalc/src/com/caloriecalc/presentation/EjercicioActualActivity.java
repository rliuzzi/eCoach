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
import com.caloriecalc.beans.UserSettings;
import com.caloriecalc.content.UserSettingsPreferencesTransformer;
import com.caloriecalc.lao.LaoEjercicio;
import com.caloriecalc.lao.LaoProgreso;

/**
 * @author Romina
 * 
 */
public class EjercicioActualActivity extends Activity {

	private Button btnFinalizar;

	private TextView lblLatitud;
	private TextView lblLongitud;
	private TextView lblPrecision;
	private TextView lblEstado;
	private TextView calories;
	private TextView distance;

	private LaoProgreso laoProgreso;
	private LaoEjercicio laoEjercicio;
	private Ejercicio ejercicio;

	private LocationManager locationManager;

	/**
	 * Registrarmos un LocationListener para recibir actualizaciones de la
	 * posicion
	 */
	private LocationListener locationListener = new LocationListener() {

		public void onLocationChanged(Location location) {
			mostrarPosicion(location);
			laoProgreso.guardarProgreso(ejercicio.getId(),
					location.getLatitude(), location.getLongitude(),
					location.getAltitude());
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

			calories.setText(ejercicio.getCalorias().toString());

			distance.setText(ejercicio.getDistancia().toString());

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
		lblPrecision = (TextView) findViewById(R.id.LblPosPrecision);
		lblEstado = (TextView) findViewById(R.id.LblEstado);
		calories = (TextView) findViewById(R.id.calories);
		distance = (TextView) findViewById(R.id.distance);

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
		mostrarPosicion(loc);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				30000, 0, locationListener);
	}

	/**
	 * @param loc
	 * @author Romina
	 */
	private void mostrarPosicion(Location loc) {
		if (loc != null) {
			lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
			lblLongitud.setText("Longitud: "
					+ String.valueOf(loc.getLongitude()));
			lblPrecision.setText("Precision: "
					+ String.valueOf(loc.getAccuracy()));
			Log.i("",
					String.valueOf(loc.getLatitude() + " - "
							+ String.valueOf(loc.getLongitude()))
							+ " - " + String.valueOf(loc.getAltitude()));

		} else {
			lblLatitud.setText("Latitud: (sin_datos)");
			lblLongitud.setText("Longitud: (sin_datos)");
			lblPrecision.setText("Precision: (sin_datos)");
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
