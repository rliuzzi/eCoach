package com.caloriecalc;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;

public class CalorieCalcResult extends Activity {
	
	private DataBaseHelper myDbHelper;	
	private int idEjercicio;
	private TextView calorias;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_page);


		calorias = (TextView)findViewById(R.id.results_page_txt_kcal_quemadas);
		
		myDbHelper = new DataBaseHelper(CalorieCalcResult.this);

		try {
			myDbHelper.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}
		
		Intent i = getIntent();
		idEjercicio = i.getIntExtra("ejercicioId", 0);
		System.out.println("VALOR> " + idEjercicio);
		Ejercicio e = myDbHelper.getEjercicio(idEjercicio);
		String s = String.valueOf(e.getCalorias());
		calorias.setText(s);
		

					
	}


	/**
	 * Enseña el resultado de las calorias quemadas de la pantalla de resultados.
	 */

	private void mostrarResultado() {
			TextView resultado = (TextView) findViewById(R.id.results_page_txt_kcal_quemadas);
			//valor correspondiente
			resultado.setText("");
	}
	
}
