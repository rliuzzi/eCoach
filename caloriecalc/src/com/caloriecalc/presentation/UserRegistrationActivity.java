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
import android.widget.RadioButton;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.lao.Utilities;

/**
 * @author Romina
 * 
 */
public class UserRegistrationActivity extends Activity {

	/**
	 * Constant to save the sharedPreferences file
	 */
	public static final String PREFS_NAME = "UserData";

	/**
	 * Constants to define the key values mapped
	 */
	public static final String USER_NAME = "user.name";
	public static final String USER_PASSWORD = "user.password";
	public static final String USER_SEX = "user.sex";
	public static final String USER_WEIGHT = "user.weight";
	public static final String USER_HEIGHT = "user.height";
	public static final String USER_DOB = "user.dob";
	
	
	/**
	 * SharedPreference file 
	 */
	private SharedPreferences settings;
	
	/**
	 * input values
	 */
	private EditText inputUsername;
	private EditText inputPassword;
	private String inputSex;
	private RadioButton isMan;
	private RadioButton isWoman;
	private EditText inputWeight;
	private EditText inputHeight;
	private EditText inputDOB;

	/**
	 * onClicListener to handle radioButton changes
	 */
	OnClickListener radio_listener = new OnClickListener() {
		public void onClick(View view) {
			RadioButton rb = (RadioButton) view;
			inputSex = rb.getText().toString().toLowerCase();
		}
	};

	/**
	 * Form Buttons
	 */
	private Button btnSave;
	private Button btnCancel;
	
	
	
	/**
	 * Form Buttons onClickListener for save action 
	 */
	OnClickListener btnSavePress = new OnClickListener() {

		public void onClick(View v) {

			// retrieve input data in the appropriate format to be persisted

			String username = inputUsername.getText().toString();
			String password = inputPassword.getText().toString();

			int weight = Utilities.kgToGrams(Float.parseFloat(inputWeight
					.getText().toString()));

			int height = Utilities.metersToCm(Float.parseFloat(inputHeight
					.getText().toString()));

			String dob = inputDOB.getText().toString();



			// Editor object to make preference changes.

			SharedPreferences.Editor editor = settings.edit();
			editor.putString(USER_NAME, username);
			
			//Encrypt password before saving
			editor.putString(USER_PASSWORD, Encrypt.md5Hash(password));

			editor.putString(USER_SEX, inputSex);
			editor.putInt(USER_WEIGHT, weight);
			editor.putInt(USER_HEIGHT, height);
			
			//validate correct values
			editor.putString(USER_DOB, dob);

			// Commit the edits
			editor.commit();

			Intent i = new Intent(UserRegistrationActivity.this,
					UserLoginActivity.class);
			startActivity(i);

		}
	};
	
	/**
	 * Form Buttons onClickListener for cancel action 
	 */
	OnClickListener btnCancelPress = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);

		// Initialize views
		inputUsername = (EditText) findViewById(R.id.username);
		inputPassword = (EditText) findViewById(R.id.password);
		inputWeight = (EditText) findViewById(R.id.edit_txt_weight);
		inputHeight = (EditText) findViewById(R.id.edit_txt_height);
		inputDOB = (EditText) findViewById(R.id.dob);

		// Initialize Buttons
		btnSave = (Button) findViewById(R.id.register_button);
		btnCancel = (Button) findViewById(R.id.cancel_button);

		//Initialize the SharedPreferences connection
		settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

		// Initialize Radio Buttons
		isMan = (RadioButton) findViewById(R.id.radio_sex_man);
		isWoman = (RadioButton) findViewById(R.id.radio_sex_woman);

		// Attach onClickListener to radio buttons
		isMan.setOnClickListener(radio_listener);
		isWoman.setOnClickListener(radio_listener);

		// Handle changes onClick of btnSave
		btnSave.setOnClickListener(btnSavePress);

		// Handle changes onClick of btnCancel
		btnCancel.setOnClickListener(btnCancelPress);
		
		//Display Stored values
		displaySavedValues();

	}

	public void displaySavedValues(){
		
		
		
		
		
		double temp = Utilities.gramsToKg(settings.getInt(USER_WEIGHT, 0));
		Double weight = new Double(temp);
		temp = Utilities.cmToMeters(settings.getInt(USER_HEIGHT, 0));
		Double height = new Double(temp);
		
		String radioButtonText = settings.getString(USER_SEX, "");
		
		if(radioButtonText.equalsIgnoreCase(isMan.getText().toString())){
			isMan.setChecked(true);
		}else if (radioButtonText.equalsIgnoreCase(isWoman.getText().toString())){
			isWoman.setChecked(true);
		}
		
		
		inputUsername.setText(settings.getString(USER_NAME, null), TextView.BufferType.NORMAL);
		inputPassword.setText(settings.getString(USER_PASSWORD, null), TextView.BufferType.NORMAL);
		inputWeight.setText(weight.toString(), TextView.BufferType.NORMAL);
		inputHeight.setText(height.toString(), TextView.BufferType.NORMAL);
		inputDOB.setText(settings.getString(USER_DOB, null), TextView.BufferType.NORMAL);
		
		
	}

}
