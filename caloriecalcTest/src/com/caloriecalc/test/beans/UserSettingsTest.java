package com.caloriecalc.test.beans;

import junit.framework.TestCase;

import org.joda.time.DateTime;

import com.caloriecalc.beans.UserSettings;

public class UserSettingsTest extends TestCase {

	public void testGetAge() {
		
		UserSettings user1 = new UserSettings(new DateTime(1985,9,12,00,00,00));
		int result = user1.getAge();
		assertEquals(26, result);
		
	}

	public void testGetDob() {
		UserSettings user2 = new UserSettings(new DateTime(1985,9,12,00,00,00));
		DateTime expected = new DateTime (1985,9,12,00,00,00);
		DateTime actual = user2.getDob();
		assertEquals(expected, actual);
		
		
	}

	

}
