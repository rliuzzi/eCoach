package com.caloriecalc.presentation;


import android.os.Bundle;
import android.preference.PreferenceActivity;


import com.caloriecalc.R.xml;

public class UserSettings extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(xml.user_settings);
		

		
		
	}

}
