package com.codelearn.carpool;

import java.util.List;

import tasks.FetchCarpoolsTask;

import models.Carpool;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class CarpoolListActivity extends ListActivity {
	private CarpoolAdapter carpoolItemArrayAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carpool_list);
		(new FetchCarpoolsTask(this)).execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.carpool_list, menu);
		return true;
	}
	
	public void renderList(List<Carpool> cp){
		carpoolItemArrayAdapter = new CarpoolAdapter(this, cp);
		setListAdapter(carpoolItemArrayAdapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, CarpoolDetailActivity.class);
		
		intent.putExtra("MyClass", (Carpool) getListAdapter().getItem(position));
		startActivity(intent);
		
	}
}
