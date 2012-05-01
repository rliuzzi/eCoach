package com.caloriecalc.presentation;

import security.Encrypt;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caloriecalc.R;

public class UserLoginActivity extends Activity {
	
	private EditText inputUsername;
	private EditText inputPassword;
	private Button btnLogin;
	private Button btnCancel;
	private TextView errorMsg;
	private TextView linkRegister;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        
        inputUsername = (EditText)findViewById(R.id.username);
        inputPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        errorMsg = (TextView)findViewById(R.id.error_msg);
        linkRegister = (TextView)findViewById(R.id.register);
        
        
    	btnLogin.setOnClickListener(new OnClickListener() {
    		
    	    public void onClick(View v) {
    	        //retrieve input username and password
    	        String username = inputUsername.getText().toString();
    	        String password = inputPassword.getText().toString();
    	         
    	        //encrypt before saving
    	        //Retrieve from shared preference stored
    	        //compare results
    	        final SharedPreferences settings = getSharedPreferences(
						UserRegistrationActivity.PREFS_NAME, MODE_PRIVATE);
    	              
    	        if(username.equals(settings.getString(UserRegistrationActivity.USER_NAME, "")) && (Encrypt.md5Hash(password)).equals(settings.getString(UserRegistrationActivity.USER_PASSWORD, ""))){
    	        	
    	        	Intent i = new Intent(UserLoginActivity.this, CalorieCalc.class);
    				startActivity(i);
    	        	
    	        } else {
    	        	errorMsg.setText("El username y/o password es incorrecto");
    	        }
    	    }
    	});
    	
    	btnCancel.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v) {
    	        finish();
    	    }
    	});
    	
    	linkRegister.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
				startActivity(i);

			}
		});
    }


}
