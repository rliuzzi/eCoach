package com.caloriecalc;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class UserSettings extends PreferenceActivity {
	
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
		addPreferencesFromResource(R.xml.user_settings);
		
		Preference enableDisableGPSPref = (Preference) findPreference("enableDisableGps");
		enableDisableGPSPref.setOnPreferenceClickListener(gpsStatus);
		
	}
	
}
