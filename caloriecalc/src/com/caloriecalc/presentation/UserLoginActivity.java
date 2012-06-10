package com.caloriecalc.presentation;

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
import com.caloriecalc.security.Encrypt;

public class UserLoginActivity extends Activity {
	
	private EditText inputUsername;
	private EditText inputPassword;
	private Button btnLogin;
	private Button btnCancel;	
	private TextView linkRegister;
	private TextView linkForgatPassword;
	private TextView errorMsg;
	private SharedPreferences settings;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(null);
        Eula.show(this);
        
        setContentView(R.layout.login_screen);
        
        //Initialize views
        
        inputUsername = (EditText)findViewById(R.id.username);
        inputPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.login_button);
        btnCancel = (Button)findViewById(R.id.cancel_button);
        errorMsg = (TextView)findViewById(R.id.error_msg);
        linkRegister = (TextView)findViewById(R.id.register);
        linkForgatPassword = (TextView) findViewById(R.id.forgat_password);
        
        settings = getSharedPreferences(UserRegistrationActivity.PREFS_NAME, MODE_PRIVATE);
        
        //If already registered hide Register link
        if(settings.getBoolean(UserRegistrationActivity.IS_REGISTERED, false)){
        	linkRegister.setVisibility(View.INVISIBLE);
        }
        
        //If login required show login screen
        if(settings.getBoolean(UserRegistrationActivity.USER_LOGIN_REQUIRED, true)){
	        
	    	btnLogin.setOnClickListener(clickLogin);
	    	
	    	btnCancel.setOnClickListener(clickCancel);
	    	
	    	linkRegister.setOnClickListener(clickRegister);
	    	
	    	linkForgatPassword.setOnClickListener(clickForgatPassword);
        } else {
        	// If login is not required (for instance disabled from personal settings by the user
        	// redirect the user to the next screen
        	
        	Intent i = new Intent(UserLoginActivity.this, CalorieCalc.class);
			startActivity(i);
			finish();
			
        }
    }
	
	
	OnClickListener clickLogin = new OnClickListener() {
		
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
	};
	
	OnClickListener clickCancel = new OnClickListener() {
	    public void onClick(View v) {
	        finish();
	    }
	};
	
	OnClickListener clickForgatPassword = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new Intent(UserLoginActivity.this, ValidateIdentityActivity.class);
			startActivity(i);
			finish();
		}
	};
	
	OnClickListener clickRegister = new OnClickListener() {
		public void onClick(View v) {
			Intent i = new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
			startActivity(i);
			finish();

		}
	};
	

	
}
