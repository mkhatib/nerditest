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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionPage extends Activity {

	private String TAG = "QuestionPage";
	private TextView questionNumber;
	private TextView questionBody;
	private Nerditest app;
	private NerditestAPI api;
	private UserData userData;
	private ProgressDialog waitDialog;
	private int currentQuestionNumber;
	private Question currentQuestion;
	private int currentAnswer;
	private RadioButton choice1;
	private RadioButton choice2;
	private RadioButton choice3;
	private RadioButton choice4;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		try {
			// Get the Application context
			
			// Create a new API

			// Get User Data

			// Get the first question and store it in currentQuestion
			
			// Load in the first question in the UI. Question #, body and choices and update the GUI

			
		} catch (Exception ex){ 
			// Display an error message with Toast
		}
	}
	
	public void answerQuestion(View v) {
		try {
			currentQuestionNumber = app.getCurrentQuestion();
			// If nothing checked give an alert
			currentQuestion = app.getUserData().getQuestions().get(currentQuestionNumber);
			currentAnswer = -1;
			int selectedId = ((RadioGroup)findViewById(R.id.answers_group)).getCheckedRadioButtonId();
			switch(selectedId){
			case R.id.choice_1: currentAnswer = 1; break;
			case R.id.choice_2: currentAnswer = 2; break;
			case R.id.choice_3: currentAnswer = 3; break;
			case R.id.choice_4: currentAnswer = 4; break;
			default:
				Log.i(TAG, "No Answer Selected");
				Toast.makeText(getApplicationContext(), "Please Answer the Question!", Toast.LENGTH_SHORT).show();
				return;
			}

			waitDialog = ProgressDialog.show(this, "Saving..." , "Please wait...", true);
			new Thread(){
				@Override
				public void run() {
					api.answerQuestion(currentQuestion.getNumber(), currentAnswer);
					handler.sendEmptyMessage(0);
				}}.start();
		} catch(Exception ex) {
			Toast.makeText(getApplicationContext(), "Something wrong happen! answerQuestion", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			try{
				if(++currentQuestionNumber < app.getUserData().getQuestions().size()) {
					currentQuestion = app.getUserData().getQuestions().get(currentQuestionNumber);

					questionNumber = (TextView) findViewById(R.id.question_number);
					Log.i(TAG, "Question #"+currentQuestion.getNumber());
					questionNumber.setText("Question #"+currentQuestion.getNumber());
					questionNumber.refreshDrawableState();

					questionBody = (TextView) findViewById(R.id.question_body);
					Log.i(TAG, currentQuestion.getBody());
					questionBody.setText(currentQuestion.getBody());
					questionBody.refreshDrawableState();

					choice1 = (RadioButton) findViewById(R.id.choice_1);
					choice2 = (RadioButton) findViewById(R.id.choice_2);
					choice3 = (RadioButton) findViewById(R.id.choice_3);
					choice4 = (RadioButton) findViewById(R.id.choice_4);

					RadioGroup rg = (RadioGroup) findViewById(R.id.answers_group);
					rg.setSelected(false);

					choice1.setChecked(false);
					choice2.setChecked(false);
					choice3.setChecked(false);
					choice4.setChecked(false);

					choice1.setText(currentQuestion.getAnswers()[0]);
					choice2.setText(currentQuestion.getAnswers()[1]);
					choice3.setText(currentQuestion.getAnswers()[2]);
					choice4.setText(currentQuestion.getAnswers()[3]);

					choice1.refreshDrawableState();
					choice2.refreshDrawableState();
					choice3.refreshDrawableState();
					choice4.refreshDrawableState();

					app.setCurrentQuestion(currentQuestionNumber);

					if (currentQuestionNumber == app.getUserData().getQuestions().size() - 1) 
					{
						Button nextButton = (Button) findViewById(R.id.next_button);
						nextButton.setText("Finish");
						nextButton.refreshDrawableState();
					}
				} else {
					Intent result = new Intent(getApplication(), ResultPage.class);
					startActivity(result);
				}
				waitDialog.dismiss();
			} catch (Exception ex) {
				Toast.makeText(getApplicationContext(), "Something wrong happen!", Toast.LENGTH_SHORT).show();
			}
		};
	};
}
