package org.codelearn.liftapp;

import java.util.List;

import org.codelearn.liftapp.models.Lift;
import org.codelearn.liftapp.tasks.DeleteLiftTask;
import org.codelearn.liftapp.tasks.FetchLiftsTask;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class LiftListActivity extends ListActivity {
	private LiftAdapter carpoolItemArrayAdapter;
	SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lift_list);
		prefs =  getSharedPreferences("codelearn_liftapp", MODE_PRIVATE);
		carpoolItemArrayAdapter = new LiftAdapter(this);
		setListAdapter(carpoolItemArrayAdapter);

	}

	public void renderList(List<Lift> cp) {
		carpoolItemArrayAdapter = new LiftAdapter(this, cp);
		setListAdapter(carpoolItemArrayAdapter);

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


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
			Intent intent = new Intent(this, LiftDetailActivity.class);
			intent.putExtra("MyClass", (Lift) getListAdapter().getItem(position));
			startActivity(intent);
		
	}
	@Override
	public void onResume(){
	super.onResume();	
	(new FetchLiftsTask(this)).execute();
	}

}
