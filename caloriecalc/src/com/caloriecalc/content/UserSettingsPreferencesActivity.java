package com.caloriecalc.content;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.caloriecalc.R;


public class UserSettingsPreferencesActivity extends Activity {
	
	public static final String PREFS_NAME = "UserSettings";
	
	public static final String USER_NAME = "user.name";
	public static final String USER_PASSWORD = "user.password";
	public static final String USER_WEIGHT = "user.weight";
	public static final String USER_HEIGHT = "user.height";
	public static final String USER_SEX = "user.sex";
	public static final String USER_AGE = "user.age";
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selector);

		// Restore preferences
		
		//long weight = settings.getLong(USER_WEIGHT, 0);
		//setSilent(silent);
		
		show(this);
	}

	@Override
	protected void onStop() {
		
		super.onStop();
		
		final SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
		
		// We need an Editor object to make preference changes.
		// All objects are from android.context.Context
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(USER_WEIGHT, 123);
		editor.putLong(USER_HEIGHT,  173);
		editor.putString(USER_SEX, "M");
		editor.putInt(USER_AGE, 26);

		// Commit the edits!
		editor.commit();
		
	}
	

    public static void show(final Activity activity) {
    	
    	final SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    
        builder.setTitle(R.string.user_settings_title);
        
        builder.setCancelable(true);
        
        
        builder.setPositiveButton(R.string.user_settings_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dataValid(dialog);
            	
            }
        });
        
        builder.setNegativeButton(R.string.user_settings_ko, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                modify(activity);
            }
        });
        
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                dataValid(dialog);
            }
        });
        
        
        builder.setMessage("\nPeso: " + settings.getLong(USER_WEIGHT, 0)
								        + "\nAltura: " + settings.getLong(USER_HEIGHT, 0)
								        + "\nSexo: " + settings.getString(USER_SEX, "H")
								        + "\nEdad: " + settings.getInt(USER_AGE, 0));
        
        
        
        builder.create().show();
        
    

    }
    
    
    
    public static void modify(Activity activity){
    	
    	final SharedPreferences settings = activity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    	
		// We need an Editor object to make preference changes.
		// All objects are from android.context.Context
		
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(USER_WEIGHT, 123);
		editor.putLong(USER_HEIGHT,  173);
		editor.putString(USER_SEX, "M");
		editor.putInt(USER_AGE, 26);

		// Commit the edits!
		editor.commit();
    }
    
    private static void dataValid(DialogInterface dialog) {
    	
			dialog.dismiss();
		
		
    	
    }
	
}
