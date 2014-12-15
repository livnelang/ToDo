package com.example.todo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class GetFromWebTask extends AsyncTask<URL, Integer, String> {
	
	private Context mainContext;
	private TaskBaseAdapter adp;
	
	public GetFromWebTask(Context context,TaskBaseAdapter adpt) {
        mainContext = context;
        adp = adpt;
    } 

	@Override
	protected String doInBackground(URL... urls) {
		StringBuilder responseBuilder = new StringBuilder();

		try {
		URL urlConnection = new URL(urls[0].toString());
		InputStream in = new
				BufferedInputStream(urlConnection.openStream());
				InputStreamReader inReader = new InputStreamReader(in);
				BufferedReader bufferedReader = new BufferedReader(inReader);
				for (String line=bufferedReader.readLine(); line!=null; line=bufferedReader.readLine()){
					responseBuilder.append(line);
					
					}
		}
		
		catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
				return responseBuilder.toString();
	}
	
	
	@Override
    protected void onPostExecute(String result) 
	{
			/*ListView mListView = (ListView) mainContext. findViewById(R.layout.task_view );
			Log.i("random", result);
			adp.notifyDataSetChanged();*/

	}



	
}