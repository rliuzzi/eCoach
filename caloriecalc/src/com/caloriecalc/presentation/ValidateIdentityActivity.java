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

public class ValidateIdentityActivity extends Activity {

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

		showText
				.setText("Contesta tu pregunta secreta para resetear tu password\n"
						+ settings.getString(
								UserRegistrationActivity.USER_HINT,
								"No se ha encontrado una pregunta secreta\n" + "Regístrate!"));

		
		
		buttonNext.setOnClickListener(btnNextPress);

		buttonCancel.setOnClickListener(btnCancelPress);

	}
	
	/**
	 * Form Buttons onClickListener for action next
	 */
	
	OnClickListener btnNextPress = new OnClickListener() {

		public void onClick(View v) {

			// retrieve from edit text box
			String input = inputText.getText().toString().trim().toLowerCase();

			// hash the input response
			String hashedInput = Encrypt.md5Hash(input);

			// Retrieve from shared preference stored

			String storedInput = settings.getString(
					UserRegistrationActivity.USER_HINT_CHECK, "");

			// compare results

			if (Encrypt.EQUAL == Encrypt.compareHash(hashedInput, storedInput)) {
				
				//redirect user to the reset password activity
				Intent i = new Intent(ValidateIdentityActivity.this,
						ResetPasswordActivity.class);
				startActivity(i);
				finish();

			} else {
				errorMsg.setText("La respuesta es incorrecta, vuelve a intentarlo");
			}

		}

	};

	/**
	 * Form Buttons onClickListener for action cancel
	 */
	
	OnClickListener btnCancelPress = new OnClickListener() {

		public void onClick(View v) {
			Intent i = new Intent(ValidateIdentityActivity.this,
					UserLoginActivity.class);
			startActivity(i);
			finish();
		}

	};


}
