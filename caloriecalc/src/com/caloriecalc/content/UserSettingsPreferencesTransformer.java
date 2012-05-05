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
		
		//HEIGHT:
		
		double height = Utilities.cmToMeters(h);
		
		user.setHeight(height);
		
		//WEIGHT:
		
		double weight = Utilities.gramsToKg(w);
		
		user.setWeight(weight);
		
		//SEX:
		
		user.setSex(s);

		return user;

	}

}
