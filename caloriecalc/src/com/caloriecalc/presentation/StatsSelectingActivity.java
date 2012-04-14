package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.caloriecalc.R;
import com.caloriecalc.presentation.graphs.BarGraph;
import com.caloriecalc.presentation.graphs.LineGraph;
import com.caloriecalc.presentation.graphs.PieGraph;
import com.caloriecalc.presentation.graphs.ScatterGraph;

public class StatsSelectingActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats_selector);
		
	}
	
	//TODO Una actividad que va a tener un handler por cada tipo de grafico
	
	public void WorkoutByTypeGraphHandler (View view){
		PieGraph pie = new PieGraph();
		Intent pieIntent = pie.getIntent(this);
		startActivity(pieIntent);
		
	}
	
	public void SpeedVsTimeGraphHandler (View view){
		LineGraph line = new LineGraph();
		Intent lineIntent = line.getIntent(this);
		startActivity(lineIntent);
		
	}
	
	public void WeightVsDateGraphHandler (View view){
		LineGraph line = new LineGraph();
		Intent lineIntent = line.getIntent(this);
		startActivity(lineIntent);
		
	}
	
	public void MinMaxAvgSpeedsGraphHandler (View view){
		BarGraph bar = new BarGraph();
		Intent barIntent = bar.getIntent(this);
		startActivity(barIntent);
		
	}
	
	public void TopSpeedsByTypeGraphHandler (View view){
		ScatterGraph scatter = new ScatterGraph();
		Intent scatterIntent = scatter.getIntent(this);
		startActivity(scatterIntent);
	}
	
	public void TopCaloriesVsDateGraphHandler (View view){
		ScatterGraph scatter = new ScatterGraph();
		Intent scatterIntent = scatter.getIntent(this);
		startActivity(scatterIntent);
		
	}

}
