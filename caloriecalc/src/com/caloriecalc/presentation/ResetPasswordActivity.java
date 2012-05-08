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
import com.caloriecalc.security.Encrypt;

public class ResetPasswordActivity extends Activity {

	private SharedPreferences settings;

	private EditText inputText;
	private TextView showText;
	private Button buttonNext;
	private Button buttonCancel;
	private TextView errorMsg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password_screen);

		showText = (TextView) findViewById(R.id.show_hint);
		errorMsg = (TextView) findViewById(R.id.errorMsg);
		inputText = (EditText) findViewById(R.id.editText);
		buttonNext = (Button) findViewById(R.id.btn_next);
		buttonCancel = (Button) findViewById(R.id.btn_cancel);

		settings = getSharedPreferences(UserRegistrationActivity.PREFS_NAME,
				MODE_PRIVATE);

		showText.setText("Introduce tu nueva contraseña");

		buttonNext.setOnClickListener(btnNextPress);

		buttonCancel.setOnClickListener(btnCancelPress);

	}

	/**
	 * Form Buttons onClickListener for action next
	 */

	OnClickListener btnNextPress = new OnClickListener() {

		public void onClick(View v) {

			// retrieve from edit text box
			
			String input = inputText.getText().toString().trim();

			// ensure the field is not null

			if (input != null) {
				
				// hash the input response

				String hashedInput = Encrypt.md5Hash(input);

				// Editor object to make preference changes.
				
				SharedPreferences.Editor editor = settings.edit();

				// Save new password
				
				editor.putString(UserRegistrationActivity.USER_PASSWORD, hashedInput);
				
				// Commit changes!!

				editor.commit();
				
				// Redirect user to the login screen
				
				Intent i = new Intent(ResetPasswordActivity.this,
						UserLoginActivity.class);
				startActivity(i);
				 finish();

			} else {
				errorMsg.setText("El campo password es obligatorio");
			}

		}

	};

	/**
	 * Form Buttons onClickListener for action cancel
	 */

	OnClickListener btnCancelPress = new OnClickListener() {

		public void onClick(View v) {
			
			// Redirect user to the login screen
			
			Intent i = new Intent(ResetPasswordActivity.this,
					UserLoginActivity.class);
			
			startActivity(i);
			finish();
		}

	};

}
