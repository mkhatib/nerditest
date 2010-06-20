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
			// TODO Get the Application context
			
			// TODO Create a new API

			// TODO Get User Data

			// TODO Get the first question and store it in currentQuestion
			
			// TODO Load in the first question in the UI. Question #, body and choices and update the GUI

			
		} catch (Exception ex){ 
			// TODO Display an error message with Toast
		}
	}
	
	public void answerQuestion(View v) {
		try {
			// TODO Get Current Question State and its number
			
			// TODO Check what answer is selected if nothing is selected Toast it up and return
			

			// TODO Start a progress dialog to indecate that you're saving the answer 

			/* TODO 
			 * Start a new thread to send a request for answering question through the API
			 * Once it is done you should notify questionHasBeenAnswered handler so it can 
			 * load the next question (if any) and dismiss the progress dialog. If that was 
			 * The last question, then it would load the result page.
			 */
			
		} catch(Exception ex) {
			Toast.makeText(getApplicationContext(), "Something wrong happen! answerQuestion", Toast.LENGTH_SHORT).show();
		}
	}
	
	
	private Handler questionHasBeenAnswered = new Handler(){
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
					// TODO Display the ResultPage activity
					
				}
				waitDialog.dismiss();
			} catch (Exception ex) {
				Toast.makeText(getApplicationContext(), "Something wrong happen!", Toast.LENGTH_SHORT).show();
			}
		};
	};
}
