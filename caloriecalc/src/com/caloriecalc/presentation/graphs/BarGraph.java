package com.caloriecalc.presentation.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;

public class BarGraph {
	
	public Intent getIntent(Context context){
		
		int[] y = {111,121,321,123,121,242,121,143,163,175,200,187};
		
		CategorySeries series = new CategorySeries("Bar Graph Test");
		
		for(int i=0; i<y.length; i++){
			series.add("Bar " + (i+1), y[i]);
		}
		
		XYMultipleSeriesDataset	dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		
		//Render the series, to support multiple series we use XYMultipleSeriesRenderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		
		Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);
		
		return intent;
		
	}
	
}
