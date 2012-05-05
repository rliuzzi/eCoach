package com.caloriecalc.presentation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.caloriecalc.R;
import com.caloriecalc.lao.Utilities;

/**
 * @author Romina
 * 
 */
public class UserDataEditActivity extends Activity {



	/**
	 * SharedPreference file
	 */
	private SharedPreferences settings;

	/**
	 * input values
	 */
	private EditText inputUsername;
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
			

			int weight = Utilities.kgToGrams(Float.parseFloat(inputWeight
					.getText().toString()));

			int height = Utilities.metersToCm(Float.parseFloat(inputHeight
					.getText().toString()));

			String dob = inputDOB.getText().toString();

			// Editor object to make preference changes.

			SharedPreferences.Editor editor = settings.edit();
			editor.putString(UserRegistrationActivity.USER_NAME, username);


			editor.putString(UserRegistrationActivity.USER_SEX, inputSex);
			editor.putInt(UserRegistrationActivity.USER_WEIGHT, weight);
			editor.putInt(UserRegistrationActivity.USER_HEIGHT, height);

			// validate correct values
			editor.putString(UserRegistrationActivity.USER_DOB, dob);

			// Commit the edits
			editor.commit();

			Intent i = new Intent(UserDataEditActivity.this,
					CalorieCalc.class);
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
		setContentView(R.layout.user_data_edit_screen);
		
		LinearLayout mainLayout=(LinearLayout)this.findViewById(R.id.layout_password);
		mainLayout.setVisibility(LinearLayout.GONE);

		// Initialize views
		inputUsername = (EditText) findViewById(R.id.username);
		inputWeight = (EditText) findViewById(R.id.edit_txt_weight);
		inputHeight = (EditText) findViewById(R.id.edit_txt_height);
		inputDOB = (EditText) findViewById(R.id.dob);

		// Initialize Buttons
		btnSave = (Button) findViewById(R.id.register_button);
		btnCancel = (Button) findViewById(R.id.cancel_button);

		// Initialize the SharedPreferences connection
		settings = getSharedPreferences(UserRegistrationActivity.PREFS_NAME, MODE_PRIVATE);

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

		// Display Stored values
		displaySavedValues();

	}

	public void displaySavedValues() {

		double temp = Utilities.gramsToKg(settings.getInt(UserRegistrationActivity.USER_WEIGHT, 0));
		Double weight = new Double(temp);
		temp = Utilities.cmToMeters(settings.getInt(UserRegistrationActivity.USER_HEIGHT, 0));
		Double height = new Double(temp);

		String radioButtonText = settings.getString(UserRegistrationActivity.USER_SEX, "");

		if (radioButtonText.equals(isMan.getText().toString())) {
			isMan.setChecked(true);
			inputSex = isMan.getText().toString().toLowerCase();
		} else if (radioButtonText.equalsIgnoreCase(isWoman.getText()
				.toString())) {
			isWoman.setChecked(true);
			inputSex = isWoman.getText().toString().toLowerCase();
		}

		inputUsername.setText(settings.getString(UserRegistrationActivity.USER_NAME, null),
				TextView.BufferType.NORMAL);
		inputWeight.setText(weight.toString(), TextView.BufferType.NORMAL);
		inputHeight.setText(height.toString(), TextView.BufferType.NORMAL);
		inputDOB.setText(settings.getString(UserRegistrationActivity.USER_DOB, null),
				TextView.BufferType.NORMAL);

	}

}
