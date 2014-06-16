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

import android.os.AsyncTask;
import android.util.Log;

import com.codelearn.carpool.CarpoolListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FetchCarpoolsTask extends AsyncTask<Void, Void, List<Carpool>>{
	
	CarpoolListActivity ac;
	public FetchCarpoolsTask(CarpoolListActivity activity){
		ac = activity;
	}
	@Override
	protected List<Carpool> doInBackground(Void... args) {
		InputStream inputStream = null;
		List<Carpool> cp = new ArrayList<Carpool>();
		try {
			 
          
            HttpClient httpclient = new DefaultHttpClient();
 
            
            HttpResponse httpResponse = httpclient.execute(new HttpGet("http://codelearn-carpool.herokuapp.com/api/carpools"));
 
           
            inputStream = httpResponse.getEntity().getContent();
 
            
            if(inputStream != null){
            	 final Gson gson = new Gson();
                 final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                 Type listType = new TypeToken<List<Carpool>>() {}.getType();
                 cp = gson.fromJson(reader, listType);
            }
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return cp;
		
	}
	
	@Override
    protected void onPostExecute(List<Carpool> result) {
 	   ac.renderList(result);
    }
}
