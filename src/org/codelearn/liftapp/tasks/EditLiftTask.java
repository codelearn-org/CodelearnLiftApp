package org.codelearn.liftapp.tasks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codelearn.liftapp.CreateLiftActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

public class EditLiftTask extends AsyncTask<String,Void,Void>{
	SharedPreferences prefs;
	ProgressDialog dialog;
	Context ac;
	public EditLiftTask(Context activity){
		ac = activity;
		dialog = new ProgressDialog(activity);
		prefs = activity.getSharedPreferences("codelearn_liftapp", Context.MODE_PRIVATE);
	}
	 @Override
     protected void onPostExecute(Void result) {
  	   if (dialog.isShowing()) {
             dialog.dismiss();
         }
  	   
  	   ((CreateLiftActivity)ac).finish();
     }

	@Override
	protected Void doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		 
        String id = prefs.getString("pref_lift_id", null);
        HttpPut httpPut = new HttpPut("http://codelearn-liftapp.herokuapp.com/api/lifts/"+id);
        StringEntity se;
		try {
			se = new StringEntity(params[0]);
            httpPut.setEntity(se);

            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("Content-type", "application/json");
            httpclient.execute(httpPut);
		} catch (UnsupportedEncodingException e) {
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		return null;
	}
	
	  @Override
      protected void onPreExecute() {
   	   dialog.setMessage("Updating carpool");
          dialog.show();
      }

}
