package org.codelearn.liftapp.tasks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codelearn.liftapp.CreateCarpoolActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

public class EditCarpoolTask extends AsyncTask<String,Void,Void>{
	SharedPreferences prefs;
	ProgressDialog dialog;
	Context ac;
	public EditCarpoolTask(Context activity){
		ac = activity;
		dialog = new ProgressDialog(activity);
		prefs = activity.getSharedPreferences("codelearn_carpool", Context.MODE_PRIVATE);
	}
	 @Override
     protected void onPostExecute(Void result) {
  	   if (dialog.isShowing()) {
             dialog.dismiss();
         }
  	   
  	   ((CreateCarpoolActivity)ac).finish();
     }

	@Override
	protected Void doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		 
        String id = prefs.getString("pref_carpool_id", null);
        HttpPut httpPut = new HttpPut("http://codelearn-carpool.herokuapp.com/api/carpools/"+id);
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
