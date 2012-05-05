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
import com.caloriecalc.content.Eula;

public class UserLoginActivity extends Activity {
	
	private EditText inputUsername;
	private EditText inputPassword;
	private Button btnLogin;
	private Button btnCancel;
	private TextView errorMsg;
	private TextView linkRegister;
	private TextView linkForgatPassword;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(null);
        setContentView(R.layout.login_screen);
        
        Eula.show(this);
        
        inputUsername = (EditText)findViewById(R.id.username);
        inputPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        errorMsg = (TextView)findViewById(R.id.error_msg);
        linkRegister = (TextView)findViewById(R.id.register);
        linkForgatPassword = (TextView) findViewById(R.id.forgat_password);
        
    	btnLogin.setOnClickListener(new OnClickListener() {
    		
    	    public void onClick(View v) {
    	        //retrieve input username and password
    	        String username = inputUsername.getText().toString();
    	        String password = inputPassword.getText().toString();
    	        
    	        //hash the input password
    	        String hashedPassword = Encrypt.md5Hash(password);
    	        
    	        //Retrieve from shared preference stored
    	        final SharedPreferences settings = getSharedPreferences(
						UserRegistrationActivity.PREFS_NAME, MODE_PRIVATE);
    	        
    	        String storedUsername = settings.getString(UserRegistrationActivity.USER_NAME, "");
    	        String storedHashPassword = settings.getString(UserRegistrationActivity.USER_PASSWORD, "");

    			//compare results
    	        
    	        if(username.equals(storedUsername) && Encrypt.EQUAL == Encrypt.compareHash(hashedPassword, storedHashPassword)){
    	        	
    	        	Intent i = new Intent(UserLoginActivity.this, CalorieCalc.class);
    				startActivity(i);
    				finish();
    	        	
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
    	
    	linkForgatPassword.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(UserLoginActivity.this, ValidateIdentityActivity.class);
				startActivity(i);
				

			}
		});
    }
	
	



}
