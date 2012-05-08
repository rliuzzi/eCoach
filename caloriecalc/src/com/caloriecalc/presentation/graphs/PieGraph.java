package com.caloriecalc.presentation.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.caloriecalc.beans.Ejercicio.TipoEjercicio;
import com.caloriecalc.lao.LaoEjercicio;

public class PieGraph {

	public Intent getIntent(Context context){
		
		LaoEjercicio laoEjercicio = new LaoEjercicio(context);
		
		
		CategorySeries series = new CategorySeries("Grafico de pastel");
		
		//FIXME Index out of bounds exception
		//Check length match
		//Check renderer and series are not null
		series.add(TipoEjercicio.CAMINAR.name(), laoEjercicio.getCountExercises(TipoEjercicio.CAMINAR.ordinal()));
		series.add(TipoEjercicio.CORRER.name(), laoEjercicio.getCountExercises(TipoEjercicio.CORRER.ordinal()));
		series.add(TipoEjercicio.PATINAR.name(), laoEjercicio.getCountExercises(TipoEjercicio.PATINAR.ordinal()));
		series.add(TipoEjercicio.BICICLETA.name(), laoEjercicio.getCountExercises(TipoEjercicio.BICICLETA.ordinal()));
		
		int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA};
		
		DefaultRenderer renderer = new DefaultRenderer();
		
		for (int color: colors){
			
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		
		renderer.setChartTitle("Ejercicios por tipo");
		renderer.setChartTitleTextSize(20);
		renderer.setZoomButtonsVisible(true);
		
		Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Pie");
		
		return intent;
	
	
	}
}
