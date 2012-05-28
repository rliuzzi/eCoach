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
		
		//HEIGHT
		
		user.setHeight(transformHeight(h));
		
		//WEIGHT
		
		user.setWeight(transformWeight(w));
		
		//SEX: the input is already limited to allowed strings
		
		user.setSex(s);

		return user;

	}
	
	public static UserSettings getUserSettings(int h, int w) {
		
		
		UserSettings user = new UserSettings();
				
		user.setHeight(transformHeight(h));
		
		user.setWeight(transformWeight(w));
		
		return user;
		
	}
	
	//WEIGHT: convert from integer grams to double Kg
	
	private static double transformWeight(int weight){
		
		return Utilities.gramsToKg(weight);
	
	}
	
	//HEIGHT: convert from integer cm to double meters
	
	private static double transformHeight (int height){
		
		return Utilities.cmToMeters(height);
		
	}
	

}
