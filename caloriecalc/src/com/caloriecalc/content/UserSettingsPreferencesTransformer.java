package com.caloriecalc.content;

import java.text.ParseException;
import java.util.Date;

import com.caloriecalc.beans.UserSettings;
import com.caloriecalc.lao.Utilities;

public final class UserSettingsPreferencesTransformer {

	// kind of a daoUserSettings

	// only one user at the time in file, there is no need to pass on an id parameter.

	public static UserSettings getUserSettings(String d, String s, int h, int w) {
		
		
		UserSettings user = new UserSettings();
		Date dob = null;
		
		// DOB: String formated dd/mm/yyyy
		
		try{
			dob  = Utilities.parseDate(d);
		} catch (ParseException e){
			//TODO Invalid date format
		}
	
		if (dob != null){	
			user.setDob(dob);
		}
		
		//HEIGHT: convert from integer cm to double meters
		
		double height = Utilities.cmToMeters(h);
		
		user.setHeight(height);
		
		//WEIGHT: convert from integer grams to double Kg
		
		double weight = Utilities.gramsToKg(w);
		
		user.setWeight(weight);
		
		//SEX: the input is already limited to allowed strings
		
		user.setSex(s);

		return user;

	}

}
