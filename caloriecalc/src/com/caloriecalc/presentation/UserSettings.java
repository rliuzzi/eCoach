package com.caloriecalc.presentation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.caloriecalc.R.xml;

public class UserSettings extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(xml.user_settings);
		

		
		
	}

}
