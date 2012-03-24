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
import com.caloriecalc.eula.Eula;

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