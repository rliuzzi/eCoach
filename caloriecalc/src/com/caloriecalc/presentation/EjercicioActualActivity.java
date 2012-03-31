package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.caloriecalc.lao.LaoEjercicio;
import com.caloriecalc.lao.LaoProgreso;

/**
 * @author Romina
 *
 */
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

	private LaoProgreso laoProgreso;
	private LaoEjercicio laoEjercicio;
	private Ejercicio ejercicio;

	private LocationManager locationManager;

	
	/**
	 * Registrarmos un LocationListener para recibir actualizaciones de la posicion
	 */
	private LocationListener locationListener = new LocationListener() {
		
		public void onLocationChanged(Location location) {
			mostrarPosicion(location);
			laoProgreso.guardarProgreso(ejercicio.getId(),
					location.getLatitude(), location.getLongitude());
		}

		public void onProviderDisabled(String provider) {
			lblEstado.setText("Provider OFF");
		}

		public void onProviderEnabled(String provider) {
			lblEstado.setText("Provider ON ");
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			Log.i("", "Provider Status: " + status);
			lblEstado.setText("Provider Status: " + status);
		}
	};
	
	
	private OnClickListener finalizar =  new OnClickListener() {

		public void onClick(View v) {

			locationManager.removeUpdates(locationListener);

			laoProgreso.finalizarEjercicio(ejercicio);

			Intent i = new Intent(EjercicioActualActivity.this,
					CalorieCalcResult.class);

			// send ejercicioId
			i.putExtra("ejercicioId", ejercicio.getId());

			startActivity(i);
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

		btnFinalizar = (Button) findViewById(R.id.BtnDesactivar);
		btnFinalizar.setOnClickListener(finalizar);

		// Leermos el intent que llamo a esta activdad para averiguar el valor
		// del tipoEjercicio
		// Devuvele -1 si no se ha inicializado en la llamada.

		Intent i = getIntent();
		TipoEjercicio tipoEjercicio = TipoEjercicio.values()[i.getIntExtra(
				"ejercicio", -1)];

		ejercicio = laoEjercicio.crearEjercicio(tipoEjercicio, 123);

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

		if (loc != null) {
			laoProgreso.guardarProgreso(ejercicio.getId(), loc.getLatitude(),
					loc.getLongitude());
		}

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000,
				0, locationListener);
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
							+ String.valueOf(loc.getLongitude())));
		} else {
			lblLatitud.setText("Latitud: (sin_datos)");
			lblLongitud.setText("Longitud: (sin_datos)");
			lblPrecision.setText("Precision: (sin_datos)");
		}
	}

}
