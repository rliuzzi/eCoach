package com.caloriecalc.presentation.graphs;

import java.text.SimpleDateFormat;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import com.caloriecalc.beans.Serie;
import com.caloriecalc.lao.LaoEjercicio;

public class BarGraph {

	private String graphTitle;
	private String xLabel;
	private String yLabel;
	private double yMaxValue;

	public static final int LENGTH_ZERO = -1;
	public static final int CORRECT_SIZE = 0;

	public Intent getIntent(Context context) {

		LaoEjercicio laoEjercicio = new LaoEjercicio(context);

		List<Serie> data = laoEjercicio.getCaloriesByDate();

		// Render the series, to support multiple series we use
		// XYMultipleSeriesRenderer
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();

		CategorySeries series = new CategorySeries(
				"Calorias quemadas por fecha");
		
		

		for (int i = 0; i < data.size(); i++) {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");

			String fecha = sdf.format(data.get(i).getX());

			series.add( data.get(i).getY());
			
			mRenderer.setXLabels(i+1);
			mRenderer.addXTextLabel(i+1, fecha);
			mRenderer.setXLabelsAngle(90);

		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());

		renderer.setColor(Color.YELLOW);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillBelowLine(true);
		renderer.setFillBelowLineColor(Color.WHITE);
		renderer.setFillPoints(true);

		mRenderer.setChartTitle(graphTitle);
		//mRenderer.setXTitle(xLabel);
		mRenderer.setYTitle(yLabel);
		mRenderer.setXAxisMax(10);
		mRenderer.setYAxisMin(0);
		mRenderer.setYAxisMax(yMaxValue);
		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setLegendTextSize(15);
		mRenderer.setBarSpacing(1);
		mRenderer.setShowGrid(true);
		mRenderer.setGridColor(Color.GRAY);
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setMargins(new int[] { 20, 30, 15, 0 });

		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setPointSize(5f);

		mRenderer.addSeriesRenderer(renderer);

		Intent intent = ChartFactory.getBarChartIntent(context, dataset,
				mRenderer, Type.DEFAULT);

		return intent;

	}

	/**
	 * @return the graphTitle
	 */
	public String getGraphTitle() {
		return graphTitle;
	}

	/**
	 * @param graphTitle
	 *            the graphTitle to set
	 */
	public void setGraphTitle(String graphTitle) {
		this.graphTitle = graphTitle;
	}

	/**
	 * @return the xLabel
	 */
	public String getxLabel() {
		return xLabel;
	}

	/**
	 * @param xLabel
	 *            the xLabel to set
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
	 * @param yLabel
	 *            the yLabel to set
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
	 * @param yMaxValue
	 *            the yMaxValue to set
	 */
	public void setyMaxValue(double yMaxValue) {
		this.yMaxValue = yMaxValue;
	}

}
