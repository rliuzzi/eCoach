package com.caloriecalc.presentation;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caloriecalc.R;
import com.caloriecalc.beans.Serie;
import com.caloriecalc.lao.LaoEjercicio;
import com.caloriecalc.presentation.graphs.BarGraph;
import com.caloriecalc.presentation.graphs.LineGraph;
import com.caloriecalc.presentation.graphs.PieGraph;
import com.caloriecalc.presentation.graphs.ScatterGraph;
import com.caloriecalc.presentation.graphs.WeightDialChart;

public class StatsSelectingActivity extends Activity {
	
	LaoEjercicio laoEjercicio;
	List<Serie> serie;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats_selector);
		laoEjercicio = new LaoEjercicio(StatsSelectingActivity.this);
		
	}
	
	
	public void WorkoutByTypeGraphHandler (View view){
		PieGraph pie = new PieGraph();
		Intent pieIntent = pie.getIntent(this);
		startActivity(pieIntent);	
	}
	
	

	
	//line graph 
	public void WeightVsDateGraphHandler (View view){
		LineGraph line = new LineGraph();
		serie = laoEjercicio.getWeightByDate();
		//envio los datos
		line.setSerie(serie);
		//configuracion
		line.setGraphTitle("Peso vs. Fecha");
		line.setxLabel("Fecha");
		line.setyLabel("Peso (Kg)");
		line.setyMaxValue(laoEjercicio.getMaxValueInColumn("Peso")+5);
		
		Intent lineIntent = line.getIntent(this);
		startActivity(lineIntent);
		
	}
	
	//weight dial chart
	public void MinMaxCurWeightGraphHandler (View view){
		WeightDialChart weightDial = new WeightDialChart();
		weightDial.setGraphTitle("Tu rango de peso saludable");
		//TODO Hardcoded
		//Retrieve height/weight from SharedPreferences File
		//Calculate min, max (Utils?)
		weightDial.setMinValue(60.00);
		weightDial.setMaxValue(90.00);
		weightDial.setCurrentValue(75.00);
		Intent weightIntent = weightDial.execute(this);
		startActivity(weightIntent);
		
		
	}
	
	public void TopSpeedsByTypeGraphHandler (View view){
		ScatterGraph scatter = new ScatterGraph();
		Intent scatterIntent = scatter.getIntent(this);
		startActivity(scatterIntent);
	}
	
	public void TopCaloriesVsDateGraphHandler (View view){
		
		BarGraph bar = new BarGraph();
		bar.setGraphTitle("Calorias Totales");
		bar.setxLabel("Ejercicio");
		bar.setyLabel("Calorias");
		bar.setyMaxValue(laoEjercicio.getMaxValueInColumn("Calorias")+5);
		Intent barIntent = bar.getIntent(this);
		startActivity(barIntent);
		
	}

}
