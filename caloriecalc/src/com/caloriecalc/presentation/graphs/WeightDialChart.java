package com.caloriecalc.presentation.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DialRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.DialRenderer.Type;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class WeightDialChart extends Graph {
	
	private String graphTitle;
	private double minValue;
	private double currentValue;
	private double maxValue;
	
	
	public Intent execute(Context context) {

		CategorySeries category = new CategorySeries(graphTitle);

		// min, max, current weight based on BMI
		category.add("Actual", currentValue);
		category.add("Minimo", minValue);
		category.add("Maximo", maxValue);

		DialRenderer renderer = new DialRenderer();

		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);

		renderer.setMargins(new int[] { 20, 30, 15, 0 });

		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.BLUE);
		renderer.addSeriesRenderer(r);

		r = new SimpleSeriesRenderer();
		r.setColor(Color.rgb(0, 150, 0));
		renderer.addSeriesRenderer(r);

		r = new SimpleSeriesRenderer();
		r.setColor(Color.GREEN);

		renderer.addSeriesRenderer(r);
		renderer.setLabelsTextSize(10);

		renderer.setLabelsColor(Color.WHITE);
		renderer.setShowLabels(true);

		renderer.setVisualTypes(new DialRenderer.Type[] { Type.ARROW,
				Type.NEEDLE, Type.NEEDLE });
		renderer.setMinValue(0);
		renderer.setMaxValue(150);

		return ChartFactory.getDialChartIntent(context, category, renderer,
				graphTitle);
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
	 * @return the minValue
	 */
	public double getMinValue() {
		return minValue;
	}


	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}


	/**
	 * @return the currentValue
	 */
	public double getCurrentValue() {
		return currentValue;
	}


	/**
	 * @param currentValue the currentValue to set
	 */
	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}


	/**
	 * @return the maxValue
	 */
	public double getMaxValue() {
		return maxValue;
	}


	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

}
