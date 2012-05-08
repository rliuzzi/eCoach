package com.caloriecalc.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public final class Encrypt {

	public static final int NOT_EQUAL = 0;
	public static final int EQUAL = 1;
	
	
	public static String md5Hash(String s) {
		MessageDigest m = null;

		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		m.update(s.getBytes(), 0, s.length());
		String hash = new BigInteger(1, m.digest()).toString(16);
		return hash;
	}
	
	public static int compareHash(String input, String expected){
		if(input.compareTo(expected)==0){
			return EQUAL;
		}else{
			return NOT_EQUAL;
		}
	}
	
	
	
}
