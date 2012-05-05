package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.caloriecalc.R;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;

public class CalorieCalc extends Activity {

	private OnClickListener clk_lst_caminar = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", TipoEjercicio.CAMINAR.ordinal());
			startActivity(i);

		}
	};

	private OnClickListener clk_lst_correr = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", TipoEjercicio.CORRER.ordinal());
			startActivity(i);

		}
	};

	private OnClickListener clk_lst_patinar = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", TipoEjercicio.PATINAR.ordinal());
			startActivity(i);

		}
	};

	private OnClickListener clk_lst_bicicleta = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					EjercicioActualActivity.class);
			i.putExtra("ejercicio", TipoEjercicio.BICICLETA.ordinal());
			startActivity(i);

		}
	};

	private OnClickListener clk_stats_btn = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					StatsSelectingActivity.class);
			startActivity(i);

		}

	};

	private OnClickListener clk_history_btn = new OnClickListener() {

		public void onClick(View v) {

			Intent i = new Intent(CalorieCalc.this,
					ExerciseHistoryActivity.class);
			startActivity(i);

		}

	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selector);
		
		

		Button btn_caminar, btn_correr, btn_patinar, btn_bicicleta, btn_stats, btn_history;

		btn_caminar = (Button) findViewById(R.id.activity_selector_btn_caminar);
		btn_correr = (Button) findViewById(R.id.activity_selector_btn_correr);
		btn_patinar = (Button) findViewById(R.id.activity_selector_btn_patinar);
		btn_bicicleta = (Button) findViewById(R.id.activity_selector_btn_bicicleta);

		btn_stats = (Button) findViewById(R.id.btn_stats);
		btn_history = (Button) findViewById(R.id.btn_history);


		// Iniciar ejercicio intent.putExtra("key","value")...
		btn_caminar.setOnClickListener(clk_lst_caminar);
		btn_correr.setOnClickListener(clk_lst_correr);
		btn_patinar.setOnClickListener(clk_lst_patinar);
		btn_bicicleta.setOnClickListener(clk_lst_bicicleta);

		btn_stats.setOnClickListener(clk_stats_btn);
		btn_history.setOnClickListener(clk_history_btn);



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Intent i;

		switch (item.getItemId()) {

		case R.id.option_settings:
			i = new Intent(CalorieCalc.this, NetworkSettings.class);
			startActivity(i);
			break;
		case R.id.option_exit:
			finish();
			break;
		case R.id.option_personal:
			i = new Intent(CalorieCalc.this, UserDataEditActivity.class);
			startActivity(i);
		}

		return true;

	}

}
