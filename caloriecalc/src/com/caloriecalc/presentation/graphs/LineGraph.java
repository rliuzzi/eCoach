package com.caloriecalc.presentation.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;

public class LineGraph {
	
	//attributes

	public Intent getIntent (Context context)
	{
		//TODO define attributes, getters y setters
		
		//create some data MUST have same number of x and y values or the app will crash.
		int[] x = {1,2,3,4,5,6,7,8,9,10};
		int[] y = {10,20,30,40,50,60,70,80,90,100};

		//Create a new time series element
		TimeSeries series = new TimeSeries("Line 1");
		
		for (int i=0; i < x.length; i++)
		{
			series.add(x[i],y[i]);
		}
		
		
		//collect all the series
		XYMultipleSeriesDataset	dataseries = new XYMultipleSeriesDataset();
		dataseries.addSeries(series);
		
		//Render the series, to support multiple series we use XYMultipleSeriesRenderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer);
		
		
		//Finally create an intent
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataseries, 
											mRenderer, "Mi primer grafico");
		
		return intent;
	}

}
