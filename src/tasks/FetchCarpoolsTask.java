package tasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import models.Carpool;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.codelearn.carpool.CarpoolListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FetchCarpoolsTask extends AsyncTask<Void, Void, List<Carpool>>{
	
	Context ac;
	ProgressDialog dialog;
	SharedPreferences prefs;
	public FetchCarpoolsTask(Context activity){
		ac = activity;
		 prefs = activity.getSharedPreferences("codelearn_carpool", Context.MODE_PRIVATE);
		 dialog = new ProgressDialog(activity);
	}
	@Override
	protected List<Carpool> doInBackground(Void... args) {
		InputStream inputStream = null;
		final Gson gson = new Gson();
		List<Carpool> cp = new ArrayList<Carpool>();
		try {
			 
          
            HttpClient httpclient = new DefaultHttpClient();
 
            
            HttpResponse httpResponse = httpclient.execute(new HttpGet("http://codelearn-carpool.herokuapp.com/api/carpools"));
 
           
            inputStream = httpResponse.getEntity().getContent();
 
            
            if(inputStream != null){
            	 
                 final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 Type listType = new TypeToken<List<Carpool>>() {}.getType();
                 cp = gson.fromJson(reader, listType);
            }
            String id = prefs.getString("pref_carpool_id", null);
            if(id !=null){
            httpResponse = httpclient.execute(new HttpGet("http://codelearn-carpool.herokuapp.com/api/carpools/"+id));
            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                 final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 Carpool cp0 = gson.fromJson(reader, Carpool.class);
                 cp.add(0, cp0);
            }
			}
            else {
            	Carpool dummy = new Carpool();
            	cp.add(0,dummy);
            }
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return cp;
		
	}

    @Override
    protected void onPreExecute() {
 	   dialog.setMessage("Fetching carpools");
        dialog.show();
    }
	
	@Override
    protected void onPostExecute(List<Carpool> result) {
		 if (dialog.isShowing()) {
             dialog.dismiss();
         }
 	   ((CarpoolListActivity)ac).renderList(result);
    }
}
