package com.caloriecalc.beans;


import org.joda.time.DateTime;
import org.joda.time.Years;



public class UserSettings {
	
	private long weight;
	private long height;
	private char sex;
	private int age;
	private DateTime dob;
	
	
	public UserSettings(DateTime dob){
		this.dob = dob;
		if(dob != null)
			setAge(dob);
	}


	/**
	 * @return the weight
	 */
	public long getWeight() {
		return weight;
	}


	/**
	 * @param weight the weight to set
	 */
	public void setWeight(long weight) {
		this.weight = weight;
	}


	/**
	 * @return the height
	 */
	public long getHeight() {
		return height;
	}


	/**
	 * @param height the height to set
	 */
	public void setHeight(long height) {
		this.height = height;
	}


	/**
	 * @return the sex
	 */
	public char getSex() {
		return sex;
	}


	/**
	 * @param sex the sex to set
	 */
	public void setSex(char sex) {
		this.sex = sex;
	}


	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}


	/**
	 * @param age the age to set
	 */
	private void setAge(DateTime dob) {
		 
		DateTime now = new DateTime();
		Years years = Years.yearsBetween(dob, now);
		age = years.getYears();
		
	}


	/**
	 * @return the dob
	 */
	public DateTime getDob() {
		return dob;
	}


	/**
	 * @param dob the dob to set
	 */
	public void setDob(DateTime dob) {
		this.dob = dob;
	}
	
	
	
	

}
