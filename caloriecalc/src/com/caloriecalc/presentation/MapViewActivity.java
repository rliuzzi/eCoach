package com.caloriecalc.presentation;

import java.util.List;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;

import com.caloriecalc.R;
import com.caloriecalc.lao.LaoProgreso;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapViewActivity extends MapActivity {
	
	private List<Overlay> mapOverlays;
	
	private Projection projection;
	
	private List <GeoPoint> listOfGeoPoints;
	
	private LaoProgreso laoProgreso;
	
	private MapController mapController;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.map_view);
		
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    
		mapView.setBuiltInZoomControls(true);
	    
	    laoProgreso = new LaoProgreso(MapViewActivity.this);
	    
	    mapOverlays = mapView.getOverlays();
	    
	    mapController = mapView.getController();
	    
	    projection = mapView.getProjection();
	    
	    //Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
	    //LocationItemizedOverlay itemizedoverlay = new LocationItemizedOverlay(drawable, this);
	    
	    //GeoPoint point = new GeoPoint(19240000,-99120000);
	    //OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
	    
	    //GeoPoint point2 = new GeoPoint(35410000, 139460000);
	   // OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
	    
	    //itemizedoverlay.addOverlay(overlayitem);
	    //itemizedoverlay.addOverlay(overlayitem2);
	    
	    Intent i = getIntent();
	    
	    int ejercicioId = i.getIntExtra("ejercicioId", 0);
	    
	    listOfGeoPoints = laoProgreso.getGeoPoints(ejercicioId);
	    
	    
	    if (listOfGeoPoints.size() > 0){
	    	mapController.animateTo(listOfGeoPoints.get(0));
	    	mapController.setZoom(20);
	    	mapView.invalidate();
	    }
	    
	    mapOverlays.add(new MyOverlay(listOfGeoPoints));
	    
	    //mapOverlays.add(itemizedoverlay);
		
	}
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}

	class MyOverlay extends Overlay{
		
		private List <GeoPoint> listOfGeoPoints;
		
		
	    public MyOverlay(List<GeoPoint> listOfGeoPoints){
	    	
	    	this.listOfGeoPoints = listOfGeoPoints;

	    }   

	    public void draw(Canvas canvas, MapView mapv, boolean shadow){
	        super.draw(canvas, mapv, shadow);

	        Paint   mPaint = new Paint();
	        mPaint.setDither(true);
	        mPaint.setColor(Color.RED);
	        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
	        mPaint.setStrokeJoin(Paint.Join.ROUND);
	        mPaint.setStrokeCap(Paint.Cap.ROUND);
	        mPaint.setStrokeWidth(2);
	        
	        GeoPoint sourceGeoPoint = null;
	        
	        
	        
	        for (GeoPoint targetGeoPoint : listOfGeoPoints){
	        	
	        	if (sourceGeoPoint != null){
		
			        Point sourcePoint = new Point();
			        Point targetPoint = new Point();
			        
			        Path path = new Path();
		
			        projection.toPixels(sourceGeoPoint, sourcePoint);
			        projection.toPixels(targetGeoPoint, targetPoint);
		
			        path.moveTo(targetPoint.x, targetPoint.y);
			        path.lineTo(sourcePoint.x, sourcePoint.y);
		
			        canvas.drawPath(path, mPaint);
	        	}
	        	
	        	sourceGeoPoint = targetGeoPoint;
	        }
	        
	    }
	
	}
	
}


