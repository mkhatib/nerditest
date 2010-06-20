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
	protected Button startButton;
	protected EditText emailField ;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		try{
			/*	
		app = (Nerditest) getApplication();
		api = new NerditestAPI(app);

		teamField = ((EditText)findViewById(R.id.team_field));
		teamField.setEnabled(false);
		teamField.setHint("Team");

		startButton = ((Button)findViewById(R.id.start_button));
		startButton.setEnabled(false);

		emailField = (EditText)findViewById(R.id.email_field);
			 */
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), "Something wrong happen! StartPageOnCreate0", Toast.LENGTH_SHORT).show();		
		}


	}

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try{
				if(userData == null) {

					waitDialog.dismiss();
					//Toast.makeText(getApplicationContext(), "Something wrong happen! StartPageHandler userData Null", Toast.LENGTH_SHORT).show();
					Intent result = new Intent(getApplication(), ResultPage.class);
					startActivity(result);
					System.out.println();
				} else {
					app.setUserData(userData);
					app.setTeam(userData.getTeam());
					Log.i(TAG, "Number of questions are: " + userData.getQuestions().size());
					teamField.setText(userData.getTeam());
					teamField.refreshDrawableState();
					startButton.setEnabled(true);
					startButton.refreshDrawableState();
					waitDialog.dismiss();
				}
			} catch (Exception ex) {
				//Toast.makeText(getApplicationContext(), "Something wrong happen! StartPageHandler", Toast.LENGTH_SHORT).show();
				waitDialog.dismiss();
				Intent result = new Intent(getApplication(), ResultPage.class);
				startActivity(result);
			}
		};
	};

	/**
	 * Get Request to get the team name
	 * Fillout the team field
	 * Enable Start Button
	 */
	public void checkinUser(View v){
		try {

			Log.i(TAG, "checkinUser");

			// Make a GET request


			Nerditest app = ((Nerditest)getApplication());

			app.setEmail(emailField.getText().toString());

			waitDialog = ProgressDialog.show(this, "Checking in..." , "Please wait...", true);

			new Thread(){
				public void run() {
					userData = api.getUserData();
					// To send a message handler  
					handler.sendEmptyMessage(0);

				}}.start();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), "Something wrong happen! checkinUser", Toast.LENGTH_SHORT).show();
		}




	}

	/**
	 * Get Request to get the questions list for the email address entered
	 * 
	 */
	public void startTest(View v){
		try {

			Log.i(TAG, "startTest");
			//waitDialog = ProgressDialog.show(this, "Saving..." , "Please wait...", true);

			Intent question = new Intent(this, QuestionPage.class);
			startActivity(question);
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), "User already answered all Questions!", Toast.LENGTH_SHORT).show();
		}
	}


}