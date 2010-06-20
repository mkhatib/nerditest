package org.nerditest.android;

import java.text.DecimalFormat;

import org.nerditest.api.NerditestAPI;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultPage extends Activity {

	private TextView percentageLabel;
	private NerditestAPI api;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		try{
			percentageLabel = (TextView)findViewById(R.id.nerdity_percentage);
			api = new NerditestAPI(getApplication());
			
			float result = api.getUserResult();
			DecimalFormat twoDForm = new DecimalFormat("#.##");
			String resultSt = Double.valueOf(twoDForm.format(result)).doubleValue()+"%";
			percentageLabel.setText(resultSt);
			
			if (result <= 25) percentageLabel.setTextColor(Color.parseColor("#ff0000"));
			else if (result <= 50) percentageLabel.setTextColor(Color.parseColor("#ff00ff"));
			else if (result <= 75) percentageLabel.setTextColor(Color.parseColor("#ffff00"));
			else percentageLabel.setTextColor(Color.parseColor("#00ff00"));
			
			percentageLabel.refreshDrawableState();
		} catch (Exception ex) {

		}
	}

	public void exitApplication(View v){
		this.moveTaskToBack(true);
	}

}
