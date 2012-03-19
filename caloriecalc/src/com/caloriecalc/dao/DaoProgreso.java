package com.caloriecalc.dao;

import android.content.Context;

public class DaoProgreso extends DataBaseHelper {

	Context myContext;
	
	public DaoProgreso(Context context){
		super(context);
		this.myContext = context;
	}
	
}
