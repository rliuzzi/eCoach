package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.security.Encrypt;

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
	public static final String USER_HINT = "user.hint";
	public static final String USER_HINT_CHECK = "user.hint.check";
	public static final String USER_SEX = "user.sex";
	public static final String USER_WEIGHT = "user.weight";
	public static final String USER_HEIGHT = "user.height";
	public static final String USER_DOB = "user.dob";
	public static final String USER_LOGIN_REQUIRED = "user.login.required";
	public static final String IS_REGISTERED = "user.registered";

	/**
	 * SharedPreference file
	 */
	private SharedPreferences settings;

	/**
	 * input values
	 */
	private EditText inputUsername;
	private EditText inputPassword;
	private EditText inputHint;
	private EditText inputHintCheck;
	private TextView status;

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
			String hint = inputHint.getText().toString();
			String hintCheck = inputHintCheck.getText().toString();

			
			// validate data is not empty
			if (username.length() != 0 && password.length() != 0
					&& hint.length() != 0 && hintCheck.length() != 0) {

				// Editor object to make preference changes.
				SharedPreferences.Editor editor = settings.edit();

				editor.putString(USER_NAME, username);

				// Encrypt password before saving
				editor.putString(USER_PASSWORD, Encrypt.md5Hash(password));

				// Remove extra spaces at the beginning and end of the string
				// and save in lower case letters
				editor.putString(USER_HINT, hint);

				editor.putString(USER_HINT_CHECK, Encrypt.md5Hash(hintCheck.trim().toLowerCase()));
				
				//Set the login screen required to true by default during registration.
				editor.putBoolean(USER_LOGIN_REQUIRED, true);
				
				//Set the user as registered
				editor.putBoolean(IS_REGISTERED, true);

				// Commit the edits
				editor.commit();

				Intent i = new Intent(UserRegistrationActivity.this,
						UserLoginActivity.class);
				startActivity(i);
				finish();
			} else {
				
				//Inform error status and highlight hint texts
				status.setText("Todos los campos son obligatorios",
						TextView.BufferType.NORMAL);

				inputUsername.setHintTextColor(Color.RED);
				inputPassword.setHintTextColor(Color.RED);
				inputHint.setHintTextColor(Color.RED);
				inputHintCheck.setHintTextColor(Color.RED);

			}
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
		setContentView(R.layout.user_registration_screen);

		// Initialize views
		inputUsername = (EditText) findViewById(R.id.username);
		inputPassword = (EditText) findViewById(R.id.password);
		inputHint = (EditText) findViewById(R.id.secret_question);
		inputHintCheck = (EditText) findViewById(R.id.txt_hint);
		status = (TextView) findViewById(R.id.mandatory_fields);

		// Initialize Buttons
		btnSave = (Button) findViewById(R.id.register_button);
		btnCancel = (Button) findViewById(R.id.cancel_button);

		// Initialize the SharedPreferences connection
		settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

		// Handle changes onClick of btnSave
		btnSave.setOnClickListener(btnSavePress);

		// Handle changes onClick of btnCancel
		btnCancel.setOnClickListener(btnCancelPress);

	}

}
