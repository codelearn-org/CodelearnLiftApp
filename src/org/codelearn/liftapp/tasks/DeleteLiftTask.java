package org.codelearn.liftapp.tasks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

public class DeleteLiftTask extends AsyncTask<String, Void, Void> {
	 private ProgressDialog dialog;
	 SharedPreferences prefs;
	 Editor edit;
	 Context ac;
	 public DeleteLiftTask(Context activity) {
		 	ac = activity;
		 	dialog = new ProgressDialog(activity);
	        prefs = activity.getSharedPreferences("codelearn_liftapp", Context.MODE_PRIVATE);
	        edit = prefs.edit();
	 }
	 	@Override
      protected Void doInBackground(String... params) {

	 		HttpClient httpclient = new DefaultHttpClient();

           String id = prefs.getString("pref_lift_id", null);
           HttpDelete httpDelete = new HttpDelete("http://codelearn-liftapp.herokuapp.com/api/lifts/"+id);
			try {
	         httpclient.execute(httpDelete);


			} catch (UnsupportedEncodingException e) {
			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			}

          
          return null;
           
      }

      @Override
      protected void onPostExecute(Void result) {
   	   if (dialog.isShowing()) {
              dialog.dismiss();
          }
   	   edit.putString("pref_lift_id", null);
   	   edit.commit();
   	   
      }

      @Override
      protected void onPreExecute() {
   	   dialog.setMessage("Deleting lift");
          dialog.show();
      }
  }
