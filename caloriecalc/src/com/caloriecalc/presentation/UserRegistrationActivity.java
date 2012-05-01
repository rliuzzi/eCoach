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
	 * input values
	 */
	private EditText inputUsername;
	private EditText inputPassword;
	private String inputSex;
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

		// Initialize Radio Buttons
		RadioButton isMan = (RadioButton) findViewById(R.id.radio_sex_man);
		RadioButton isWoman = (RadioButton) findViewById(R.id.radio_sex_woman);

		// Attach onClickListener to radio buttons
		isMan.setOnClickListener(radio_listener);
		isWoman.setOnClickListener(radio_listener);

		// Handle changes onClick of btnSave
		btnSave.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// retrieve input data in the appropiate format to be persisted

				String username = inputUsername.getText().toString();
				String password = inputPassword.getText().toString();

				int weight = Utilities.kgToGrams(Float.parseFloat(inputWeight
						.getText().toString()));

				int height = Utilities.metersToCm(Float.parseFloat(inputHeight
						.getText().toString()));

				String dob = inputDOB.getText().toString();

				final SharedPreferences settings = getSharedPreferences(
						PREFS_NAME, MODE_PRIVATE);

				// Editor object to make preference changes.

				SharedPreferences.Editor editor = settings.edit();
				editor.putString(USER_NAME, username);

				// encrypt before saving
				// extract to a function
				/**
				 * try { MessageDigest digester =
				 * MessageDigest.getInstance("MD5"); byte[] bytes = new
				 * byte[8192]; int byteCount; while ((byteCount = bytes.length)
				 * > 0) { digester.update(bytes, 0, byteCount); } byte[] digest
				 * = digester.digest(); // finish extract } catch
				 * (NoSuchAlgorithmException e) { // TODO }
				 */

				editor.putString(USER_PASSWORD, Encrypt.md5Hash(password));

				editor.putString(USER_SEX, inputSex);
				editor.putInt(USER_WEIGHT, weight);
				editor.putInt(USER_HEIGHT, height);
				editor.putString(USER_DOB, dob);

				// Commit the edits
				editor.commit();

				Intent i = new Intent(UserRegistrationActivity.this,
						UserLoginActivity.class);
				startActivity(i);

			}
		});

		// Handle changes onClick of btnCancel
		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

	}



}
