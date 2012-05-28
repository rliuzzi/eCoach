package com.caloriecalc.presentation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.beans.Ejercicio;
import com.caloriecalc.lao.LaoEjercicio;
import com.caloriecalc.lao.LaoProgreso;


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
	private LaoProgreso laoProgreso;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_history);

		laoEjercicio = new LaoEjercicio(ExerciseHistoryActivity.this);
		laoProgreso = new LaoProgreso(ExerciseHistoryActivity.this);


		exercises = new ArrayList<Ejercicio>();
		this.adapter = new ExerciseAdapter(this, R.layout.exercise_item, exercises);
		setListAdapter(this.adapter);

		loadExercises = new Runnable() {
			public void run() {
				getExercises();
			}
		};
		Thread thread = new Thread(null, loadExercises, "BackgroundProcess");
		thread.start();
		progressDialog = ProgressDialog.show(ExerciseHistoryActivity.this,
				"Por favor espera...", "Recogiendo datos ...", true);
	}
	
	public void deleteEjercicio (View view){
		
		laoProgreso.deleteProgreso(view.getId());
		laoEjercicio.deleteEjercicio(view.getId());
		
		//Refresh the view
		Intent i = new Intent(ExerciseHistoryActivity.this,
				ExerciseHistoryActivity.class);
		startActivity(i);
		finish();
		
		
		
	}
	
	public void showRoute(View view){
		Intent i = new Intent(ExerciseHistoryActivity.this, MapViewActivity.class);
		//send exercise Id to the Map Activity to retrieve coordinates and draw the route
		i.putExtra("ejercicioId", view.getId());
		startActivity(i);
		
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
				ImageButton typeImg = (ImageButton) v.findViewById(R.id.icon);
				ImageButton trashExercise = (ImageButton) v.findViewById(R.id.ex_trash);
				
				TextView date = (TextView) v.findViewById(R.id.ex_date);
				TextView dist = (TextView) v.findViewById(R.id.ex_distance);
				TextView cal  = (TextView) v.findViewById(R.id.ex_calories);
				
				trashExercise.setId(e.getId());
				typeImg.setId(e.getId());
				
				if (typeImg != null) {
					int type = e.getTipoEjercicio().getTipo();
					switch(type){
					case 0:
						typeImg.setImageResource(com.caloriecalc.R.drawable.walk);
						break;
					case 1:
						typeImg.setImageResource(com.caloriecalc.R.drawable.run);
						break;
					case 2:
						typeImg.setImageResource(com.caloriecalc.R.drawable.skate);
						break;
					case 3:
						typeImg.setImageResource(com.caloriecalc.R.drawable.bike);
						break;
					}
				}
				if (date != null) {
					SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
					String fecha = sdf.format(e.getFechaInicio());
					date.setText("Fecha: " + fecha);
				}
				if (dist != null) {
					dist.setText("Distancia: " + Math.round(e.getDistancia()) + " mts.");
				}
				if (cal  != null){
					cal.setText("Calorias: " + Math.round(e.getCalorias()) + " Kcal");
				}
				
			}
			
			
			
			return v;
		}
	}


}
