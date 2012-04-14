package com.caloriecalc.presentation.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class ScatterGraph {
	
	public Intent getIntent(Context context){
		
		//data series 1
		int[] x1 = {1,2,3,4,5,6,7,8,9,10};
		double[] values1 = {1.1,2.2,3.3,4.4,5.5,6.6,7.7,8.8,9.9,10.1};
		XYSeries series1 = new XYSeries("Series 1");
		int length1 = x1.length;
		for (int k1=0; k1<length1; k1++){
			series1.add(x1[k1], values1[k1]);
		}
		
		//data series 2
				int[] x2 = {1,2,3,4,5,6,7,8,9,10};
				double[] values2 = {1.5,2.5,3.5,4.5,5.5,6.8,7.8,8.9,9.5,10.5};
				XYSeries series2 = new XYSeries("Series 2");
				int length2 = x2.length;
				for (int k2=0; k2<length2; k2++){
					series2.add(x2[k2], values2[k2]);
		}
		
		XYMultipleSeriesDataset	dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		
		//Customizations 1
		XYSeriesRenderer renderer1 = new XYSeriesRenderer();
		renderer1.setColor(Color.WHITE);
		renderer1.setPointStyle(PointStyle.DIAMOND);
		renderer1.setLineWidth(6);

		//Customizations 2
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.YELLOW);
		renderer2.setPointStyle(PointStyle.SQUARE);
		renderer2.setLineWidth(6);

		
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.addSeriesRenderer(renderer1);
		mRenderer.addSeriesRenderer(renderer2);
		
		Intent intent = ChartFactory.getScatterChartIntent(context, dataset, mRenderer);

		
		return intent;
	}

}
