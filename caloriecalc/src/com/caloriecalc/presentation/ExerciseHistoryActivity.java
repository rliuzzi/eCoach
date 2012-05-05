package com.caloriecalc.presentation;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.lao.LaoEjercicio;

public class ExerciseHistoryActivity extends ListActivity {

	// Small pop up displaying information data is being loaded
	private ProgressDialog progressDialog = null;
	// ArrayList which will hold data acquired from our database
	private ArrayList<Ejercicio> exercises = null;
	// Custom class extending ArrayAdapter
	private ExerciseAdapter adapter;
	// Runnable for loading data in a separate thread
	private Runnable loadExercises;
	// Retrieve Exercise List
	private LaoEjercicio laoEjercicio;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_history);

		laoEjercicio = new LaoEjercicio(ExerciseHistoryActivity.this);

		exercises = new ArrayList<Ejercicio>();
		this.adapter = new ExerciseAdapter(this, R.layout.exercise_item, exercises);
		setListAdapter(this.adapter);

		loadExercises = new Runnable() {
			public void run() {
				getExercises();
			}
		};
		Thread thread = new Thread(null, loadExercises, "MagentoBackground");
		thread.start();
		progressDialog = ProgressDialog.show(ExerciseHistoryActivity.this,
				"Por favor espera...", "Recogiendo datos ...", true);
	}

	private Runnable returnRes = new Runnable() {

		public void run() {
			if (exercises != null && exercises.size() > 0) {
				adapter.notifyDataSetChanged();
				for (int i = 0; i < exercises.size(); i++)
					adapter.add(exercises.get(i));
			}
			progressDialog.dismiss();
			adapter.notifyDataSetChanged();
		}
	};

	private void getExercises() {

		exercises = laoEjercicio.getExercises();

		runOnUiThread(returnRes);
	}

	private class ExerciseAdapter extends ArrayAdapter<Ejercicio> {

		private ArrayList<Ejercicio> items;

		public ExerciseAdapter(Context context, int textViewResourceId,
				ArrayList<Ejercicio> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.exercise_item, null);
			}
			Ejercicio e = items.get(position);
			if (e != null) {
				TextView date = (TextView) v.findViewById(R.id.ex_date);
				TextView type = (TextView) v.findViewById(R.id.ex_type);
				TextView dist = (TextView) v.findViewById(R.id.ex_distance);
				TextView cal  = (TextView) v.findViewById(R.id.ex_calories);
				if (date != null) {
					date.setText("Fecha: " + e.getFechaInicio());
				}
				if (type != null) {
					type.setText("Tipo: " + e.getTipoEjercicio().name());
				}
				if (dist != null) {
					dist.setText("Distancia: " + e.getDistancia());
				}
				if (cal  != null){
					cal.setText("Calorias: " + e.getCalorias());
				}
				
			}
			return v;
		}
	}


}
