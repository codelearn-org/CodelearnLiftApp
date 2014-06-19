package org.codelearn.liftapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button _skip;
	Button _create;
	Editor edit;
	SharedPreferences prefs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// prefs =  getSharedPreferences("codelearn_carpool", MODE_PRIVATE);
		// edit = prefs.edit();
		 // Boolean firstrun = prefs.getBoolean("pref_first_run", true);
		  /*if(!firstrun){
			  Intent myIntent = new Intent(MainActivity.this, CarpoolListActivity.class);
				startActivity(myIntent);
		  };*/
			  
		  _skip = (Button) findViewById(R.id.skip);
		  _create = (Button) findViewById(R.id.create);
		  
		  _create.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//edit.putBoolean("pref_first_run", false);
				//edit.commit();
				Intent myIntent = new Intent(MainActivity.this, CreateCarpoolActivity.class);
				startActivity(myIntent);
			}});
		  
		  _skip.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					//edit.putBoolean("pref_first_run", false);
					//edit.commit();
					Intent myIntent = new Intent(MainActivity.this, CarpoolListActivity.class);
					startActivity(myIntent);
					
				}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override 
	public void onResume(){
		super.onResume();
		/*Boolean firstrun = prefs.getBoolean("pref_first_run", true);
		  if(!firstrun){
		  finish();
		  
		  }*/
	}
	
}
