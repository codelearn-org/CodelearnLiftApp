package org.codelearn.liftapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LiftListActivity extends Activity {
	
	SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lift_list);
		prefs =  getSharedPreferences("codelearn_liftapp", MODE_PRIVATE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.carpool_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent myIntent = new Intent(LiftListActivity.this, CreateLiftActivity.class);
		String phone = prefs.getString("pref_key_phone", null);
		if(phone != null){
			myIntent.putExtra("edit", true);
			}
		startActivity(myIntent);
	    return true;
	}

	
}
