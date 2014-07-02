package org.codelearn.liftapp;

import org.codelearn.liftapp.tasks.DeleteLiftTask;

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
		getMenuInflater().inflate(R.menu.lift_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.action_edit_carpool:
		Intent myIntent = new Intent(this, CreateLiftActivity.class);
		String phone = prefs.getString("pref_lift_id", null);
		if(phone != null){
			myIntent.putExtra("edit", true);
		}
		startActivity(myIntent);
	    return true;
		case R.id.action_delete_carpool:
			(new DeleteLiftTask(this)).execute();
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	
}
