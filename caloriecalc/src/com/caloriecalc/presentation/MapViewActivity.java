package com.caloriecalc.presentation;

import java.util.List;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.caloriecalc.R;
import com.caloriecalc.beans.LocationItemizedOverlay;
import com.caloriecalc.beans.MapItem;
import com.caloriecalc.lao.LaoProgreso;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class MapViewActivity extends MapActivity {

	private List<Overlay> mapOverlays;

	private Projection projection;

	private List<MapItem> listOfMapItems;

	private LaoProgreso laoProgreso;

	private MapController mapController;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.map_view);

		MapView mapView = (MapView) findViewById(R.id.mapview);

		mapView.setBuiltInZoomControls(true);

		laoProgreso = new LaoProgreso(MapViewActivity.this);

		mapOverlays = mapView.getOverlays();

		mapController = mapView.getController();

		projection = mapView.getProjection();
		
		
		//Retrieve exerciseId
		Intent i = getIntent();
		int ejercicioId = i.getIntExtra("ejercicioId", 0);
		
		//Instantiate list of items to display
		listOfMapItems = laoProgreso.getMapItems(ejercicioId);
		
		//center map
		if (listOfMapItems.size() > 0) {
			mapController.animateTo(listOfMapItems.get(0).getGeoPoint());
			mapController.setZoom(18);
			mapView.invalidate();
		}

		mapOverlays.add(new MyOverlay(listOfMapItems));
		
		//
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
		LocationItemizedOverlay itemizedoverlay = new LocationItemizedOverlay(drawable, this);
		OverlayItem overlayitem;
		GeoPoint sourceGeoPoint = null;
		for (MapItem targetMapItem : listOfMapItems) {
			if (sourceGeoPoint != null) {
				overlayitem = new OverlayItem(sourceGeoPoint, "Tus marcas", targetMapItem.getToast());
				itemizedoverlay.addOverlay(overlayitem);
				mapOverlays.add(itemizedoverlay);
			}
			sourceGeoPoint = targetMapItem.getGeoPoint();
		}
		//
		
		

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	class MyOverlay extends Overlay {

		private List<MapItem> listOfMapItems;

		public MyOverlay(List<MapItem> listOfMapItems) {

			this.listOfMapItems = listOfMapItems;

		}

		public void draw(Canvas canvas, MapView mapv, boolean shadow) {
			super.draw(canvas, mapv, shadow);

			Paint mPaint = new Paint();
			mPaint.setDither(true);
			mPaint.setColor(Color.RED);
			mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(2);

			GeoPoint sourceGeoPoint = null;

			for (MapItem targetMapItem : listOfMapItems) {

				if (sourceGeoPoint != null) {

					Point sourcePoint = new Point();
					Point targetPoint = new Point();

					Path path = new Path();

					projection.toPixels(sourceGeoPoint, sourcePoint);
					projection.toPixels(targetMapItem.getGeoPoint(),
							targetPoint);

					path.moveTo(targetPoint.x, targetPoint.y);
					path.lineTo(sourcePoint.x, sourcePoint.y);

					canvas.drawPath(path, mPaint);

				}

				sourceGeoPoint = targetMapItem.getGeoPoint();
			}

		}



	}

}
