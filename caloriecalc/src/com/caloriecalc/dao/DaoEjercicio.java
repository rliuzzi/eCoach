package com.caloriecalc.dao;

import android.content.Context;

public class DaoEjercicio extends DataBaseHelper {
	
	Context myContext;
	
	public DaoEjercicio(Context context){
		super(context);
		this.myContext = context;
	}

}
