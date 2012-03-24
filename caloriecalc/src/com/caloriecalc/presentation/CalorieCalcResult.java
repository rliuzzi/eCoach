package com.caloriecalc.presentation;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.dao.DaoEjercicio;

public class CalorieCalcResult extends Activity {
	
	private DaoEjercicio myDaoEjercicio;	
	private int idEjercicio;
	private TextView calorias;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_page);


		calorias = (TextView)findViewById(R.id.results_page_txt_kcal_quemadas);
		
		

		try {
			myDaoEjercicio = new DaoEjercicio(CalorieCalcResult.this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent i = getIntent();
		idEjercicio = i.getIntExtra("ejercicioId", 0);
		System.out.println("VALOR> " + idEjercicio);
		Ejercicio e = myDaoEjercicio.getEjercicio(idEjercicio);
		String s = String.valueOf(e.getCalorias());
		calorias.setText(s);
		

					
	}


	/**
	 * Enseña el resultado de las calorias quemadas de la pantalla de resultados.
	 */

/*	private void mostrarResultado() {
			TextView resultado = (TextView) findViewById(R.id.results_page_txt_kcal_quemadas);
			//valor correspondiente
			resultado.setText("");
	}*/
	
}
