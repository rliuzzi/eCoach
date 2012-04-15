package com.caloriecalc.test.lao;

import com.caloriecalc.lao.Utilities;


import junit.framework.TestCase;

public class UtilitiesTest extends TestCase {

	public void testMetersToKm() {

		Utilities utilils = new Utilities();
		double actual = utilils.metersToKm(1000);
		assertEquals(1, actual, 0.01);
	}

	public void testKmToMeters() {
		fail("Not yet implemented"); // TODO
	}

	public void testSecondsToHours() {
		fail("Not yet implemented"); // TODO
	}

}
