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
import com.caloriecalc.lao.Utilities;

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
		this.adapter = new ExerciseAdapter(this, R.layout.exercise_item,
				exercises);
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

	public void deleteEjercicio(View view) {

		laoProgreso.deleteProgreso(view.getId());
		laoEjercicio.deleteEjercicio(view.getId());

		// Refresh the view
		Intent i = new Intent(ExerciseHistoryActivity.this,
				ExerciseHistoryActivity.class);
		startActivity(i);
		finish();

	}

	public void showRoute(View view) {
		Intent i = new Intent(ExerciseHistoryActivity.this,
				MapViewActivity.class);
		// send exercise Id to the Map Activity to retrieve coordinates and draw
		// the route
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
			ViewHolder holder;

			if (v == null) {

				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.exercise_item, null);

				// cache view fields into the holder
				holder = new ViewHolder();

				holder.typeImg = (ImageButton) v.findViewById(R.id.icon);
				holder.trashExercise = (ImageButton) v
						.findViewById(R.id.ex_trash);

				holder.date = (TextView) v.findViewById(R.id.ex_date);
				holder.dist = (TextView) v.findViewById(R.id.ex_distance);
				holder.cal = (TextView) v.findViewById(R.id.ex_calories);

				v.setTag(holder);

				

			} else {

				holder = (ViewHolder) v.getTag();

			}
			
			Ejercicio e = items.get(position);

			if (e != null) {

				holder.trashExercise.setId(e.getId());
				holder.typeImg.setId(e.getId());


				switch (e.getTipoEjercicio().getTipo()) {
					case 0:
						holder.typeImg
								.setImageResource(com.caloriecalc.R.drawable.walk);
						break;
					case 1:
						holder.typeImg
								.setImageResource(com.caloriecalc.R.drawable.run);
						break;
					case 2:
						holder.typeImg
								.setImageResource(com.caloriecalc.R.drawable.skate);
						break;
					case 3:
						holder.typeImg
								.setImageResource(com.caloriecalc.R.drawable.bike);
						break;
				}

				holder.date.setText("Fecha: " + Utilities.getFormattedDate(e.getFechaInicio()));

				holder.dist.setText("Distancia: "
						+ Math.round(e.getDistancia()) + " mts.");

				holder.cal.setText("Calorias: "
						+ Math.round(e.getCalorias()) + " Kcal");

			}

			return v;
		}
	}

	static class ViewHolder {
		ImageButton typeImg;
		ImageButton trashExercise;
		TextView date;
		TextView dist;
		TextView cal;
	}

}
