package org.codelearn.liftapp;

import java.util.List;

import org.codelearn.liftapp.models.Carpool;
import org.codelearn.liftapp.tasks.DeleteCarpoolTask;
import org.codelearn.liftapp.tasks.FetchCarpoolsTask;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class CarpoolListActivity extends ListActivity {
	private CarpoolAdapter carpoolItemArrayAdapter;
	SharedPreferences prefs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carpool_list);
		prefs =  getSharedPreferences("codelearn_liftapp", MODE_PRIVATE);
		carpoolItemArrayAdapter = new CarpoolAdapter(this);
		setListAdapter(carpoolItemArrayAdapter);

	}

	public void renderList(List<Carpool> cp) {
		carpoolItemArrayAdapter = new CarpoolAdapter(this, cp);
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


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
			Intent intent = new Intent(this, CarpoolDetailActivity.class);
			intent.putExtra("MyClass", (Carpool) getListAdapter().getItem(position));
			startActivity(intent);
		
	}
	@Override
	public void onResume(){
	super.onResume();	
	(new FetchCarpoolsTask(this)).execute();
	}

}
