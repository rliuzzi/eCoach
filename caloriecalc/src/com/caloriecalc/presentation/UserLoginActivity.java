package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.beans.Ejercicio.TipoEjercicio;

public class UserLoginActivity extends Activity {
	
	private EditText etUsername;
	private EditText etPassword;
	private Button btnLogin;
	private Button btnCancel;
	private TextView lblResult;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        
        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        lblResult = (TextView)findViewById(R.id.result);
        
    	btnLogin.setOnClickListener(new OnClickListener() {
    		
    	    public void onClick(View v) {
    	        // Check Login
    	        String username = etUsername.getText().toString();
    	        String password = etPassword.getText().toString();
    	         
    	        if(username.equals("guest") && password.equals("guest")){
    	            //lblResult.setText("Login successful.");
    	        	
    	        	Intent i = new Intent(UserLoginActivity.this,
    						CalorieCalc.class);
    				startActivity(i);
    	        	
    	        } else {
    	            lblResult.setText("Login failed. Username and/or password doesn't match.");
    	        }
    	    }
    	});
    	
    	btnCancel.setOnClickListener(new OnClickListener() {
    	  
    	    public void onClick(View v) {
    	        // Close the application
    	        finish();
    	    }
    	});
    }
	


}
