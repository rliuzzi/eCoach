package com.caloriecalc.content;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.caloriecalc.R;
import com.caloriecalc.presentation.UserRegistrationActivity;


public class UserRegistrationPreferencesActivity extends Activity {
	
	public static final String PREFS_NAME = "UserLogin";
	
	public static final String USER_NAME = "user.name";
	public static final String USER_PASSWORD = "user.password";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		
		show(this);
	}

	@Override
	protected void onStop() {
		
		super.onStop();
		
		final SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		
		// Editor object to make preference changes.
		// All objects are from android.context.Context
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(USER_NAME, 123);
		editor.putLong(USER_PASSWORD,  173);
		

		// Commit the edits
		editor.commit();
		
	}
	

    public static void show(final Activity activity) {
    	
    	final SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    
        builder.setTitle(R.string.register_title);
        
        builder.setCancelable(true);
        
        builder.setNegativeButton(R.string.register_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                refuse(activity);
            }
        });
        
        builder.setPositiveButton(R.string.register_next, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                register(activity);
            	
            }
        });
             
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                refuse(activity);
            }
        });
        
        
        builder.setMessage(R.string.register_msg);
        
        builder.create().show();
        
    }
    
    
    
    public static void register(Activity activity){
		Intent i = new Intent(activity,
				UserRegistrationActivity.class);
		activity.startActivity(i);
    }
    
    private static void refuse(Activity activity) {
    	
		activity.finish();
    	
    }
	
}
