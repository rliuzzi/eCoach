package com.caloriecalc.presentation;

import com.caloriecalc.R;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class NetworkSettings extends PreferenceActivity {
	
	OnPreferenceClickListener gpsStatus =
	new OnPreferenceClickListener(){
		public boolean onPreferenceClick(Preference preference){
			startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
			return true;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.network_settings);
		
		Preference enableDisableGPSPref = (Preference) findPreference("enableDisableGps");
		enableDisableGPSPref.setOnPreferenceClickListener(gpsStatus);
		
	}
	
}
