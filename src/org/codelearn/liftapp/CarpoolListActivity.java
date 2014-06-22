package org.codelearn.liftapp;

import org.codelearn.liftapp.tasks.DeleteCarpoolTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CarpoolListActivity extends Activity {
	
	SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carpool_list);
		prefs =  getSharedPreferences("codelearn_liftapp", MODE_PRIVATE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.carpool_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.action_edit_carpool:
		Intent myIntent = new Intent(this, CreateCarpoolActivity.class);
		String phone = prefs.getString("pref_carpool_id", null);
		if(phone != null){
			myIntent.putExtra("edit", true);
		}
		startActivity(myIntent);
	    return true;
		case R.id.action_delete_carpool:
			(new DeleteCarpoolTask(this)).execute();
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	
}
