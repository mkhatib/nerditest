package org.nerditest.android;

import org.nerditest.api.NerditestAPI;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartPage extends Activity {

	private String TAG = "StartPage";
	private ProgressDialog waitDialog;
	protected UserData userData;
	protected NerditestAPI api;
	protected Nerditest app;
	protected EditText teamField ;
	protected EditText emailField;
	protected Button startButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Get the Application context
		
		// Create a new API

		// Get the team field, disable it, and sit the hint as Team
		
		// Get The startButton, disable it
		
		// Set Email Field
		
	}

	/**
	 * Get Request to get the team name
	 * Fill out the team field
	 * Enable Start Button
	 */
	public void checkinUser(View v){
		try {
			Log.i(TAG, "checkinUser");
			
			// Save the email state and start a waiting dialog 

			// Call the API method to get user data by starting a new thread
			
		} catch (Exception ex) {
			// Display an error message using Toast
		}
	}

	/**
	 * Get Request to get the questions list for the email address entered
	 * 
	 */
	public void startTest(View v){
		Log.i(TAG, "startTest");			
		// Start the QuestionPage activity
	}
	
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			waitDialog.dismiss();
			try{
				// Check if userData is null or no questions are in userData then you got only results
				// Redirect the user to the result page

				// Else save the userData and the team state and update team field and enable start button

			} catch (Exception ex) {
				// Display an error message 
			}
		};
	};
}