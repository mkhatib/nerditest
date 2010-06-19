package org.nerditest.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.nerditest.android.Nerditest;
import org.nerditest.android.UserData;

import com.google.gson.Gson;

import android.app.Application;
import android.util.Log;

public class NerditestAPI {

	private Nerditest context;

	private String API_SERVER = "http://nerditest.appspot.com/";
	
	public NerditestAPI(Application context) {
		this.context = (Nerditest) context;
	}

	public Nerditest getContext() {
		return context;
	}

	public void setContext(Nerditest context) {
		this.context = context;
	}

	public UserData getUserData() {
		return (UserData) runJSONParser(API_SERVER+"user/"+context.getEmail(), UserData.class);
	}

	public void answerQuestion(int question_number, int answer_number) {
		String url = API_SERVER+"teams/"+context.getTeam()+ "/members/"+context.getEmail()+"/questions/" + question_number+"/answer/"+answer_number;
		post(url,null);
	}

	public InputStream get(String url){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		URI uri;
		InputStream data = null;
		try {
			uri = new URI(url);
			HttpGet method = new HttpGet(uri);
			HttpResponse response = httpClient.execute(method);
			data = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Object runJSONParser(String url, Class cls){
		try{
			Log.i("runJSONParser", "Json Parser started..");
			Gson gson = new Gson();
			Reader r = new InputStreamReader(get(url));
			Log.i("runJSONParser", r.toString());
			Object objs = gson.fromJson(r, cls);
			Log.i("runJSONParser", objs.toString());
			return objs;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	public void post(String url, List<NameValuePair> params ){
		try {
			HttpClient client = new DefaultHttpClient();  
			HttpPost post = new HttpPost(url); 
			HttpResponse responsePOST = client.execute(post);  
			HttpEntity resEntity = responsePOST.getEntity();  
			if (resEntity != null) {    
				Log.i("NerditestAPI",EntityUtils.toString(resEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public float getUserResult() {
		String data = "";
		try {
			data = (convertStreamToString(get(API_SERVER+"result/"+context.getEmail())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Log.i("NerditestAPI", data);
		return Float.parseFloat(data);
	}


	public String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the BufferedReader.readLine()
		 * method. We iterate until the BufferedReader return null which means
		 * there's no more data to read. Each line will appended to a StringBuilder
		 * and returned as String.
		 */
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {        
			return "";
		}
	}

}
