package org.codelearn.liftapp.tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codelearn.liftapp.LiftListActivity;
import org.codelearn.liftapp.models.Lift;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FetchLiftsTask extends AsyncTask<Void, Void, List<Lift>>{
	
	Context ac;
	ProgressDialog dialog;
	SharedPreferences prefs;
	public FetchLiftsTask(Context activity){
		ac = activity;
		 prefs = activity.getSharedPreferences("codelearn_carpool", Context.MODE_PRIVATE);
		 dialog = new ProgressDialog(activity);
	}
	@Override
	protected List<Lift> doInBackground(Void... args) {
		InputStream inputStream = null;
		final Gson gson = new Gson();
		List<Lift> cp = new ArrayList<Lift>();
		try {
			 
          
            HttpClient httpclient = new DefaultHttpClient();
 
            
            HttpResponse httpResponse = httpclient.execute(new HttpGet("http://codelearn-liftapp.herokuapp.com/api/lifts"));
 
           
            inputStream = httpResponse.getEntity().getContent();
 
            
            if(inputStream != null){
            	 
                 final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 Type listType = new TypeToken<List<Lift>>() {}.getType();
                 cp = gson.fromJson(reader, listType);
            }
            
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return cp;
		
	}

    @Override
    protected void onPreExecute() {
 	   dialog.setMessage("Fetching lifts");
        dialog.show();
    }
	
	@Override
    protected void onPostExecute(List<Lift> result) {
		 if (dialog.isShowing()) {
             dialog.dismiss();
         }
 	   ((LiftListActivity)ac).renderList(result);
    }
}