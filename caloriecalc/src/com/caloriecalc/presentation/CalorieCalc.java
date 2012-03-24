package com.caloriecalc.presentation;

import java.util.Calendar;
import java.util.Date;

import com.caloriecalc.R;
import com.caloriecalc.eula.Eula;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CalorieCalc extends Activity {

	private OnClickListener clk_lst_user_settings = new OnClickListener() {

		public void onClick(View v) {
			Intent i = new Intent(CalorieCalc.this, FormUserSettings.class);
			startActivity(i);
		}
	};
	private OnClickListener clk_lst_caminar = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", 0);
			startActivity(i);

		}
	};
	private OnClickListener clk_lst_correr = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", 1);
			startActivity(i);

		}
	};
	private OnClickListener clk_lst_patinar = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", 2);
			startActivity(i);

		}
	};

	private OnClickListener clk_lst_bicicleta = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", 3);
			startActivity(i);

		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selector);
		Eula.show(this);

		Button btn_caminar, btn_correr, btn_patinar, btn_bicicleta, btn_user_settings;

		btn_caminar = (Button) findViewById(R.id.activity_selector_btn_caminar);
		btn_correr = (Button) findViewById(R.id.activity_selector_btn_correr);
		btn_patinar = (Button) findViewById(R.id.activity_selector_btn_patinar);
		btn_bicicleta = (Button) findViewById(R.id.activity_selector_btn_bicicleta);
		btn_user_settings = (Button) findViewById(R.id.activity_selector_btn_config);

		// Iniciar ejercicio intent.putExtra("key","value")...
		btn_caminar.setOnClickListener(clk_lst_caminar);
		btn_correr.setOnClickListener(clk_lst_correr);
		btn_patinar.setOnClickListener(clk_lst_patinar);
		btn_bicicleta.setOnClickListener(clk_lst_bicicleta);

		btn_user_settings.setOnClickListener(clk_lst_user_settings);

/*		DataBaseHelper myDbHelper = new DataBaseHelper(CalorieCalc.this);

		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Imposible crear Base de Datos");

		}

		try {

			myDbHelper.openDataBase();

			// Cursor c = myDbHelper.getEjercicios();
			// while (c.moveToNext()) {
			// Log.d("zzzzzz", c.getString(1));
			// Log.d("zzzzzz", c.getString(2));
			// }

			//
			// for (int i = 0; i < 100; i++) {
			// myDbHelper.LogProgress(1, 32.3 * i, 32.6 * i);
			// }
			//
			// List<Ejercicio> list = myDbHelper.getEjercicios();
			// for (Ejercicio ejercicio : list) {
			// Log.d("zzzzzz", ejercicio.getFechaInicio().toString());
			// }

			// String sql = "SELECT * FROM EjercicioProgreso";
			// Cursor mCursor = myDbHelper.getEjercicio(1);

		} catch (SQLException sqle) {

			throw sqle;

		}
*/
		// Intent i = new Intent(CalorieCalc.this, CalorieCalcResult.class);
		// startActivity(i);

		/*
		 * LocationManager locManager =
		 * (LocationManager)getSystemService(LOCATION_SERVICE); List<String>
		 * listaProviders = locManager.getAllProviders(); LocationProvider
		 * provider = locManager.getProvider(listaProviders.get(0)); Criteria
		 * req = new Criteria(); req.setAccuracy(Criteria.ACCURACY_FINE);
		 * //req.setAltitudeRequired(true);
		 * 
		 * //Mejor proveedor por criterio String mejorProviderCrit =
		 * locManager.getBestProvider(req, false);
		 * 
		 * //Lista de proveedores por criterio List<String> listaProvidersCrit =
		 * locManager.getProviders(req, false);
		 * 
		 * if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
		 * //mostrarAvisoGpsDeshabilitado(); }
		 * 
		 * LocationListener locListener = new LocationListener() {
		 * 
		 * public void onLocationChanged(Location location) {
		 * mostrarPosicion(location); }
		 * 
		 * public void onProviderDisabled(String provider){
		 * lblEstado.setText("Provider OFF"); }
		 * 
		 * public void onProviderEnabled(String provider){
		 * lblEstado.setText("Provider ON"); }
		 * 
		 * public void onStatusChanged(String provider, int status, Bundle
		 * extras){ lblEstado.setText("Provider Status: " + status); } };
		 */

		Date timestamp;

		// Create time stamp from current UTC time
		timestamp = Calendar.getInstance().getTime();
		// System.out.println("TIMESTAMP UTC " + timestamp);

		// Convert time stamp to milliseconds (for saving to DB)
		long ms = timestamp.getTime();
		// System.out.println("date -- > ms " + ms);

		// Convert milliseconds back to time stamp (for reading from DB)
		timestamp = new Date(ms);
		// System.out.println("ms --> date " + timestamp);

		// DBAdapter db = new DBAdapter(this);
		// Calendar c = Calendar.getInstance();
		// String s = c.getTime().toGMTString();
		// ---add 1 GPS coordinate
		// db.open();
		// db.insertPunto(ms, 100, 78, 34);
		// db.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent i;

		switch (item.getItemId()) {

		case R.id.option_settings:
			i = new Intent(CalorieCalc.this, UserSettings.class);
			startActivity(i);
			break;
		case R.id.option_exit:
			System.exit(0);
			break;
		}
		return true;

	}

}
