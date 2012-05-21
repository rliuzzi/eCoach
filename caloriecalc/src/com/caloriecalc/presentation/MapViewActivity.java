package com.caloriecalc.presentation;

import android.os.Bundle;

import com.caloriecalc.R;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapViewActivity extends MapActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_view);
		
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
		
	}
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
}
