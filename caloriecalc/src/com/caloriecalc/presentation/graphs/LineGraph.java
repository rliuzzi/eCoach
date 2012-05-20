package com.caloriecalc.presentation.graphs;

import java.text.SimpleDateFormat;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.caloriecalc.beans.Serie;

public class LineGraph {
	
	
	//attributes
	private String graphTitle;
	private List<Serie> data;
	private String xLabel;
	private String yLabel;
	private double yMaxValue;
	
	public Intent getIntent (Context context)
	{
		
		//The data MUST have same number of x and y values or the app will crash.

		//Create a new time series element
		TimeSeries series = new TimeSeries(graphTitle);
		
		//Render the series, to support multiple series we use XYMultipleSeriesRenderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		
		for (int i=0; i < data.size(); i++)
		{
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM");
			
			String fecha = sdf.format(data.get(i).getX());
			
			series.add(data.get(i).getX().getDay(), data.get(i).getY());
			
			mRenderer.addXTextLabel(i+1, fecha);
			
			
		}
		
		
		//collect all the series
		XYMultipleSeriesDataset	dataseries = new XYMultipleSeriesDataset();
		dataseries.addSeries(series);
		
		
		renderer.setColor(Color.RED);
		renderer.setLineWidth(5);
		renderer.setPointStyle(PointStyle.SQUARE);
		//renderer.setFillBelowLine(false);
		renderer.setFillPoints(true);
		
		mRenderer.setChartTitle(graphTitle);
		//mRenderer.setXTitle(xLabel);
		mRenderer.setYTitle(yLabel);
		mRenderer.setXAxisMax(10);
		mRenderer.setXLabelsAngle(90);
		mRenderer.setYAxisMin(50);
		mRenderer.setYAxisMax(yMaxValue);
		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setLegendTextSize(15);
		mRenderer.setBarSpacing(1);
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setMargins(new int[] {20, 30, 30, 0 });
		mRenderer.setShowGrid(true);

		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setPointSize(5f);

		
		
		
		mRenderer.addSeriesRenderer(renderer);
		
		
		
		
		//Finally create an intent
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataseries, 
											mRenderer, graphTitle);
		
		return intent;
	}






	/**
	 * @return the graphTitle
	 */
	public String getGraphTitle() {
		return graphTitle;
	}



	/**
	 * @param graphTitle the graphTitle to set
	 */
	public void setGraphTitle(String graphTitle) {
		this.graphTitle = graphTitle;
	}






	/**
	 * @return the serie
	 */
	public List<Serie> getSerie() {
		return data;
	}






	/**
	 * @param serie the serie to set
	 */
	public void setSerie(List<Serie> data) {
		this.data = data;
	}






	/**
	 * @return the xLabel
	 */
	public String getxLabel() {
		return xLabel;
	}






	/**
	 * @param xLabel the xLabel to set
	 */
	public void setxLabel(String xLabel) {
		this.xLabel = xLabel;
	}






	/**
	 * @return the yLabel
	 */
	public String getyLabel() {
		return yLabel;
	}






	/**
	 * @param yLabel the yLabel to set
	 */
	public void setyLabel(String yLabel) {
		this.yLabel = yLabel;
	}






	/**
	 * @return the yMaxValue
	 */
	public double getyMaxValue() {
		return yMaxValue;
	}






	/**
	 * @param yMaxValue the yMaxValue to set
	 */
	public void setyMaxValue(double yMaxValue) {
		this.yMaxValue = yMaxValue;
	}

}
