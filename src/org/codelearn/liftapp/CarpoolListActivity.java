package org.codelearn.liftapp;

import java.util.List;

import org.codelearn.liftapp.models.Carpool;
import org.codelearn.liftapp.tasks.DeleteCarpoolTask;
import org.codelearn.liftapp.tasks.FetchCarpoolsTask;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
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
		prefs = getSharedPreferences("codelearn_carpool", Context.MODE_PRIVATE);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.carpool_list, menu);
		return true;
	}

	public void renderList(List<Carpool> cp) {
		carpoolItemArrayAdapter = new CarpoolAdapter(this, cp);
		setListAdapter(carpoolItemArrayAdapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if (position == 0) {
			if (prefs.getString("pref_carpool_id", null) != null) {
				registerForContextMenu(v);
				openContextMenu(v);
			} else {
				Intent intent = new Intent(this, CreateCarpoolActivity.class);
				startActivity(intent);
			}
		} else {
			Intent intent = new Intent(this, CarpoolDetailActivity.class);

			intent.putExtra("MyClass",
					(Carpool) getListAdapter().getItem(position));
			startActivity(intent);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.carpool_context_menu, menu);
		menu.setHeaderTitle("Choose an Option");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// Switch on the item? ID to find the action the user selected
		switch (item.getItemId()) {
		case R.id.menu_delete:
			(new DeleteCarpoolTask(this)).execute();
			return true;
		case R.id.menu_edit:
			Intent intent = new Intent(this, CreateCarpoolActivity.class);
			intent.putExtra("MyClass", (Carpool) getListAdapter().getItem(0));
			startActivity(intent);
			return true;
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public void onResume(){
	super.onResume();	
	(new FetchCarpoolsTask(this)).execute();
	}

}
