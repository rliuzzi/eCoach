package com.caloriecalc;

//ojo
import android.app.Application;
import android.location.Location;

public class AppData extends Application {
	//ljds

	private static Location currentLocationInfo;
	private static int satellites;

	/**
	 * @param currentLocationInfo
	 *            the latest Location class
	 */
	public static void setCurrentLocationInfo(Location currentLocationInfo) {
		AppData.currentLocationInfo = currentLocationInfo;
	}

	/**
	 * @return the currentLatitude
	 */
	public static double getCurrentLatitude() {
		if (getCurrentLocationInfo() != null) {
			return getCurrentLocationInfo().getLatitude();
		} else {
			return 0;
		}
	}

	/**
	 * Determines whether a valid location is available
	 */
	public static boolean hasValidLocation() {
		return (getCurrentLocationInfo() != null && getCurrentLatitude() != 0 && getCurrentLongitude() != 0);
	}

	/**
	 * @return the currentLongitude
	 */
	public static double getCurrentLongitude() {
		if (getCurrentLocationInfo() != null) {
			return getCurrentLocationInfo().getLongitude();
		} else {
			return 0;
		}
	}

	/**
	 * @return the Location class containing latest lat-long information
	 */
	public static Location getCurrentLocationInfo() {
		return currentLocationInfo;
	}

	/**
	 * @return the number of satellites visible
	 */
	public static int getSatelliteCount() {
		return satellites;
	}

	/**
	 * @param satellites
	 *            sets the number of visible satellites
	 */
	public static void setSatelliteCount(int satellites) {
		AppData.satellites = satellites;
	}

}